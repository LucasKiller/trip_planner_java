import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Hotel {
    
    private int ID;
    private String nome;
    private String endereco;
    private String checkin;
    private String checkout;

    public Hotel(String nome, String endereco, String checkin, String checkout) {
        this.nome = nome;
        this.endereco = endereco;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Hotel(int ID) {
        this.ID = ID;
    }

    //Metodos de acesso e modificadores
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getCheckin() {
        return checkin;
    }
    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }
    public String getCheckout() {
        return checkout;
    }
    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public void inserir(Connection conn) {
        String sqlInsert = "INSERT INTO hotel(id, nome, endereco, checkin, checkout) VALUES (null, ?, ?, ?, ?)";

        try (PreparedStatement stm = conn.prepareStatement(sqlInsert)) {
            stm.setString(1, this.getNome());
            stm.setString(2, this.getEndereco());
            stm.setString(3, this.getCheckin());
            stm.setString(4, this.getCheckout());

            stm.execute();
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException sql_ex) {
                System.out.println(sql_ex.getMessage());
            }
            System.out.println("Erro ao inserir hotel: " + ex.getMessage());
        }
    }

        public void excluir(Connection conn) {
        String sqlDelete = "DELETE FROM hotel WHERE id = ?";

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

    public void atualiza(Connection conn) {
        String sqlUpdate = "UPDATE hotel SET nome = ?, endereco = ?, checkin = ?, checkout = ?, img_path = ? WHERE id = ?";

        try (PreparedStatement stm = conn.prepareStatement(sqlUpdate)) {
            stm.setString(1, this.getNome());
            stm.setString(2, this.getEndereco());
            stm.setString(3, this.getCheckin());
            stm.setString(4, this.getCheckout());
            stm.setInt(6, this.getID());

            stm.execute();
        } catch (Exception ex) {
            try {
                conn.rollback();
            } catch(SQLException sql_ex) {
                System.out.println(sql_ex.getStackTrace());
            }
        }
    }

    public void carregar(Connection conn) {

        String sqlSelect = "SELECT * FROM hotel WHERE id = ?";

        try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
            stm.setInt(1, this.getID());
            try (ResultSet rs = stm.executeQuery();) {
                if(rs.next()) {
                    this.setNome(rs.getString(2));
                    this.setEndereco(rs.getString(3));
                    this.setCheckin(rs.getString(4));
                    this.setCheckout(rs.getString(5));
                } 
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        } catch (SQLException sql_ex) {
            System.out.println(sql_ex.getStackTrace());
        }
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }
}