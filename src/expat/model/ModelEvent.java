package expat.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Is responsible for transaction of game events to other clients.
 * <p>
 * created on 20.04.2017
 *
 * @author vanonir
 */
public class ModelEvent implements Serializable {

    private String eventType;
    private String message;
    private int sender = -1;
    private ArrayList<Object> ObjectArray;
    private Object SingleObject;

    public ModelEvent(int sender) {
        this.sender = sender;
    }

    public void setTypeAndAttachObjects(String eventType, ArrayList<Object> objectArrayList){
        this.eventType = eventType;
        this.ObjectArray = objectArrayList;
    }
    public void setTypeAndAttachSingleObject(String eventType, Object object){
        this.eventType = eventType;
        this.SingleObject = object;
    }



    /**
     * For testing, so
     */
    public String eventToString() {
        String outputStirng ="sender: " +sender ;

        if (eventType != null) {
            outputStirng +="\n    eventType: "+eventType;
        }

        if (message != null){
            outputStirng += "\n    message: "+ message;
        }
        return outputStirng;
    }

    public void setEventType(String type) {
        this.eventType = type;
    }

    public String getEventType() {
        return eventType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Object> getObjectArray() {
        return ObjectArray;
    }

    public void setObjectArray(ArrayList<Object> objectArray) {
        ObjectArray = objectArray;
    }

    public Object getSingleObject() {
        return SingleObject;
    }

    public void setSingleObject(Object singleObject) {
        SingleObject = singleObject;
    }

    public int getSender() {
        return sender;
    }
}
