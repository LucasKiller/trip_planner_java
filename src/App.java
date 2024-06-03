import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import Telas.TelaCriarViagem;
import Telas.TelaPainelViagens;

public class App {
    public static void main(String[] args) throws Exception {
        new TelaPainelViagens();

        String driver = "com.mysql.cj.jdbc.Driver";

        try (Connection conn = ConnectDB.conectar();){
            Class.forName(driver);

            conn.setAutoCommit(false);
            DatabaseSetup.executeInitialSQL(conn);

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
