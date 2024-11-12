package entities;

import java.io.Serializable;
import java.sql.*;

public class User implements Serializable{
    
    private String user;
    private String nome;
    private String pass;
    private int ID = 0;
    

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
    public User(String nome, String user, String pass) {
        this.user = user;
        this.nome = nome;
        this.pass = pass;
    }

    // public User(byte[] data) {

    //     try {
    //         ByteArrayInputStream byteIn = new ByteArrayInputStream(data);
    //         ObjectInputStream in = new ObjectInputStream(byteIn);

    //         User testUser = (User) in.readObject();

    //         this.user = testUser.user;
    //         this.pass = testUser.pass;


    //     } catch (IOException | ClassNotFoundException e) {
    //         e.printStackTrace();
    //     }

    // }

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
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }

    // Metodos de persistência
    /*
     * No caso de novo usuario
     */
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
                sql_ex.printStackTrace();
            }
        }
    }

    /*
     * Extra
     */
    public void excluir(Connection conn) {
        String sqlDelete = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
            stm.setInt(1, this.getID());

            stm.execute();
        } catch(Exception ex) {
            try {
                conn.rollback();
            } catch(SQLException sql_ex) {
                System.out.println(sql_ex.getStackTrace());
            }
        }
    }

    /*
     * Extra
     */
    public void atualiza(Connection conn) {
        String sqlUpdate = "UPDATE users SET nome = ?, login = ?, password = ? WHERE id = ?";

        try (PreparedStatement stm = conn.prepareStatement(sqlUpdate)) {
            stm.setString(1, this.getNome());
            stm.setString(2, this.getUser());
            stm.setString(3, this.getPass());
            stm.setInt(4, this.getID());

            stm.execute();
        } catch (Exception ex) {
            try {
                conn.rollback();
            } catch(SQLException sql_ex) {
                System.out.println(sql_ex.getStackTrace());
            }
        }
    }

    /*
     * Verificar se o usuario existe
     */
    public void carregar(Connection conn) {

        String sqlSelect = "SELECT * FROM users WHERE login = ? AND password = ?";

        try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
            stm.setString(1, this.getUser());
            stm.setString(2, this.getPass());
            try (ResultSet rs = stm.executeQuery();) {
                if(rs.next()) {
                    this.setNome(rs.getString(2));
                    this.setID(rs.getInt(1));
                } 
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        } catch (SQLException sql_ex) {
            System.out.println(sql_ex.getStackTrace());
        }
    }

}
