
import classes.ClientSocket;
import telas.TelaLinguagem;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {

        try {

            ClientSocket clientSocket = new ClientSocket();
            clientSocket.start();

            new TelaLinguagem(clientSocket);

        } catch (IOException ex) {
            System.out.println("Erro ao iniciar o Socket do cliente!");
        }
    }
}