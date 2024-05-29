import java.sql.Statement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/*
 *  Classe dedicada para a preparacao da database no localhost
 */

public class DatabaseSetup {

    private static final String sqlFilePath = "./database/load_database.sql";

    /*
     * Executa o script SQL localizado no diretorio acima
     */
    
    public static void executeInitialSQL(Connection conn) throws SQLException, IOException {

        try(BufferedReader br = new BufferedReader(new FileReader(sqlFilePath)); // Leitura linha por linha do arquivo pelo objeto BufferedReader
            Statement stm = conn.createStatement();) { // cria a statement
            
            StringBuilder sqlInsert = new StringBuilder();
            String line;

            while((line = br.readLine()) != null) { //enquanto tiver linhas no arquivo
                sqlInsert.append(line); // adiciona no StringBuilder
            
                if(line.trim().endsWith(";")) { // Caso a linha atual sem espacos termine com ; o comando finaliza e a StringBuilder reinicia
                    stm.execute(sqlInsert.toString());
                    sqlInsert = new StringBuilder();
            
                }
            }

            if(sqlInsert.length() > 0) { // Caso o ultimo comando nao tenha ponto e virgula
                stm.execute(sqlInsert.toString());
            }
        }

    }   

}
