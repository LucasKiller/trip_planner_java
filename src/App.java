
import classes.ClientSocket;
import telas.TelaInicial;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {

        try {

            ClientSocket clientSocket = new ClientSocket();
            clientSocket.start();

            new TelaInicial(clientSocket);

        } catch (IOException ex) {
            System.out.println("Erro ao iniciar o Socket do cliente!");
        }
    }
}