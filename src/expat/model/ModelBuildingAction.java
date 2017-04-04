package expat.model;

import expat.model.buildings.ModelBuilding;
import expat.model.buildings.ModelConnection;

import java.util.ArrayList;

/**
 * Created by Rino on 01.04.2017.
 * <p>
 * Delivers all methods for the app, so a player can build new Buildings and connections depending on his Materials and aviable building spots.
 */
public class ModelBuildingAction {
    private ModelPlayer player;
    private ModelMaterial materials;
    private String buildingType;
    ArrayList<ModelBuilding> buildings;
    ArrayList<ModelConnection> connections;

    public ModelBuildingAction(ModelPlayer player, String buildingType, ArrayList<ModelBuilding> buildings, ArrayList<ModelConnection> connections) {
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
    public boolean createBuilding(int[] coords, String type) {
        if (type.equals("Connection") && buildingType.equals("Road")) {
            for (ModelConnection connection :
                    connections) {
                if (connection.getCoords()[0] == coords[0] && connection.getCoords()[1] == coords[1]) {
                    if (connection.getOwner() == null) {
                        buildingCost(buildingType);
                        connection.buildRoad(buildingType, player);
                        return true;
                    }
                    return false;
                }
            }
        } else if (type.equals("Building")) {
            for (ModelBuilding building :
                    buildings) {
                if (building.getCoords()[0] == coords[0] && building.getCoords()[1] == coords[1]) {
                    if (buildingType.equals("Town")&&building.getOwner()==player) {
                        buildingCost(buildingType);
                        building.buildTown(player);
                        return true;
                    } else if (buildingType.equals("Settlement")&&building.getOwner()==null) {
                        buildingCost(buildingType);
                        building.buildSettlement(player);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void buildingCost (String type){
        ModelMaterial modelMaterial;
        switch(type){
            case "Road":
                player.reduceMaterial(modelMaterial = new ModelMaterial(new int[]{1,0,0,1,0}));
                break;
            case "Boat":
                player.reduceMaterial(modelMaterial = new ModelMaterial(new int[]{0,0,0,1,1}));
                break;
            case "Settlement":
                player.reduceMaterial(modelMaterial = new ModelMaterial(new int[]{1,1,0,1,1}));
                break;
            case "Town":
                player.reduceMaterial(modelMaterial = new ModelMaterial(new int[]{0,2,3,0,0}));
                break;
            case "Development":
                player.reduceMaterial(modelMaterial = new ModelMaterial(new int[]{0,1,1,0,1}));
            default:
                modelMaterial = null;
        }
    }

    public String getBuildingType() {
        return buildingType;
    }
}
