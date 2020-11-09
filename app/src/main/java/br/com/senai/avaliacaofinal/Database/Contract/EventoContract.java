package br.com.senai.avaliacaofinal.Database.Contract;

import br.com.senai.avaliacaofinal.Database.Entity.EventoEntity;
import br.com.senai.avaliacaofinal.Database.Entity.LocalEntity;

public final class EventoContract {

    private EventoContract(){}

    public final static String criarTabela() {

        return "CREATE TABLE " + EventoEntity.TABLE_NAME + " (" +
                EventoEntity._ID + " INTEGER PRIMARY KEY," +
                EventoEntity.COLUMN_NAME_NOME + " TEXT," +
                EventoEntity.COLUMN_NAME_DATA + " TEXT," +
                EventoEntity.COLUMN_NAME_ID_LOCAL + " INTEGER," +
                "FOREIGN KEY (" + EventoEntity.COLUMN_NAME_ID_LOCAL + ") REFERENCES " +
                LocalEntity.TABLE_NAME + "(" + LocalEntity._ID + "))";
    }

    public final static String removerTabela() {
        return "DROP TABLE IF EXISTS " + EventoEntity.TABLE_NAME;
    }
}
