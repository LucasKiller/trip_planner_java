import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Viagem {

    private int ID;
    private User user;
    private Hotel hotel;
    private Carro carro;
    private String diaFinal;
    private String diaInicial;
    private String nomeViagem;
    private String descricaoViagem;
    private String imagepath;

    public Viagem(User user, Hotel hotel, Carro carro, String diaFinal, String diaInicial, String nomeViagem, String descricaoViagem){
        this.user = user;
        this.hotel = hotel;
        this.carro = carro;
        this.diaFinal = diaFinal;
        this.diaInicial = diaInicial;
        this.nomeViagem = nomeViagem;
        this.descricaoViagem = descricaoViagem;
    }
    
    public void inserir(Connection conn) {
        
        String sqlInsert = "INSERT INTO trips (id, nome, descrp, id_hotel, id_carro, init_date, final_date, img_path, id_user) VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stm = conn.prepareStatement(sqlInsert);) {

            stm.setString(1, this.getNomeViagem());
            stm.setString(2, this.getDescricaoViagem());
            stm.setInt(3, this.getHotel().getID());
            stm.setInt(4, this.getCarro().getID());
            stm.setString(5, this.getDiaInicial());
            stm.setString(6, this.getDiaFinal());
            stm.setString(7, this.getImagepath());
            stm.setInt(8, this.getUser().getID());

            stm.execute();
        } catch(SQLException ex) {
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException sql_ex) {
                sql_ex.printStackTrace();
            }
        }
    }

     public void excluir(Connection conn) {
        String sqlDelete = "DELETE FROM trips WHERE id = ?";

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
        String sqlUpdate = "UPDATE trips SET nome = ?, descrp = ?, id_hotel = ?, id_carro = ?, init_date = ?, final_date = ?, img_path = ? WHERE id = ?";

        try (PreparedStatement stm = conn.prepareStatement(sqlUpdate)) {
            stm.setString(1, this.getNomeViagem());
            stm.setString(2, this.getDescricaoViagem());
            stm.setInt(3, this.getHotel().getID());
            stm.setInt(4, this.getCarro().getID());
            stm.setString(5, this.getDiaInicial());
            stm.setString(6, this.getDiaFinal());
            stm.setString(7, this.getImagepath());
            stm.setInt(8, this.getUser().getID());

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

        String sqlSelect = "SELECT * FROM trips WHERE id = ?";

        try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
            stm.setInt(1, this.getID());
            try (ResultSet rs = stm.executeQuery();) {
                if(rs.next()) {
                    // this.setNome(rs.getString(2));
                    // this.setEndereco(rs.getString(3));
                    // this.setCheckin(rs.getString(4));
                    // this.setCheckout(rs.getString(5));
                    // this.setImagepath(rs.getString(6));
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public String getDiaFinal() {
        return diaFinal;
    }

    public void setDiaFinal(String diaFinal) {
        this.diaFinal = diaFinal;
    }

    public String getDiaInicial() {
        return diaInicial;
    }

    public void setDiaInicial(String diaInicial) {
        this.diaInicial = diaInicial;
    }

    public String getNomeViagem() {
        return nomeViagem;
    }

    public void setNomeViagem(String nomeViagem) {
        this.nomeViagem = nomeViagem;
    }

    public String getDescricaoViagem() {
        return descricaoViagem;
    }

    public void setDescricaoViagem(String descricaoViagem) {
        this.descricaoViagem = descricaoViagem;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }
}
