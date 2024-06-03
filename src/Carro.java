import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

public class Carro {
    private String nome;
    private String marca;
    private String placa;
    private boolean temSeguro;
    private String imagem;

    public Carro(String placa) {
        this.placa = placa;
    }

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
        String sqlInsert = "INSERT INTO carros(id, nome, marca, placa, temSeguro, imagem) VALUES (null, ?, ?, ?, ?, ?)";

        try (PreparedStatement stm = conn.prepareStatement(sqlInsert)) {
            stm.setString(1, this.getNome());
            stm.setString(2, this.getMarca());
            stm.setString(3, this.getPlaca());
            stm.setBoolean(4, this.isTemSeguro());
            stm.setString(5, this.getImagem());

            stm.execute();
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException sql_ex) {
                System.out.println(sql_ex.getMessage());
            }
            System.out.println("Erro ao inserir carro: " + ex.getMessage());
        }
    }

    public void deletar(Connection conn) {

        String sqlDelete = "DELETE FROM carro WHERE placa = ?";

        try (PreparedStatement stm = conn.prepareStatement(sqlDelete);) {

            stm.setString(1, this.getPlaca());

            stm.execute();

        }   catch(SQLException sql_ex) {
            sql_ex.printStackTrace();
        } 

    }

    public void atualizar(Connection conn) {
        String sqlUpdate = "UPDATE carro SET nome = ?, marca = ?, temSeguro = ?, img_path = ? WHERE placa = ?";

        try (PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {

            stm.setString(1, this.getNome());
            stm.setString(2, this.getMarca());
            stm.setBoolean(3, this.isTemSeguro());
            stm.setString(4, this.getImagem());
            stm.setString(5, this.getPlaca());

            stm.execute();
        } catch(SQLException sql_ex) {
            sql_ex.printStackTrace();
        }
    }

    public void carregar(Connection conn) {
        String sqlSelect = "SELECT * FROM carro WHERE placa = ?";

        try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
            stm.setString(1, this.getPlaca());

            stm.execute();
        } catch(SQLException sql_ex) {
            sql_ex.printStackTrace();
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

