import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Viagem {
    
    private User user;
    private Hotel hotel;
    private Ingresso ingresso;
    private Date diaFinal;
    private Date diaInicial;
    private String nomeViagem;
    private String descricaoViagem;
    private String diaInicio;

    public Viagem(User user, Hotel hotel, Ingresso ingresso, Date diaFinal, Date diaInicial, String nomeViagem, String descricaoViagem, String diaInicio){
        this.user = user;
        this.hotel = hotel;
        this.ingresso = ingresso;
        this.diaFinal = diaFinal;
        this.diaInicial = diaInicial;
        this.nomeViagem = nomeViagem;
        this.descricaoViagem = descricaoViagem;
        this.diaInicio = diaInicio;
    }
    
    public void inserir(Connection conn) {
        
        String sqlInsert = "INSERT INTO viagem (id, nome_viagem, descricao, data_inicio, data_final, user_id, hotel_id, ingresso_id) VALUES (null, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
            stm.setString(1, this.nomeViagem);
            stm.setString(2, this.descricaoViagem);
            stm.setString(3, this.diaInicio);
            stm.setString(4, this.diaFinal.toString());
            stm.setInt(5, this.user.getId());
            stm.setInt(6, this.hotel.getId());
            stm.setInt(7, this.ingresso.getId());

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
<<<<<<< HEAD
}
=======
}
>>>>>>> 718f6ef7e71e6da1aab3fb3dd3a8bc5fbc41c81f
