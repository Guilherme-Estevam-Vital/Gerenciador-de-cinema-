package persistance;

import model.Cinema;
import model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CinemaDAO {
    private ConexaoJDBC conexaoJDBC;
    public CinemaDAO(ConexaoJDBC conexaoJDBC) {
        this.conexaoJDBC = conexaoJDBC;
    }
    public void inserir(Cinema cinema) {
        String sql = "INSERT INTO cinema (nome_cinema) VALUES (?)";
        try (Connection connection = conexaoJDBC.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, cinema.getNomeCinema());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void excluir(int id) {
        String sql = "DELETE FROM cinema WHERE id = ?";
        try (Connection connection = conexaoJDBC.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void alterar(Cinema cinema) {
        String sql = "UPDATE cinema SET nome_cinema = ? WHERE id = ?";
        try (Connection connection = conexaoJDBC.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, cinema.getNomeCinema());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Cinema> localizarTodos() {
        List<Cinema> cinemas = new ArrayList<>();
        String sql = "SELECT * FROM cinema";
        try (Connection connection = conexaoJDBC.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cinema cinema = new Cinema(
                        rs.getInt("id"),
                        rs.getString("nome_cinema")

                );
                cinemas.add(cinema);
            }
            this.conexaoJDBC.close(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cinemas;
    }
    public Cinema localizarID(int id) {
        String sql = "SELECT * FROM cinema WHERE id = ?";
        Cinema cinema = null;
        try (Connection connection = conexaoJDBC.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                cinema = new Cinema(

                        rs.getInt("id"),
                        rs.getString("nome_cinema")

                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cinema;
    }


}
