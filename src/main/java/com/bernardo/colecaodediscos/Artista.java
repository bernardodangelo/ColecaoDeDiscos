package com.bernardo.colecaodediscos;

public class Artista {
    private String nome;

    public Artista(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Artista: " + nome;
    }
}
