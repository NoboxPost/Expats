package expat.model.board;

import expat.model.buildings.ModelBuilding;
import expat.model.ModelRaider;
import expat.model.buildings.ModelBuilding;
import expat.model.buildings.ModelConnection;

import java.util.ArrayList;

/**
 * is responsible for the actual game board that includes hexes, buildings and a raider
 * <p>
 * created on 22.03.2017
 *
 * @author vanonir
 */
public class ModelBoard {
    private ModelHex[][] hexes;
    private ArrayList<ModelBuilding> buildings;
    private ArrayList<ModelConnection> modelConnections;
    private ModelRaider raider;

    public ModelBoard(ModelHex[][] hexes , ArrayList<ModelBuilding> buildings,ArrayList<ModelConnection>connections, ModelRaider raider) {
        this.hexes = hexes;
        this.buildings = buildings;
        this.raider = raider;
        this.modelConnections= connections;
    }

    /**
     * 1. checks all the hexes on board an compares them to the rolled dice number
     * 2. checks all the buildings flanking those specific hexes and checks whether the hex is raided or not
     * 3. calls the building class to distribute the diced materials
     *
     * @param diceNumber
     */
    public void resourceOnDiceEvent(int diceNumber){
        for(ModelHex[] hexLine : hexes){
            for(ModelHex hex : hexLine){
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


    public ArrayList<ModelConnection> getConnections() {
        return modelConnections;
    }
}
