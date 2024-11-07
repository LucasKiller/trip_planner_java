package classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import enums.RequestType;
import utils.ConnectDB;
import utils.CryptoKeyHandler;
import utils.DatabaseSetup;
import utils.Router;

public class Server {

    public static final String ADDRESS = "127.0.0.1";
    public static final int PORT = 4000;
    private ServerSocket serverSocket;
    private volatile boolean serverUp = true;

    public static Connection conn = null;

    public void start() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado: " + ADDRESS + ":" + PORT);
        System.out.println("Para finalizar o servidor, digite 'exit'.");

        new Thread(() -> listenForShutdownCommand()).start();

        clientConnectionLoop();
    }

    private void clientConnectionLoop() {
        System.out.println("Aguardando conexao...");

        try {
            while (serverUp) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleRequest(clientSocket)).start();
            }
        } catch (IOException ex) {
            if (serverUp) {  
                ex.printStackTrace();
            }
        } finally {
            stopDBConnection();
        }
    }

    private void handleRequest(Socket clientSocket) {
        System.out.println("Cliente conectado: " + clientSocket.getRemoteSocketAddress());
        boolean isRunning = true;

        try (ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

            while (isRunning) {
                Request request = (Request) in.readObject();
                System.out.println("Recebido de " + clientSocket.getRemoteSocketAddress() + " : " + request.getType());

                if (request.getType() == RequestType.CLOSE_CONNECTION) {
                    isRunning = false;
                    System.out.println("Finalizando a conexão do cliente " + clientSocket.getRemoteSocketAddress() + " com o servidor!");
                    continue;
                }

                Response res = Router.getResponse(request, conn);
                out.writeObject(res);
                out.flush();
            }

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void listenForShutdownCommand() {
        Scanner sc = new Scanner(System.in);
        while (serverUp) {
            String command = sc.nextLine();
            if (command.trim().equalsIgnoreCase("exit")) {
                serverUp = false;
                try {
                    serverSocket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            }
        }
        sc.close();
    }

    private void stopDBConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            System.out.println("Conexao com o banco de dados finalizada!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String driver = "com.mysql.cj.jdbc.Driver";

        try {
            CryptoKeyHandler.generateKey();
            Class.forName(driver);
            conn = ConnectDB.conectar();
            conn.setAutoCommit(false);
            DatabaseSetup.executeInitialSQL(conn);

            Server server = new Server();
            server.start();

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não encontrado!");
        } catch (SQLException sql_ex) {
            System.out.println("Não foi possível realizar a conexão ao server: " + sql_ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro do arquivo do banco de dados ou do socket do cliente!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Servidor finalizado");
    }
}
