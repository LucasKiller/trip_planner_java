package entities;

import java.io.Serializable;
import java.sql.*;

public class Carro implements Serializable {
    private String nome;
    private String marca;
    private String placa;
    private boolean temSeguro;
    private int valorSeguro;
    private String imagem;

    private int ID;

    public Carro(int ID) {
        this.ID = ID;
    }

    public Carro(String nome, String marca, String placa, boolean temSeguro, int valorSeguro, String imagem) {
        this.nome = nome;
        this.marca = marca;
        this.placa = placa;
        this.temSeguro = temSeguro;
        this.valorSeguro = valorSeguro;
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
        String sqlInsert = "INSERT INTO carro(id, nome, marca, placa, temSeguro, valorSeguro, img_path) VALUES (null, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stm = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, this.getNome());
            stm.setString(2, this.getMarca());
            stm.setString(3, this.getPlaca());
            stm.setBoolean(4, this.isTemSeguro());
            stm.setInt(5, this.getValorSeguro());
            stm.setString(6, this.getImagem());

            stm.executeUpdate();

            ResultSet generatedID = stm.getGeneratedKeys();
            if(generatedID.next()) {
                this.setID(generatedID.getInt(1));
            }

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

        String sqlDelete = "DELETE FROM carro WHERE id = ?";

        try (PreparedStatement stm = conn.prepareStatement(sqlDelete);) {

            stm.setInt(1, this.getID());

            stm.execute();

        }   catch(SQLException sql_ex) {
            sql_ex.printStackTrace();
        } 

    }

    public void atualiza(Connection conn) {
        String sqlUpdate = "UPDATE carro SET nome = ?, marca = ?, placa = ?, temSeguro = ?, valorSeguro = ?, img_path = ? WHERE id = ?";
        try {
            // Desativa o autocommit se ainda não estiver desativado
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
    
            try (PreparedStatement stm = conn.prepareStatement(sqlUpdate)) {
                stm.setString(1, this.getNome());
                stm.setString(2, this.getMarca());
                stm.setString(3, this.getPlaca());
                stm.setBoolean(4, this.isTemSeguro());
                stm.setInt(5, this.getValorSeguro());
                stm.setString(6, this.getImagem());  // Atualiza a imagem também se necessário
                stm.setInt(7, this.getID());
    
                int rowsAffected = stm.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Atualização do carro realizada com sucesso.");
                    conn.commit();
                } else {
                    System.out.println("Nenhuma linha foi atualizada. Verifique o ID do carro.");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar carro: " + ex.getMessage());
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                System.out.println("Erro ao fazer rollback: " + rollbackEx.getMessage());
            }
        }
    }    

    public void carregar(Connection conn) {
        String sqlSelect = "SELECT * FROM carro WHERE id = ?";

        try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
            stm.setInt(1, this.getID());

            try (ResultSet rs = stm.executeQuery();) {
                if(rs.next()) {
                    this.setNome(rs.getString(2));
                    this.setMarca(rs.getString(3));
                    this.setPlaca(rs.getString(4));
                    this.setTemSeguro(rs.getBoolean(5));
                    this.setValorSeguro(rs.getInt(6));
                    this.setImagem(rs.getString(7));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public int getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(int valorSeguro) {
        this.valorSeguro = valorSeguro;
    }
}

