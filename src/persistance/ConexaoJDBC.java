package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoJDBC {
    // Configurações do banco de dados
    private static final String URL = "jdbc:postgresql://localhost:5432/GerenciadorCinema";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "84075479";
    private Connection connection;



    public  Connection getConnection() throws SQLException {
        return this.connection = DriverManager.getConnection(URL, USUARIO, SENHA);
    }


    public static void close(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}
