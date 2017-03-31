package expat.model.buildings;

import expat.model.board.ModelHex;
import expat.model.ModelMaterial;
import expat.model.ModelPlayer;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ModelBuilding {
    private ModelHex[] neighbours = new ModelHex[3];
    private ModelMaterial material;
    private int winPoints;
    protected ModelPlayer owner;



    public boolean isFlanking(ModelHex hexNeighbour){

        for (ModelHex hex: neighbours){
            if (hex.equals(hexNeighbour)){//TODO: Test if equals realy works. Performance?
                return true;
            }
        }
        return false;
    }
    public void addNeighbour(ModelHex hex1,ModelHex hex2, ModelHex hex3){
        boolean isAllreadySet = false;
        for (ModelHex hex: neighbours){

        }
        neighbours[0] = hex1;
        neighbours[1] = hex2;
        neighbours[2] = hex3;
    }
}
