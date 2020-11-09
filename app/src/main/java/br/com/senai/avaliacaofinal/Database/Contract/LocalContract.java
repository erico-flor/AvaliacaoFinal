package br.com.senai.avaliacaofinal.Database.Contract;

import br.com.senai.avaliacaofinal.Database.Entity.LocalEntity;

public final class LocalContract {

    private LocalContract(){}

    public static final String criarTabela() {
        return "CREATE TABLE " + LocalEntity.TABLE_NAME + " (" +
                LocalEntity._ID + " INTEGER PRIMARY KEY," +
                LocalEntity.COLUMN_NAME_BAIRRO + " TEXT," +
                LocalEntity.COLUMN_NAME_CIDADE + " TEXT," +
                LocalEntity.COLUMN_NAME_LOTACAO + " TEXT," +
                LocalEntity.COLUMN_NAME_DESCRICAO + " TEXT)";
    }

    public static final String removerTabela() {
        return "DROP TABLE IF EXISTS " + LocalEntity.TABLE_NAME;
    }
}
