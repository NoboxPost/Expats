package expat.model.board;

import expat.model.buildings.ModelBuildingAction;
import expat.model.ModelPlayer;
import expat.model.buildings.ModelBuilding;
import expat.model.ModelRaider;
import expat.model.buildings.ModelConnection;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * is responsible for the actual game board that includes hexes, buildings and a raider
 * <p>
 * created on 22.03.2017
 *
 * @author vanonir
 */
public class ModelBoard implements Serializable {
    private ModelHex[][] hexes;
    private ArrayList<ModelBuilding> buildings;
    private ArrayList<ModelConnection> connections;
    private ModelRaider raider;
    private ModelBuildingAction buildingAction;

    public ModelBoard(ModelHex[][] hexes, ArrayList<ModelBuilding> buildings, ArrayList<ModelConnection> connections, ModelRaider raider) {
        this.hexes = hexes;
        this.buildings = buildings;
        this.raider = raider;
        this.connections = connections;
    }

    /**
     * 1. checks all the hexes on board an compares them to the rolled dice number
     * 2. checks all the buildings flanking those specific hexes and checks whether the hex is raided or not
     * 3. calls the building class to distribute the diced materials
     *
     * @param diceNumber
     */
    public void resourceOnDiceEvent(int diceNumber) {
        for (ModelHex[] hexLine : hexes) {
            for (ModelHex hex : hexLine) {
                if (hex.getDiceNumber() == diceNumber) { //TODO: check if raided,
                    for (ModelBuilding building : buildings) {
                        if (building.isFlanking(hex) && !hex.getRaided()) {
                            building.giveMaterialToOwner(hex.getMaterial());
                        }
                    }
                }
            }
        }
    }


    /**
     * Creates a new ModelBuildingAction with give building type and for given owner. No materials will be checked and reduced
     *
     * @param type type of Building.
     * @param newOwner ModelPlayer which will be owner of new building.
     */
    public void firstBuildingAction(String type, ModelPlayer newOwner) {
        buildingAction = new ModelBuildingAction(newOwner,type,buildings,connections,true);
    }

    /**
     * Creates a new ModelBuildingAction with give building type and for given owner. Materials will be checked and reduced
     *
     * @param type Building type
     * @param newOwner ModelPlayer which will be owner of new building.
     */
    public void newBuildingAction(String type, ModelPlayer newOwner) {
        buildingAction = new ModelBuildingAction(newOwner, type, buildings, connections);

    }

    /**
     * Ends BuildingAction if given coordinates are valid and types of coordinate and type given by constructor match.
     *
     * @param coords Int array 1. pos = x coordinate 2. pos = y coordinate
     * @param type building type
     * @return true if building is valid.
     */
    public boolean finishBuildingAction(int[] coords, String type) {


        if (buildingAction != null) {
            if (buildingAction.createBuilding(coords, type)) {
                buildingAction = null;
                return true;
            }
        }
        return false;
    }

    /**
     * reference to BuildingAction is reset if building process is aborted.
     */
    public void abortBuildingAction() {
        buildingAction = null;
    }

    /**
     * counts all settlements and towns for a given player
     *
     * @param player owner of buildings
     * @return sum cf buildings.
     */
    public int countBuildingsOwned(ModelPlayer player){
        int counter = 0;
        for (ModelBuilding building:buildings) {
            if (building.getOwner()==player){
                counter+=1;
            }
        }
        return counter;
    }

    /**
     * counts all roads and ships for a given player
     *
     * @param player onwer of connections
     * @return sum of connections.
     */
    public int countConnectionsOwned(ModelPlayer player){
        int counter = 0;
        for (ModelConnection connection:connections) {
            if (connection.getOwner()==player){
                counter+=1;
            }
        }
        return counter;
    }
    public ArrayList<ModelBuilding> getAllBuildingsBlockedByRaider(){
        ArrayList<ModelBuilding> blockedByRaider = new ArrayList<>();
        ModelHex blockedHex = raider.getRaiderHex();
        for (ModelBuilding building: buildings) {
            if (building.checkHex(blockedHex)&&!building.getType().equals("empty")){
                blockedByRaider.add(building);
            }
        }
        return blockedByRaider;
    }



    /**
     *sets Raider as active so he can be moved after all players have dropped their materials if they got more than 7.
     */
    public void activateRaider() {
        raider.setAllowMovement(true);
    }


    /**
     * getter
     *
     * @return
     */
    public ModelHex[][] getHexes() {
        return hexes;
    }

    /**
     * getter
     *
     * @return
     */
    public ArrayList<ModelBuilding> getBuildings() {
        return buildings;
    }

    /**
     * getter
     *
     * @return
     */
    public ModelRaider getRaider() {
        return raider;
    }


    /**
     * getter
     *
     * @return
     */
    public ArrayList<ModelConnection> getConnections() {
        return connections;
    }


    public ModelBuildingAction getBuildingAction() {
        return buildingAction;
    }
}
