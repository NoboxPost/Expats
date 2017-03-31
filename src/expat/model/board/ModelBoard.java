package expat.model.board;

import expat.model.buildings.ModelBuilding;
import expat.model.ModelRaider;
import expat.model.buildings.ModelBuilding;

import java.util.ArrayList;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ModelBoard {
    private ModelHex[][] hexes;
    private ArrayList<ModelBuilding> buildings;
    private ModelRaider raider;

    public ModelBoard(ModelHex[][] hexes , ArrayList<ModelBuilding> buildings, ModelRaider raider) {
        this.hexes = hexes;
        this.buildings = buildings;
        this.raider = raider;
    }

    public void resourceOnDiceEvent(int diceNumber){
        for(ModelHex[] hexline : hexes){
            for(ModelHex hex : hexline){
                if(hex.getDiceNumber()==diceNumber){ //TODO: check if raided,
                    for(ModelBuilding building: buildings){
                        if(building.isFlanking(hex)){
                            building.giveMaterialToOwner(hex.getMaterial());
                        }
                    }
                }
            }
        }
    }

    public ModelHex[][] getHexes() {
        return hexes;
    }

    public ArrayList<ModelBuilding> getBuildings() {
        return buildings;
    }

    public ModelRaider getRaider() {
        return raider;
    }

    public void changeBuilding(int xCoord,int yCoord){
        for (ModelBuilding building:buildings) {
            if (building.getCoords()[0]==xCoord&&building.getCoords()[1]==yCoord){
                building.changeBuildingType();
            }

        }
    }
}
