import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {
    
    private String user;
    private String nome;
    private String pass;
    private String ID;

    /*
     * Construtor para a verificar a existencia do login e senha do usuario
     */
    public User(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    /*
     * Construtor para a criacao de um novo usuario
     */
    public User(String user, String nome, String pass) {
        this.user = user;
        this.nome = nome;
        this.pass = pass;
    }

    //Metodos de acesso e modificadores
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        ID = iD;
    }

    // Metodos de persistência
    public void inserir(Connection conn) {
        
        String sqlInsert = "INSERT INTO users(id, nome, login, password) VALUES (null, ?, ?, ?)";

        try (PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
            stm.setString(1, this.getNome());
            stm.setString(2, this.getUser());
            stm.setString(3, this.getPass());

            stm.execute();
        } catch(Exception ex) {

            try {
                conn.rollback();
            } catch (SQLException sql_ex) {
                System.out.println(sql_ex.getStackTrace());
            }
        }
    }

}
