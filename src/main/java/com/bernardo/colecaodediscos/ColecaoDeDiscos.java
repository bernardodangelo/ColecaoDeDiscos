package com.bernardo.colecaodediscos;

import java.util.ArrayList;

public class ColecaoDeDiscos {
    private ArrayList<Disco> discos;

    public ColecaoDeDiscos() {
        discos = new ArrayList<>();
    }

    public void adicionarDisco(Disco disco) {
        discos.add(disco);
    }

    public ArrayList<Disco> listarDiscos() {
        return discos;
    }

    public void atualizarDisco(int index, Disco novoDisco) throws IndexOutOfBoundsException {
        if (index < 0 || index >= discos.size()) {
            throw new IndexOutOfBoundsException("Índice inválido!");
        }
        discos.set(index, novoDisco);
    }

    public void removerDisco(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= discos.size()) {
            throw new IndexOutOfBoundsException("Índice inválido!");
        }
        discos.remove(index);
    }
}
