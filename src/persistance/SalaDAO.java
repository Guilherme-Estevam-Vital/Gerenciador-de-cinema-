package persistance;

import model.Cliente;
import model.Sala;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO {
    private ConexaoJDBC conexaoJDBC;
    public SalaDAO(ConexaoJDBC conexaoJDBC) {
        this.conexaoJDBC = conexaoJDBC;
    }
    public void inserir(Sala sala) {
        String sql = "INSERT INTO sala (nome_sala, capacidade, assento_linha,assento_coluna) VALUES (?, ?, ?,?)";
        try (Connection connection = conexaoJDBC.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, sala.getNomeDaSala());
            pstmt.setInt(2, sala.getCapacidade());
            pstmt.setString(3, sala.getAssentoLinha());
            pstmt.setInt(4, sala.getAssentoColuna());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void excluir(int id) {
        String sql = "DELETE FROM sala WHERE id = ?";
        try (Connection connection = conexaoJDBC.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void alterar(Sala sala) {
        String sql = "UPDATE sala SET nome_sala = ?, capacidade = ?, assento_linha = ?, assento_coluna = ? WHERE id = ?";
        try (Connection connection = conexaoJDBC.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, sala.getNomeDaSala());
            pstmt.setInt(2, sala.getCapacidade());
            pstmt.setString(3, sala.getAssentoLinha());
            pstmt.setInt(4, sala.getAssentoColuna());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Sala> localizarTodos() {
        List<Sala> salas = new ArrayList<>();
        String sql = "SELECT * FROM sala";
        try (Connection connection = conexaoJDBC.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Sala sala = new Sala(
                        rs.getInt("id"),
                        rs.getString("nome_sala"),
                        rs.getInt("capacidade"),
                        rs.getString("assento_linha"),
                        rs.getInt("assento_coluna")
                );
                salas.add(sala);
            }
            this.conexaoJDBC.close(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salas;
    }
    public Sala localizarID(int id) {
        String sql = "SELECT * FROM sala WHERE id = ?";
        Sala sala = null;
        try (Connection connection = conexaoJDBC.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                sala = new Sala(
                        rs.getInt("id"),
                        rs.getString("nome_sala"),
                        rs.getInt("capacidade"),
                        rs.getString("assento_linha"),
                        rs.getInt("assento_coluna")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sala;
    }


}
