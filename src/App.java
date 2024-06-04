import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        try {
            // Carregar o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Obter a conexão
            try (Connection conn = ConnectDB.conectar()) {

                conn.setAutoCommit(false);
                DatabaseSetup.executeInitialSQL(conn);
                System.out.println("Hotel inserido com sucesso!");

            } catch (SQLException sql_ex) {
                System.out.println("Não foi possível realizar a conexão ao servidor: " + sql_ex.getMessage());
            } catch (IOException ex) {
                System.out.println("Não foi possível ler o arquivo!");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não encontrado!");
        }
    }
}