package classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import enums.RequestType;

// Entender como continuar essa implementacao!

public class Server {

    public static final String ADDRESS = "127.0.0.1";
    public static final int PORT = 4000;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    public void start() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado: " + ADDRESS + ":" + PORT);
        clientConnectionLoop();
    }

    private void clientConnectionLoop() {
        System.out.println("Aguardando conexao...");

        try {
            do {
            clientSocket = serverSocket.accept();
            new Thread(() -> handleRequest(clientSocket)).start();
        } while(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void handleRequest(Socket clientSocket)  {

        try (ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());){
                
            Request request = (Request) in.readObject();

            System.out.println("Recebido: " + request.getType() + ", " + request.getParameters());
            
            // Envia um objeto de volta para o cliente -> Aqui é onde vao ser mapeadas as rotas corretamente
            Request response = new Request(RequestType.GET_USER, request.getParameters());
            out.writeObject(response);
            out.flush();

        } catch(IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } 

    }

    public static void main(String[] args) {
        
        try {
            Server server = new Server();
            server.start();
        } catch(IOException ex) {
            ex.printStackTrace();
        }

    }

}
