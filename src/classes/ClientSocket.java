package classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import enums.RequestType;

public class ClientSocket {
    
    private Socket clientSocket;
    
    public void start() throws IOException {
        clientSocket = new Socket(Server.ADDRESS, Server.PORT);
    }

    public void doRequest() {

        try (ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {

                // out.writeObject(request);

                User user = new User("User", "Senha");

                Request testRequest = new Request(RequestType.GET_USER, user);

                out.writeObject(testRequest);

                out.flush();

                Request response = (Request) in.readObject();

                User responseUser = (User) response.getParameters()[0];

                System.out.println("Resposta do servidor: " + responseUser.getUser()); 

        } catch( IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

}
