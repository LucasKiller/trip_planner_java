package utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.Carro;
import classes.Hotel;
import classes.ManageUserLogin;
import classes.Viagem;

public class GetTrips {
    
    public static ArrayList<Viagem> getTrips(Connection conn, ManageUserLogin manager) {
        ArrayList<Viagem> viagens = new ArrayList<>();
    
        String sqlSelect = "SELECT * FROM trips WHERE id_user = ?";
    
        try (PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
            stm.setInt(1, manager.getUser().getID());
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Hotel hotel = new Hotel(rs.getInt("id_hotel"));
                    hotel.carregar(conn);
    
                    Carro carro = new Carro(rs.getInt("id_carro"));
                    carro.carregar(conn);
    
                    Viagem viagem = new Viagem(manager.getUser(), hotel, carro, 
                                               rs.getString("final_date"), 
                                               rs.getString("init_date"), 
                                               rs.getString("nome"), 
                                               rs.getString("descrp"));
                                               
                    viagem.setID(rs.getInt("id"));
                    viagens.add(viagem);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return viagens;
    }
}
