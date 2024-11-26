package com.bernardo.colecaodediscos;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {
    private ColecaoDeDiscos colecao;
    private DefaultListModel<String> listModel;
    private JList<String> listaDiscos;

    public MainFrame() {
        colecao = new ColecaoDeDiscos();
        listModel = new DefaultListModel<>();
        listaDiscos = new JList<>(listModel);

        setTitle("Coleção de Discos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelBotoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnRemover = new JButton("Remover");
        JButton btnEditar = new JButton("Editar");
        panelBotoes.add(btnAdicionar);
        panelBotoes.add(btnRemover);
        panelBotoes.add(btnEditar);

        JScrollPane scrollPane = new JScrollPane(listaDiscos);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);

        btnAdicionar.addActionListener(e -> adicionarDisco());
        btnRemover.addActionListener(e -> removerDisco());
        btnEditar.addActionListener(e -> editarDisco());

        carregarDiscosAoIniciar();
    }

    private void carregarDiscosAoIniciar() {
        try {
            for (Disco disco : ArquivoHelper.carregarDiscos()) {
                colecao.adicionarDisco(disco);
                listModel.addElement(disco.toString());
            }
            JOptionPane.showMessageDialog(this, "Discos carregados com sucesso!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Nenhum arquivo de discos encontrado ou erro ao carregar discos.");
        }
    }

    private void adicionarDisco() {
        String nome;
        String titulo;
        int ano;

        while (true) {
            nome = JOptionPane.showInputDialog(this, "Nome do Artista:");
            if (nome == null) {
                return;
            }
            if (!nome.trim().isEmpty()) {
                break;
            } else {
                JOptionPane.showMessageDialog(this, "O campo 'Nome do Artista' é obrigatório.");
            }
        }

        while (true) {
            titulo = JOptionPane.showInputDialog(this, "Título do Disco:");
            if (titulo == null) {
                return;
            }
            if (!titulo.trim().isEmpty()) {
                break;
            } else {
                JOptionPane.showMessageDialog(this, "O campo 'Título do Disco' é obrigatório.");
            }
        }

        while (true) {
            try {
                String inputAno = JOptionPane.showInputDialog(this, "Ano de Lançamento:");
                if (inputAno == null) {
                    return;
                }
                if (inputAno.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "O campo 'Ano de Lançamento' é obrigatório.");
                    continue;
                }
                ano = Integer.parseInt(inputAno);
                break;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ano inválido! Insira um número.");
            }
        }

        Disco disco = new Disco(nome, titulo, ano);
        colecao.adicionarDisco(disco);
        listModel.addElement(disco.toString());

        salvarAutomaticamente(); 
    }

    private void removerDisco() {
        int index = listaDiscos.getSelectedIndex();
        if (index != -1) {
            colecao.removerDisco(index);
            listModel.remove(index);

            salvarAutomaticamente();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um disco para remover.");
        }
    }

    private void editarDisco() {
        int index = listaDiscos.getSelectedIndex();
        if (index != -1) {
            Disco discoSelecionado = colecao.listarDiscos().get(index);

            String novoNome = JOptionPane.showInputDialog(this, "Nome do Artista:", discoSelecionado.getNome());
            String novoTitulo = JOptionPane.showInputDialog(this, "Título do Disco:", discoSelecionado.getTitulo());
            int novoAno;

            while (true) {
                try {
                    String inputAno = JOptionPane.showInputDialog(this, "Ano de Lançamento:", discoSelecionado.getAno());
                    if (inputAno == null) {
                        return;
                    }
                    if (inputAno.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "O campo 'Ano de Lançamento' é obrigatório.");
                        continue;
                    }
                    novoAno = Integer.parseInt(inputAno);
                    break;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ano inválido! Insira um número.");
                }
            }

            discoSelecionado.setNome(novoNome);
            discoSelecionado.setTitulo(novoTitulo);
            discoSelecionado.setAno(novoAno);

            listModel.set(index, discoSelecionado.toString());

            salvarAutomaticamente();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um disco para editar.");
        }
    }

    private void salvarAutomaticamente() {
        try {
            ArquivoHelper.salvarDiscos(colecao.listarDiscos());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar disco: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
