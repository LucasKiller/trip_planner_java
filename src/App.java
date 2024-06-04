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

                Hotel hotel = new Hotel("Fasano", "rua marilha, 918", "21/02/1912", "13/04/1938"); // Adicione o caminho da imagem se necessário
                hotel.inserir(conn);

                conn.commit();
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