package expat.server;

import java.io.IOException;
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
public class ServerMain extends Thread{

    private ArrayList<ServerClientThread> serverClientThreads = new ArrayList();


    public void run(){
        ServerSocket mServerSocket = null;
        try {
            mServerSocket = new ServerSocket(4227);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int id = 0;
        System.out.println("Server started");
        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = mServerSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ServerClientThread cliThread = new ServerClientThread(clientSocket, id++, serverClientThreads);
            serverClientThreads.add(cliThread);
            cliThread.start();
        }
    }
}
