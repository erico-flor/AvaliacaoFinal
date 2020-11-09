package br.com.senai.avaliacaofinal.Database.Entity;

import android.provider.BaseColumns;

public final class EventoEntity implements BaseColumns {

    private EventoEntity(){}

    public final static String TABLE_NAME = "evento";
    public final static String COLUMN_NAME_NOME = "nome";
    public final static String COLUMN_NAME_DATA = "data";
    public final static String COLUMN_NAME_ID_LOCAL = "idlocal";
}
