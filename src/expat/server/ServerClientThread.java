package expat.server;


import expat.model.ModelEvent;

import java.io.*;
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
    private ArrayList<ServerClientThread> serverClientThreads;
    int id;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ServerClientThread(Socket clientSocket, int i, ArrayList<ServerClientThread> serverClientThreads) {
        this.clientSocket = clientSocket;
        this.id = i;
        this.serverClientThreads = serverClientThreads;

    }

    @Override
    public void run() {
        System.out.println("Socket with ID " + id + " created");
        try {
//            BufferedInputStream bufferedInputStream = new BufferedInputStream(clientSocket.getInputStream());
//            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(clientSocket.getOutputStream());

            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());

            while (true) {
                ModelEvent modelEvent = (ModelEvent) in.readObject();
                System.out.println(modelEvent.eventToString());
                if (modelEvent.getEventType().equals("getID")) {
                    modelEvent.setMessage("" + id);
                    out.writeObject(modelEvent);
                    out.flush();
                    out.reset();
                } else {
                    for (ServerClientThread thread : serverClientThreads) {
                        thread.send(modelEvent);

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void send(ModelEvent event) {
        synchronized (this) {
            try {
                out.writeObject(event);
                out.flush();
                out.reset();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

