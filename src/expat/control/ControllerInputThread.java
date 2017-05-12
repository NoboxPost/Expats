package expat.control;

import expat.model.ModelEvent;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * is responsible for
 * <p>
 * created on 20.04.2017
 *
 * @author vanonir
 */
public class ControllerInputThread extends Thread {
    ObjectInputStream in;
    ControllerMainStage controllerMainStage;

    public ControllerInputThread( ObjectInputStream in,ControllerMainStage controllerMainStage){
        this.in = in;
        this.controllerMainStage = controllerMainStage;
    }

    @Override
    public void run() {
        System.out.println("InputListener active");
        while (true){
            try {
                ModelEvent modelEvent = (ModelEvent)in.readObject();
                System.out.println(modelEvent.getEventType());
                controllerMainStage.eventHandler(modelEvent);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
