package com.example.root.listacontatosbd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.root.listacontatosbd.models.Aluno;

import java.util.ArrayList;

/**
 * Created by root on 12/06/17.
 */

public class listaDAO {

    private SQLiteDatabase db;

    public static final String NOME_TABELA = "alun";
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String SEXO = "sexo";
    public static final String INTERESSES = "interesses";
    public static final String CRIAR_TABELA = "CREATE TABLE " + NOME_TABELA + "("+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, " +
            ""+NOME + " TEXT NOT NULL, " + SEXO + " TEXT NOT NULL, " + INTERESSES + " TEXT NOT NULL)";


    public listaDAO(Context context){
        db = SQLhelper.getInstance(context).getWritableDatabase();
    }

    public boolean salvar(Aluno aluno){
        long i = db.insert(NOME_TABELA, null, alunoToValues(aluno));

        // O metodo insert retorna -1 quando der merda na execucão
        if(i == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deletar(Aluno aluno) {
        String where = ID + "=?";

        String[] whereArgs = {
                aluno.getId().toString()
        };

        long i = db.delete(NOME_TABELA, where, whereArgs);

        if(i == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean editar(Aluno aluno){
        String where = ID + " =?";

        String[] whereArgs = {
                "3"
        };
        long i = db.update(NOME_TABELA, alunoToValues(aluno), where, whereArgs);

        if(i == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Aluno> listar(){
        ArrayList<Aluno> listaAlunos = new ArrayList<Aluno>();
        String sqlBusca = "select * from " + NOME_TABELA;
        Cursor cursor = db.rawQuery(sqlBusca, null);

        Aluno aluno;

        int indiceId = cursor.getColumnIndex(ID);
        int indiceNome = cursor.getColumnIndex(NOME);
        int indiceSexo = cursor.getColumnIndex(SEXO);
        int indiceInteresse = cursor.getColumnIndex(INTERESSES);

        while(cursor.moveToNext()) {
            aluno = new Aluno();

            aluno.setId(cursor.getString(indiceId));
            aluno.setNome(cursor.getString(indiceNome));
            aluno.setSexo(cursor.getString(indiceSexo));
            aluno.setInteresses(cursor.getString(indiceInteresse));

            listaAlunos.add(aluno);
        }
        return listaAlunos;
    }

    private ContentValues alunoToValues(Aluno aluno){
        ContentValues contentvalues = new ContentValues();

        contentvalues.put(NOME, aluno.getNome());
        contentvalues.put(SEXO, aluno.getSexo());
        contentvalues.put(INTERESSES, aluno.getInteresses());

        return  contentvalues;

    }
}
