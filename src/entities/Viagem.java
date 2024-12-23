package entities;

import java.io.Serializable;
import java.sql.*;

import classes.LoginAndRegisterUser;

public class Viagem implements Serializable{

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

        try (PreparedStatement stm = conn.prepareStatement(sqlDelete)) {
            stm.setInt(1, this.getID());
            stm.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException sql_ex) {
                sql_ex.printStackTrace();
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
            stm.setInt(8, this.getID());
    
            stm.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            try {
                conn.rollback();
            } catch (SQLException sql_ex) {
                sql_ex.printStackTrace();
            }
            ex.printStackTrace();
        }
    }    

    public void carregar(Connection conn, LoginAndRegisterUser manager) {

        String sqlSelect = "SELECT * FROM trips WHERE id_user = ?";

        try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
            stm.setInt(1, manager.getUser().getID());
            try (ResultSet rs = stm.executeQuery();) {
                if(rs.next()) {
                    this.setID(rs.getInt("id"));
                    this.setNomeViagem(rs.getString("nome"));
                    this.setDescricaoViagem(rs.getString("descrp"));
                    this.setDiaInicial(rs.getString("init_date"));
                    this.setDiaFinal(rs.getString("final_date"));
                    this.setImagepath(rs.getString("img_path"));
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

    public boolean isTemCarro() {
        if (this.carro != null) {
            return true;
        }
        else {
            return false;
        }
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
