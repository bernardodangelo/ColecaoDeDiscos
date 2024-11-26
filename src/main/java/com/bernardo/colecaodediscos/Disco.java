package com.bernardo.colecaodediscos;

public class Disco extends Artista {
    private String titulo;
    private int ano;

    public Disco(String nome, String titulo, int ano) {
        super(nome);
        this.titulo = titulo;
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return titulo + " - " + getNome() + " (" + ano + ")";
    }
}
