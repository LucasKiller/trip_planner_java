package utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.ManageUserLogin;
import entities.Carro;
import entities.Hotel;
import entities.Viagem;

public class GetTrips {
    
    public static Viagem[] getTrips(Connection conn, ManageUserLogin manager) {
        ArrayList <Viagem> viagens = new ArrayList<Viagem>();

        String sqlSelect = ("SELECT * FROM trips WHERE id_user = ?");

        try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
            stm.setInt(1, manager.getUser().getID());
            try (ResultSet rs = stm.executeQuery();) {
                while(rs.next()) {
                    Hotel hotel = new Hotel((rs.getInt(4)));
                    hotel.carregar(conn);

                    Carro carro = new Carro((rs.getInt(5)));
                    carro.carregar(conn);

                    Viagem viagem = new Viagem(manager.getUser(), hotel, carro, rs.getString("final_date"), rs.getString("init_date"), rs.getString("nome"), rs.getString("descrp"));
                    viagens.add(viagem);
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        } catch (SQLException sql_ex) {
            System.out.println(sql_ex.getStackTrace());
        }
        return viagens.toArray(new Viagem[0]);
    }
}
