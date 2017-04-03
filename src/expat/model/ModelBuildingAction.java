package expat.model;

import expat.model.buildings.ModelBuilding;
import expat.model.buildings.ModelConnection;

import java.util.ArrayList;

/**
 * Created by Rino on 01.04.2017.
 *
 * Delivers all methods for the app, so a player can build new Buildings and connections depending on his Materials and aviable building spots.
 */
public class ModelBuildingAction {
    private ModelPlayer player;
    private ModelMaterial materials;
    private String buildingType;
    ArrayList<ModelBuilding> buildings;
    ArrayList<ModelConnection> connections;

    public ModelBuildingAction(ModelPlayer player, String buildingType,ArrayList<ModelBuilding> buildings, ArrayList<ModelConnection> connections) {
        this.player = player;
        this.materials = player.getMaterial();
        this.buildingType = buildingType;
        this.buildings = buildings;
        this.connections = connections;
        // TODO: depending on buildingType, show possible locations for types
        // TODO: switch case for building and COST!, COST is not handled by building, so need to do i here.
    }

    /**
     * Takes building coords and finishes the building process.
     *
     * @param coords
     */
    public boolean createBuilding(int[]coords,String type){
        if (type.equals("Road")&&buildingType.equals("Road")){
            for (ModelConnection connection :
                    connections) {
                if (connection.getCoords()[0]==coords[0]&&connection.getCoords()[1]==coords[1]){
                    connection.buildRoad(buildingType,player);
                    return true;
                }
            }
        }
        return false;
    }
}
