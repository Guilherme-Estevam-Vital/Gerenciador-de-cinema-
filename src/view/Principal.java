package view;

import model.Cliente;
import persistance.ClienteDAO;
import persistance.ConexaoJDBC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal {
    private JTextField id;
    private JTextField nome;
    private JButton inserirButton;
    private JPanel mainPanel;
    private JTextField cpf;
    private JTextField email;
    private JButton crudButton;
    private ConexaoJDBC conexaoJDBC = new ConexaoJDBC();
    private  JFrame frame;

    public Principal() {
        JFrame frame = new JFrame("Tela Principal");
        frame.setContentPane(this.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(400,400);
        frame.setResizable(false);

        inserirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cliente = new Cliente(Integer.parseInt(id.getText()),nome.getText(), cpf.getText(), email.getText());
                ClienteDAO clienteDAO = new ClienteDAO(conexaoJDBC);
                clienteDAO.inserir(cliente);
                JOptionPane.showMessageDialog(
                        mainPanel,
                        "Cliente inserido com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE
                );


                id.setText("");
                nome.setText("");
                cpf.setText("");
                email.setText("");
            }
        });
        crudButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Principal2 principal2 = new Principal2();



            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
