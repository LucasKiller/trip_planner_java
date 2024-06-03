import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Hotel {
    
    private String nome;
    private String endereco;
    private String checkin;
    private String checkout;
    
    /*
     * Construtor para a criacao de um novo usuario
     */
    public Hotel(String nome, String endereco, String checkin, String checkout) {
        this.nome = nome;
        this.endereco = endereco;
        this.checkin = checkin;
        this.checkout = checkout;
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
        String sqlInsert = "INSERT INTO hotel(nome, endereco, checkin, checkout) VALUES (null, ?, ?, ?)";

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
}