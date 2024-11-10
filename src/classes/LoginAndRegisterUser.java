package classes;

import java.sql.Connection;
import java.sql.SQLException;

import entities.User;
import utils.CryptoKeyHandler;

/*
 * Classe para monitoriamento de login de usuario
 * Metodos como: login, registrar
 * Variaveis: isLogged -> diz respeito a condicao atual do usuario (se esta logado ou nao)
 * 
 * Objeto mng com os metodos logUser e registerUser
 */

public class LoginAndRegisterUser {
    
    private User user;

    /*
     * Metodo para loggar o usuario
     */
    public int logUser(Connection conn, User user) {
        
        String pass = user.getPass();

        String criptoPass = CryptoKeyHandler.getCipherPass(pass);

        user.setPass(criptoPass);

        // User userTest = new User(user, criptoPass);

        // try {
        //     ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        //     ObjectOutputStream out = new ObjectOutputStream(byteOut);
        //     out.writeObject(userTest);
        //     out.flush();

        //     byte[] personBytes = byteOut.toByteArray();

        //     this.setUser(new User(personBytes));

        //     System.out.println(this.getUser().getPass());

        // } catch (Exception ex) {
        //     ex.printStackTrace();
        // }

        this.setUser(user);

        this.getUser().carregar(conn);

        if(this.getUser().getID() == 0) {
            // Utilizar de algum dialogo para informar que o login foi MAL SUCEDIDO
            return -1;
        } 

        return 1;

    }

    public int registerUser(Connection conn, User user) {

        String pass = user.getPass();

        String criptoPass = CryptoKeyHandler.getCipherPass(pass);

        user.setPass(criptoPass);

        this.setUser(user);
        if(this.getUser().getID() != 0) {
            // Utilizar de algum dialogo para informar que o registro foi MAL SUCEDIDO
            return -1;
        }

        this.getUser().inserir(conn);
        
        try {
            conn.commit();
        } catch (SQLException ex) {
            System.out.println("Nao foi possivel registrar o usuario! SQLExeption");
        }

        return 1;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}