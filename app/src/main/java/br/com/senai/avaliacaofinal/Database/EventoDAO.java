package br.com.senai.avaliacaofinal.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.avaliacaofinal.Database.Entity.EventoEntity;
import br.com.senai.avaliacaofinal.Database.Entity.LocalEntity;
import br.com.senai.avaliacaofinal.Modelo.Evento;
import br.com.senai.avaliacaofinal.Modelo.Local;

public class EventoDAO {

    private final String SQL_LISTAR_TODOS = "SELECT evento._id, nome, data, idlocal, descricao, bairro, cidade, lotacao  FROM " +
            EventoEntity.TABLE_NAME +
            " INNER JOIN " + LocalEntity.TABLE_NAME + " ON " + LocalEntity.TABLE_NAME + "." + LocalEntity._ID +
            " = " + EventoEntity.COLUMN_NAME_ID_LOCAL;

    private String sql;
    private DBGateway dbGateway;


    public EventoDAO(Context context) {
        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvar(Evento evento) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventoEntity.COLUMN_NAME_NOME, evento.getNome());
        contentValues.put(EventoEntity.COLUMN_NAME_DATA, evento.getData());
        contentValues.put(EventoEntity.COLUMN_NAME_ID_LOCAL, evento.getLocal().getId());
        if (evento.getId() > 0) {
            return dbGateway.getDatabase().update(EventoEntity.TABLE_NAME,
                    contentValues,
                    EventoEntity._ID + "=?",
                    new String[]{String.valueOf(evento.getId())}) > 0;
        }
        return dbGateway.getDatabase().insert(EventoEntity.TABLE_NAME,
                null, contentValues) > 0;

    }

    public boolean excluir(Evento evento) {
        return dbGateway.getDatabase().delete(EventoEntity.TABLE_NAME,
                EventoEntity._ID + "=?",
                new String[]{String.valueOf(evento.getId())}) > 0;
    }

    public List<Evento> listar() {
        List<Evento> eventos = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_NOME));
            String data = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_DATA));

            int idlocal = cursor.getInt(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_ID_LOCAL));
            String descricao = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_DESCRICAO));
            String bairro = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_BAIRRO));
            String cidade = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CIDADE));
            String lotacao = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_LOTACAO));

            Local local = new Local(idlocal, descricao, bairro, cidade, lotacao);
            eventos.add(new Evento(id, nome, data, local));
        }
        cursor.close();
        return eventos;
    }

    public List<Evento> buscar(String pesquisa, Boolean ordem) {

        String ordemNome;

        if(ordem) {
            ordemNome = "ASC";
        }else {
            ordemNome = "DESC";
        }
        String SQL_LISTAR_LIKE = "SELECT evento._id, nome, data, idlocal, descricao, bairro, cidade, lotacao FROM " +
                EventoEntity.TABLE_NAME +
                " INNER JOIN " + LocalEntity.TABLE_NAME + " ON " + LocalEntity.TABLE_NAME + "." + LocalEntity._ID +
                " = " + EventoEntity.COLUMN_NAME_ID_LOCAL +" where "+EventoEntity.COLUMN_NAME_NOME +
                " like '%" + pesquisa + "%'" + " OR " + LocalEntity.COLUMN_NAME_CIDADE + " like '%"+ pesquisa + "%'" +
                " ORDER BY " + EventoEntity.COLUMN_NAME_NOME + " " + ordemNome;

        if( (pesquisa == null || pesquisa.trim().equals("")) && !ordem){
            sql = SQL_LISTAR_TODOS;
        }else {
            sql = SQL_LISTAR_LIKE;
        }

        List<Evento> eventos = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_NOME));
            String data = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_DATA));

            int idlocal = cursor.getInt(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_ID_LOCAL));
            String descricao = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_DESCRICAO));
            String bairro = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_BAIRRO));
            String cidade = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CIDADE));
            String lotacao = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_LOTACAO));

            Local local = new Local(idlocal, descricao, bairro, cidade, lotacao);
            eventos.add(new Evento(id, nome, data, local));
        }
        cursor.close();
        return eventos;


    }
}
