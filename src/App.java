import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        Connection conn = null;

        try {
            Class.forName(driver);

            conn = ConnectDB.conectar();

            conn.setAutoCommit(false);
            DatabaseSetup.executeInitialSQL(conn);

            new TelaInicial(conn);
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não encontrado!");
        } catch (SQLException sql_ex) {
            System.out.println("Não foi possível realizar a conexão ao server: " + sql_ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Não foi possível ler o arquivo!");
        }
    }
}
