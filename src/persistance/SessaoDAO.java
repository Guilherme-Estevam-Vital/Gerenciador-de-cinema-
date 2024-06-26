package persistance;

import model.Cliente;
import model.Sessao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SessaoDAO {
    private ConexaoJDBC conexaoJDBC;
    public SessaoDAO(ConexaoJDBC conexaoJDBC) {
        this.conexaoJDBC = conexaoJDBC;
    }

    public void inserir(Sessao sessao) {
        String sql = "INSERT INTO sessao (nome_sessao, hora_inicio) VALUES (?, ?)";
        try (Connection connection = conexaoJDBC.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, sessao.getNomeSessao());
            pstmt.setString(2, sessao.getHoraDeInicio());
            pstmt.setInt(3, sessao.getSala().getIdSala());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void excluir(int id) {
        String sql = "DELETE FROM sessao WHERE id = ?";
        try (Connection connection = conexaoJDBC.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void alterar(Sessao sessao) {
        String sql = "UPDATE sessao SET nome_sessao = ?, hora_inicio = ?,  WHERE id = ?";
        try (Connection connection = conexaoJDBC.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, sessao.getNomeSessao());
            pstmt.setString(2, sessao.getHoraDeInicio());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Sessao> localizarTodos() {
        List<Sessao> sessaos = new ArrayList<>();
        String sql = "SELECT * FROM sessao";
        try (Connection connection = conexaoJDBC.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Sessao sessao = new Sessao(

                        rs.getInt("id"),
                        rs.getString("nome_sessao"),
                        rs.getString("hora_inicio")


                );
                sessaos.add(sessao);
            }
            this.conexaoJDBC.close(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessaos;
    }
    public Sessao localizarID(int id) {
        String sql = "SELECT * FROM sessao WHERE id = ?";
        Sessao sessao = null;
        try (Connection connection = conexaoJDBC.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                sessao = new Sessao(
                        rs.getInt("id"),
                        rs.getString("nome_sessao"),
                        rs.getString("hora_inicio")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessao;
    }

}
