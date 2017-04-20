package expat.server;


import expat.model.ModelEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * is responsible for
 * <p>
 * created on 20.04.2017
 *
 * @author vanonir
 */
public class ServerClientThread extends Thread {

    private Socket clientSocket;
    private ArrayList<ServerClientThread> serverClientThreats;
    int id;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ServerClientThread(Socket clientSocket, int i, ArrayList<ServerClientThread> serverClientThreads) {
        this.clientSocket = clientSocket;
        this.id = i;
        this.serverClientThreats = serverClientThreads;

    }

    @Override
    public void run() {
        System.out.println("Socket with ID "+id+" created");
        try {
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());

            while (true){
                ModelEvent modelEvent = (ModelEvent)in.readObject();
                System.out.println(modelEvent.eventToString());
                if (modelEvent.getEventType().equals("getID")){
                    modelEvent.setMessage(""+id);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
