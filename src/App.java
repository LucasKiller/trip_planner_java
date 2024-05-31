import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws Exception {
        //Connection conn = null;
        String driver = "com.mysql.cj.jdbc.Driver";

        User user;

        try (Connection conn = ConnectDB.conectar();){
            Class.forName(driver);

            conn.setAutoCommit(false);
            DatabaseSetup.executeInitialSQL(conn);

            user = new User("kyp", "Victor", "pass");

            user.inserir(conn);
            
            conn.commit();
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não encontrado!");
        } catch (SQLException sql_ex) {
            System.out.println("Não foi possível realizar a conexão ao server: " + sql_ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Não foi possível ler o arquivo!");
        }
    }
}
