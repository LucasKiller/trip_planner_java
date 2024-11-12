package classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import enums.RequestType;

public class ClientSocket {
    
    private Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    
    public void start() throws IOException {
        clientSocket = new Socket(Server.ADDRESS, Server.PORT);

        out = new ObjectOutputStream(clientSocket.getOutputStream());

        in = new ObjectInputStream(clientSocket.getInputStream());
    }

    public Response doRequest(Request request) {

        Response response = null;

        try {
                out.writeObject(request);
                out.flush();

                if(request.getType() == RequestType.CLOSE_CONNECTION) {

                    in.close();
                    out.close();
                    return null;

                }

                response = (Response) in.readObject();

                System.out.println("Recebido do servidor: " + response.getType());

        } catch( IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return response;

    }

}
