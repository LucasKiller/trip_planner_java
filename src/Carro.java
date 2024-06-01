import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Carro {
    private String marca;
    private String placa;
    private boolean temSeguro;
    private String imagem;

    public Carro(String marca, String placa, boolean temSeguro, String imagem) {
        this.marca = marca;
        this.placa = placa;
        this.temSeguro = temSeguro;
        this.imagem = imagem;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public boolean isTemSeguro() {
        return temSeguro;
    }

    public void setTemSeguro(boolean temSeguro) {
        this.temSeguro = temSeguro;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public void inserir(Connection conn) {
        
        String sqlInsert = "INSERT INTO users(marca, placa, temSeguro, Imagem) VALUES (null, ?, ?, ?)";

        try (PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
            stm.setString(1, this.getMarca());
            stm.setString(2, this.getPlaca());
            stm.setBoolean(3, this.isTemSeguro());
            stm.setString(3, this.getImagem());

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
