package com.bernardo.colecaodediscos;

import java.io.*;
import java.util.ArrayList;

public class ArquivoHelper {
    private static final String CAMINHO_ARQUIVO = "discos.txt";

    public static void salvarDiscos(ArrayList<Disco> discos) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
            for (Disco disco : discos) {
                writer.write(disco.getNome() + ";" + disco.getTitulo() + ";" + disco.getAno());
                writer.newLine();
            }
        }
    }

    public static ArrayList<Disco> carregarDiscos() throws IOException {
        ArrayList<Disco> discos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                discos.add(new Disco(partes[0], partes[1], Integer.parseInt(partes[2])));
            }
        }
        return discos;
    }
}
