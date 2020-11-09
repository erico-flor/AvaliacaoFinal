package br.com.senai.avaliacaofinal.Database.Entity;

import android.provider.BaseColumns;

public final class LocalEntity implements BaseColumns {

    private LocalEntity(){}

    public final static String TABLE_NAME = "local";
    public final static String COLUMN_NAME_DESCRICAO = "descricao";
    public final static String COLUMN_NAME_BAIRRO = "bairro";
    public final static String COLUMN_NAME_CIDADE = "cidade";
    public final static String COLUMN_NAME_LOTACAO = "lotacao";
}
