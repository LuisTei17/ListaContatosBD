package com.example.root.listacontatosbd.models;

import java.io.Serializable;

/**
 * Created by root on 12/06/17.
 */

public class Aluno implements Serializable {

    private String nome, id, sexo, interesses;

    public Aluno() {
    }

    public Aluno(String nome, String sexo, String interesses) {
        this.nome = nome;
        this.sexo = sexo;
        this.interesses = interesses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getInteresses() {
        return interesses;
    }

    public void setInteresses(String interesses) {
        this.interesses = interesses;
    }

    @Override
    public String toString() {
        return this.nome + ": " + this.sexo;
    }
}
