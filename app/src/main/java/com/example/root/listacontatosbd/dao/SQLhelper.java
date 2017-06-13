package com.example.root.listacontatosbd.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 12/06/17.
 */

public class SQLhelper extends SQLiteOpenHelper{

    private static SQLhelper instance;

    private final static String NOME_BANCO = "sqlitedb";
    private final static int versao = 1;

    private SQLhelper(Context context) {
        super(context, NOME_BANCO, null, versao);
    }

    public static SQLhelper getInstance(Context context) {
        if(instance == null){
            instance = new SQLhelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(listaDAO.CRIAR_TABELA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
