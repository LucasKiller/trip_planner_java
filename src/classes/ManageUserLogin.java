package classes;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;

import utils.CryptoKeyHandler;

/*
 * Classe para monitoriamento de login de usuario
 * Metodos como: login, registrar
 * Variaveis: isLogged -> diz respeito a condicao atual do usuario (se esta logado ou nao)
 * 
 * Objeto mng com os metodos logUser e registerUser
 */

public class ManageUserLogin {
    
    private boolean isLogged = false;
    private User user;

    /*
     * Metodo para loggar o usuario
     */
    public int logUser(Connection conn, String user, String pass) {

        String criptoPass = CryptoKeyHandler.getCipherPass(pass);

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

        this.setUser(new User(user, criptoPass));


        this.getUser().carregar(conn);

        if(this.getUser().getID() == 0) {
            // Utilizar de algum dialogo para informar que o login foi MAL SUCEDIDO
            return -1;
        } 

        this.setLogged(true); // Informar que o login foi bem sucedido e trocar telas
        return 1;

    }

    public int registerUser(Connection conn, String nome, String user, String pass) {

        String criptoPass = CryptoKeyHandler.getCipherPass(pass);

        this.setUser(new User(nome, user, criptoPass));
        this.getUser().carregar(conn);
        if(this.getUser().getID() != 0) {
            // Utilizar de algum dialogo para informar que o registro foi MAL SUCEDIDO
            return -1;
        }

        this.getUser().inserir(conn);
        this.setLogged(true);
        return 1;
    }

    public boolean getIsLogged() {
        return isLogged;
    }

    private void setLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}