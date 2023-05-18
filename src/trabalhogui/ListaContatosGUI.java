package trabalhogui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Marino Ricardo
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ListaContatosGUI extends JFrame implements ActionListener {

    private JTextField nomeField;
    private JTextField telefoneField;
    private JTextArea listaContatosArea;
    private JButton adicionarButton;
    private JButton removerButton;
    private JButton pesquisarButton;
    private List<Contato> contatos;

    public ListaContatosGUI() {
        setTitle("Lista de Contatos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel formularioPanel = new JPanel();
        formularioPanel.setLayout(new GridLayout(3, 2));
        formularioPanel.add(new JLabel("Nome: "));
        nomeField = new JTextField();
        formularioPanel.add(nomeField);
        formularioPanel.add(new JLabel("Telefone: "));
        telefoneField = new JTextField();
        formularioPanel.add(telefoneField);
        adicionarButton = new JButton("Adicionar");
        adicionarButton.addActionListener(this);
        formularioPanel.add(adicionarButton);
        removerButton = new JButton("Remover");
        removerButton.addActionListener(this);
        formularioPanel.add(removerButton);
        pesquisarButton = new JButton("Pesquisar");
        pesquisarButton.addActionListener(this);
        formularioPanel.add(pesquisarButton);

        listaContatosArea = new JTextArea();
        listaContatosArea.setEditable(false);

        add(formularioPanel, BorderLayout.NORTH);
        add(new JScrollPane(listaContatosArea), BorderLayout.CENTER);

        contatos = new ArrayList<>();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adicionarButton) {
            String nome = nomeField.getText();
            String telefone = telefoneField.getText();
            Contato contato = new Contato(nome, telefone);
            contatos.add(contato);
            atualizarListaContatos();
            limparCampos();
        } else if (e.getSource() == removerButton) {
            String nome = nomeField.getText();
            boolean removido = false;
            for (Contato contato : contatos) {
                if (contato.getNome().equalsIgnoreCase(nome)) {
                    contatos.remove(contato);
                    removido = true;
                    break;
                }
            }
            if (removido) {
                JOptionPane.showMessageDialog(this, "Contato removido!");
            } else {
                JOptionPane.showMessageDialog(this, "Contato não encontrado!");
            }
            atualizarListaContatos();
            limparCampos();
        } else if (e.getSource() == pesquisarButton) {
            String nome = nomeField.getText();
            boolean encontrado = false;
            for (Contato contato : contatos) {
                if (contato.getNome().equalsIgnoreCase(nome)) {
                    JOptionPane.showMessageDialog(this, "Contato encontrado:\n" + contato);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(this, "Contato não encontrado!");
            }
            limparCampos();
        }
    }

    private void atualizarListaContatos() {
        listaContatosArea.setText("");
        for (Contato contato : contatos) {
            listaContatosArea.append(contato.toString() + "\n");
        }
    }

    private void limparCampos() {
        nomeField.setText("");
        telefoneField.setText("");
    }

    private class Contato {

        private String nome;
        private String telefone;

        public Contato(String nome, String telefone) {
            this.nome = nome;
            this.telefone = telefone;
        }

        public String getNome() {
            return nome;
        }

        public String getTelefone() {
            return telefone;
        }

        @Override
        public String toString() {
            return "Nome: " + nome + ", Telefone: " + telefone;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ListaContatosGUI();
            }
        });
    }
}
