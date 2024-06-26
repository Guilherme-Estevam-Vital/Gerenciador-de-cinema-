package persistance;

import model.Cliente;
import model.Filme;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAO {
    private ConexaoJDBC conexaoJDBC;
    public FilmeDAO(ConexaoJDBC conexaoJDBC) {
        this.conexaoJDBC = conexaoJDBC;
    }
    public void inserir(Filme filme) {
        String sql = "INSERT INTO filme (titulo, duracao) VALUES (?, ?)";
        try (Connection connection = conexaoJDBC.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, filme.getTitulo());
            pstmt.setFloat(2, filme.getDuracao());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void excluir(int id) {
        String sql = "DELETE FROM filme WHERE id = ?";
        try (Connection connection = conexaoJDBC.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void alterar(Filme filme) {
        String sql = "UPDATE filme SET titulo = ?, duracao = ? WHERE id = ?";
        try (Connection connection = conexaoJDBC.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, filme.getTitulo());
            pstmt.setFloat(2, filme.getDuracao());
            pstmt.setInt(3, filme.getIdFilme());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   public List<Filme> localizarTodos() {
       List<Filme> filmes = new ArrayList<>();
       String sql = "SELECT * FROM filme";
       try (Connection connection = conexaoJDBC.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
           while (rs.next()) {
               Filme filme = new Filme(
                       rs.getInt("id"),
                       rs.getString("titulo"),
                       rs.getFloat("duracao")



                              );
               filmes.add(filme);
           }
           this.conexaoJDBC.close(connection);
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return filmes;
   }
    public Filme localizarID(int id) {
        String sql = "SELECT * FROM filme WHERE id = ?";
        Filme filme = null;
        try (Connection connection = conexaoJDBC.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                filme = new Filme(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getFloat("duracao")

                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filme;
    }


}
