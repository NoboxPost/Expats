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
    ModelPlayer sender;
    ModelPlayer receiver;


    public ModelTradeAction(String type,ModelPlayer sender) {
        this.type = type;
        this.sender =sender;
    }

    public String getType() {
        return type;
    }



    public void finischTradeAction(int[] materialResultSender){
        sender.addMaterial(new ModelMaterial(materialResultSender));
    }
}
