import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Viagem {
    
    private User user;
    private Hotel hotel;
    private Carro carro;
    private String diaFinal;
    private String diaInicial;
    private String nomeViagem;
    private String descricaoViagem;
    private String diaInicio;

    public Viagem(User user, Hotel hotel, Carro carro, String diaFinal, String diaInicial, String nomeViagem, String descricaoViagem, String diaInicio){
        this.user = user;
        this.hotel = hotel;
        this.carro = carro;
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
}
