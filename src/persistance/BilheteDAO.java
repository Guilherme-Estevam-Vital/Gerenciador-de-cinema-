package persistance;

import model.Bilhete;
import model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BilheteDAO {
    private ConexaoJDBC conexaoJDBC;
    public BilheteDAO(ConexaoJDBC conexaoJDBC) {
        this.conexaoJDBC = conexaoJDBC;
    }
    public void inserir(Bilhete bilhete) {
        String sql = "INSERT INTO bilhete (numero_bilhete, cliente_id, sessao_id) VALUES (?, ?, ?)";
        try (Connection connection = conexaoJDBC.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, bilhete.getNumeroBilhete());
            pstmt.setInt(2, bilhete.getIdCliente());
            pstmt.setInt(3, bilhete.getIdSessao());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void excluir(int id) {
        String sql = "DELETE FROM bilhete WHERE id = ?";
        try (Connection connection = conexaoJDBC.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void alterar(Bilhete bilhete) {
        String sql = "UPDATE bilhete SET numero_bilhete = ?, cliente_id = ?, sessao_id = ? WHERE id = ?";
        try (Connection connection = conexaoJDBC.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, bilhete.getNumeroBilhete());
            pstmt.setInt(2, bilhete.getIdCliente());
            pstmt.setInt(3, bilhete.getIdSessao());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Bilhete> localizarTodos() {
        List<Bilhete> bilhetes = new ArrayList<>();
        String sql = "SELECT * FROM bilhete";
        try (Connection connection = conexaoJDBC.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Bilhete bilhete = new Bilhete(
                        rs.getInt("numero_bilhete"),
                        rs.getInt("cliente_id"),
                        rs.getInt("sessao_id")

                );
                bilhetes.add(bilhete);
            }
            this.conexaoJDBC.close(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bilhetes;
    }

    public Bilhete localizarID(int id) {
        String sql = "SELECT * FROM bilhete WHERE id = ?";
        Bilhete bilhete = null;
        try (Connection connection = conexaoJDBC.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                bilhete = new Bilhete(
                        rs.getInt("numero_bilhete")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bilhete;
    }

}
