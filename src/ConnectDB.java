import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Classe dedicada para a conexao ao servidor MySQL
 */

public class ConnectDB {

    private static final String servidor = "localhost";
    private static final String porta = "3306";
    private static final String usuario = "aluno";
    private static final String senha = "alunos";

    private static Connection conn = null;

    /*
     * Realiza a conexao ao servidor descrito nas constantes acima
     */

    public static Connection conectar() throws SQLException {
        if (conn != null) {
            return conn;
        }

        conn = DriverManager.getConnection("jdbc:mysql://" + servidor + ":" + porta, usuario, senha);
        return conn;
    }

    public static Connection getConnection() {
        return conn;
    }

}