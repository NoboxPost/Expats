package expat.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * is responsible for
 * <p>
 * created on 20.04.2017
 *
 * @author vanonir
 */
public class ServerMain {

    private ArrayList<ServerClientThread> serverClientThreads = new ArrayList();


    public ServerMain() throws Exception{
        ServerSocket mServerSocket = new ServerSocket(4227);
        int id = 0;
        System.out.println("Server started");
        while (true){
            Socket clientSocket = mServerSocket.accept();
            ServerClientThread cliThread = new ServerClientThread(clientSocket, id++, serverClientThreads);
            serverClientThreads.add(cliThread);
            cliThread.start();


        }

    }

    public static void main(String[] args) throws Exception {
        new ServerMain();
    }
}
