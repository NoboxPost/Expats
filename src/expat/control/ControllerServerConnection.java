package expat.control;

import expat.model.ModelEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * is responsible for
 * <p>
 * created on 20.04.2017
 *
 * @author vanonir
 */
public class ControllerServerConnection {

    private Socket socket;
    private ControllerMainStage controllerMainStage;
    ObjectOutputStream out;
    ObjectInputStream in;
    ControllerInputThread inputThread;


    public ControllerServerConnection(ControllerMainStage controllerMainStage) throws IOException {
        this.controllerMainStage = controllerMainStage;
         socket= new Socket("localhost",4227);
        System.out.println("connected to Server");
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        inputThread = new ControllerInputThread(in,controllerMainStage);
        inputThread.start();

    }
    public void sendEvent(ModelEvent modelEvent){
        try {
            out.writeObject(modelEvent);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getConnectionID() {
        ModelEvent getID = new ModelEvent(-1);
        getID.setEventType("getID");
        sendEvent(getID);
        boolean waiting = true;
        int returnint = 0;
        while (waiting){

            try {
                getID = (ModelEvent) in.readObject();
                if (!getID.getMessage().isEmpty()){
                    returnint = Integer.parseInt(getID.getMessage());
                    getID = null;
                    waiting = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        System.out.println(returnint);
        return returnint;
    }
}
