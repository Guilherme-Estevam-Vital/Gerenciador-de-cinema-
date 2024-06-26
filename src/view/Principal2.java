package view;

import model.Cliente;
import persistance.ClienteDAO;
import persistance.ConexaoJDBC;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Principal2 {
    private JButton localizarIDButton;
    private JButton localizarTodosButton;
    private JButton excluirButton;
    private JButton alterarButton;
    private JPanel mainPanel2;
    private ConexaoJDBC conexaoJDBC = new ConexaoJDBC();
    ClienteDAO clienteDAO = new ClienteDAO(conexaoJDBC);

    public Principal2() {
        JFrame frame1 = new JFrame("Tela Principal 2");
        frame1.setContentPane(this.getMainPanel2());
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
        frame1.setSize(400,400);
        frame1.setResizable(false);
        /*email.setText("");
        id.setText("");
        nome.setText("");
        cpf.setText("");*/
        localizarIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(
                        frame1,
                        "Digite id para pesquisa:",
                        "Consulta individual",
                        JOptionPane.PLAIN_MESSAGE
                );
                Cliente localizar = clienteDAO.localizarID(Integer.parseInt(input));




                if (input != null) {
                    JOptionPane.showMessageDialog(
                            frame1,
                            "Id: " + String.valueOf(localizar.getId())+ "; nome: "+localizar.getNome()+"; cpf: "+localizar.getCPF()+"; email: "+localizar.getEmail(),
                            "Entrada do Usuário",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(
                        frame1,
                        "Digite id a ser excluido:",
                        "Exclusao de cliente",
                        JOptionPane.PLAIN_MESSAGE
                );


                if (input != null) {
                    JOptionPane.showMessageDialog(
                            frame1,
                            "Você digitou: " + input,
                            "Entrada do Usuário",
                            JOptionPane.INFORMATION_MESSAGE

                    );
                    Cliente x = clienteDAO.localizarID(Integer.parseInt(input));
                    JOptionPane.showMessageDialog(null, "Cliente : ."+x.getNome()+" excluido!");
                    clienteDAO.excluir(Integer.parseInt(input));


                }
            }
        });
        alterarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(
                        frame1,
                        "Digite ID para alteração:",
                        "Alterar Cliente",
                        JOptionPane.PLAIN_MESSAGE
                );

                if (input != null) {

                    Cliente cliente = clienteDAO.localizarID(Integer.parseInt(input));
                    if (cliente != null) {
                        // Criar os componentes da caixa de diálogo
                        JTextField nomeField = new JTextField(cliente.getNome(), 20);
                        JTextField cpfField = new JTextField(cliente.getCPF(), 11);
                        JTextField emailField = new JTextField(cliente.getEmail(), 30);

                        JPanel panel = new JPanel();
                        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                        panel.add(new JLabel("Nome:"));
                        panel.add(nomeField);
                        panel.add(new JLabel("CPF:"));
                        panel.add(cpfField);
                        panel.add(new JLabel("Email:"));
                        panel.add(emailField);

                        // Exibir a caixa de diálogo JOptionPane personalizada
                        int option = JOptionPane.showConfirmDialog(
                                frame1,
                                panel,
                                "Alterar Informações do Cliente",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE
                        );

                        if (option == JOptionPane.OK_OPTION) {
                            // Atualizar o objeto cliente com os novos dados
                            cliente.setNome(nomeField.getText());
                            cliente.setCPF(cpfField.getText());
                            cliente.setEmail(emailField.getText());

                            clienteDAO.alterar(cliente);

                            JOptionPane.showMessageDialog(
                                    frame1,
                                    "Cliente atualizado com sucesso!",
                                    "Sucesso",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                                frame1,
                                "Cliente com ID " + input + " não encontrado.",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }

            }
        });
        localizarTodosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Cliente> clientes = clienteDAO.localizarTodos();

                JFrame tabelaFrame = new JFrame("Tabela de Clientes");
                tabelaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                String[] colunas = {"ID", "Nome", "CPF", "Email"};
                DefaultTableModel model = new DefaultTableModel(colunas, 0);

                for (Cliente cliente : clientes) {
                    Object[] linha = {cliente.getId(), cliente.getNome(), cliente.getCPF(), cliente.getEmail()};
                    model.addRow(linha);
                }

                JTable tabela = new JTable(model);

                JScrollPane scrollPane = new JScrollPane(tabela);
                tabelaFrame.add(scrollPane);

                tabelaFrame.pack();
                tabelaFrame.setLocationRelativeTo(null); // Centraliza na tela
                tabelaFrame.setVisible(true);
            }
        });
    }

    public JPanel getMainPanel2() {
        return mainPanel2;
    }

    public void setMainPanel2(JPanel mainPanel2) {
        this.mainPanel2 = mainPanel2;
    }
}
