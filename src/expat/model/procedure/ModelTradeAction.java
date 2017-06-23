package expat.model.procedure;

import expat.model.ModelMaterial;
import expat.model.ModelPlayer;

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

/**
 * Initiates a new ModelTradingAction which represents trading in Model and which distributes materials in the end.
 * Trade types will be:
 *      GeneralTrade 4:1
 *      PlayerToPlayerTrade ?:?
 *      GeneralPortTrade 3:1
 *      SpecificPortTrade 2:1
 * @param type type of Trade
 * @param sender ModelPlayer which initiated the trade.
 */
 public ModelTradeAction(String type,ModelPlayer sender) {
        this.type = type;
        this.sender =sender;
    }


    /**
     * Hands the integer array taken as input from the view over to the ModelPlayer.
     *
     * @param materialResultSender
     */
    public void finishTradeAction(int[] materialResultSender){
        sender.addMaterial(new ModelMaterial(materialResultSender));
    }

    /**
     * Getter for trade type
     *
     *
     * @return String of trade type
     */
    public String getType() {
        return type;
    }

}
