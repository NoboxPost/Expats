package expat.model.buildings;

import expat.model.board.ModelHex;
import expat.model.ModelMaterial;
import expat.model.ModelPlayer;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ModelBuilding {
    private ModelHex[] Neighbours;
    private java.util.List<ModelMaterial> buildingCosts;
    private int winPoints;
    protected ModelPlayer owner;



    public boolean isFlanking(ModelHex hexNeighbour){

        for (ModelHex hex:Neighbours){
            if (hex.equals(hexNeighbour)){//TODO: Test if equals realy works. Performance?
                return true;
            }
        }
        return false;
    }
}
