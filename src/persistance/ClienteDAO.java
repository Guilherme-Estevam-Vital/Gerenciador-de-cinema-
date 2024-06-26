package persistance;

import model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private ConexaoJDBC conexaoJDBC;

    public ClienteDAO(ConexaoJDBC conexaoJDBC) {
        this.conexaoJDBC = conexaoJDBC;
    }


    public void inserir(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, cpf, email) VALUES (?, ?, ?)";
        try (Connection connection = conexaoJDBC.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCPF());
            pstmt.setString(3, cliente.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void excluir(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";
        try (Connection connection = conexaoJDBC.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, cpf = ?, email = ? WHERE id = ?";
        try (Connection connection = conexaoJDBC.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCPF());
            pstmt.setString(3, cliente.getEmail());
            pstmt.setInt(4, cliente.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> localizarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Connection connection = conexaoJDBC.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email")
                );
                clientes.add(cliente);
            }
            this.conexaoJDBC.close(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public Cliente localizarID(int id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";
        Cliente cliente = null;
        try (Connection connection = conexaoJDBC.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }
}
