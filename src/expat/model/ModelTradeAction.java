package expat.model;

/**
 * is responsible for
 * <p>
 * created on 05.04.2017
 *
 * @author vanonir
 */
public class ModelTradeAction {
    String type;
    ModelMaterial materialOffer;
    ModelMaterial materialDemand;
    ModelPlayer Sender;
    ModelPlayer Receiver;


    public ModelTradeAction(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
