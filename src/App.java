import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";

        Carro carro;

        try (Connection conn = ConnectDB.conectar();){
            Class.forName(driver);

            conn.setAutoCommit(false);
            DatabaseSetup.executeInitialSQL(conn);

            carro = new Carro("Fiat", "BRA2E19", true, "");

            carro.inserir(conn);
            
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
