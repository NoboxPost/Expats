package expat.model.buildings;

import expat.model.ModelMaterial;
import expat.model.ModelPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gallatib on 28.06.2017.
 */
public class ModelBuildingValidator {

    private ModelPlayer player;
    private String buildingType;
    private ArrayList<ModelBuilding> buildings;
    private ArrayList<ModelConnection> connections;
    private boolean startStage = false;
    private Map<String, ModelMaterial> buildingCostMap = new HashMap<>();

    public ModelBuildingValidator(ModelPlayer player, String buildingType, ArrayList<ModelBuilding> buildings, ArrayList<ModelConnection> connections, boolean startStage) {
        this.player = player;
        this.buildingType = buildingType;
        this.buildings = buildings;
        this.connections = connections;
        this.startStage = startStage;
        addMaterialsToMap();
    }

    private void addMaterialsToMap() {
        buildingCostMap.put("Road", new ModelMaterial(new int[]{1, 0, 0, 1, 0}));
        buildingCostMap.put("Boat", new ModelMaterial(new int[]{0, 0, 0, 1, 1}));
        buildingCostMap.put("Settlement", new ModelMaterial(new int[]{1, 1, 0, 1, 1}));
        buildingCostMap.put("Town", new ModelMaterial(new int[]{0, 2, 3, 0, 0}));
        buildingCostMap.put("Development", new ModelMaterial(new int[]{0, 1, 1, 0, 1}));
    }

    public boolean playerHasEnoughMaterials(String type) {
        if (player.getMaterial().equals(buildingCostMap.get(type))){
            return true;
        }else{
            return false;
        }
    }

    /*
    public boolean validateRoadPlaces(int[] coords, ModelConnection connection) {

        //Todo: loop bei Aufruf starten
        // for (ModelConnection connection :connections) {
            if (connection.checkCoords(coords) && connection.getOwner() == null) {
                    if (checkConnectionForConnection(coords, connection) && playerHasEnoughMaterials(buildingType)) {
                        return true;
                    } else if (startStage && checkSettlementforConnectionToBeBuilt(coords, connection)) {
                        return true;
                    }
            }
            else{return false;}
    }

    public void validateSettlementPlaces(){

    }

    public void validateTownPlaces(){

    }


    public boolean checkConnectionForConnection(int[] coords, ModelConnection connectionToBuild) {
        boolean legalPosition = false;
        int xCoordOfNewConnection = coords[0];
        int yCoordOfNewConnection = coords[1];
        switch (connectionToBuild.getOrientation()) {
            case "straight":
                for (ModelConnection connectionToCheck : connections) {
                    int[] xCoordModulator = new int[]{-3, +3, -3, +3};
                    int[] yCoordModulator = new int[]{-1, -1, +1, +1};
                    if (connectionToCheck.getOwner() == player) {
                        for (int i = 0; i < 4; i++) {
                            if (connectionToCheck.checkCoords(xCoordOfNewConnection + xCoordModulator[i], yCoordOfNewConnection + yCoordModulator[i])) {
                                legalPosition = true;
                            }
                        }
                    }
                }
                break;
            case "up":
                for (ModelConnection connectionToCheck : connections) {
                    int[] xCoordModulator = new int[]{0, +3, -3, 0};
                    int[] yCoordModulator = new int[]{-2, -1, +1, +2};

                    if (connectionToCheck.getOwner() == player) {
                        for (int i = 0; i < 4; i++) {
                            if (connectionToCheck.checkCoords(xCoordOfNewConnection + xCoordModulator[i], yCoordOfNewConnection + yCoordModulator[i])) {
                                legalPosition = true;
                            }
                        }
                    }
                }
                break;
            case "down":
                for (ModelConnection connectionToCheck : connections) {
                    int[] xCoordModulator = new int[]{-3, 0, +3, 0};
                    int[] yCoordModulator = new int[]{-1, -2, +1, +2};
                    if (connectionToCheck.getOwner() == player) {
                        for (int i = 0; i < 4; i++) {
                            if (connectionToCheck.checkCoords(xCoordOfNewConnection + xCoordModulator[i], yCoordOfNewConnection + yCoordModulator[i])) {
                                legalPosition = true;
                            }
                        }
                    }
                }
                break;
        }
        if (legalPosition) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkSettlementforConnectionToBeBuilt(int[] coords, ModelConnection connection) {
        boolean legalPosition = false;
        int xCoordOfNewConnection = coords[0];
        int yCoordOfNewConnection = coords[1];
        switch (connection.getOrientation()) {
            case "straight":
                for (ModelBuilding building : buildings) {
                    int[] xCoordModulator = new int[]{-2, +2};
                    int[] yCoordModulator = new int[]{0, 0};
                    if (building.getOwner() == player) {
                        for (int i = 0; i < 2; i++) {
                            if (building.checkCoords(xCoordOfNewConnection + xCoordModulator[i], yCoordOfNewConnection + yCoordModulator[i])) {
                                legalPosition = true;
                            }
                        }
                    }
                }
                break;
            case "up":
                for (ModelBuilding building : buildings) {
                    int[] xCoordModulator = new int[]{+1, -1};
                    int[] yCoordModulator = new int[]{-1, +1};
                    if (building.getOwner() == player) {
                        for (int i = 0; i < 2; i++) {
                            if (building.checkCoords(xCoordOfNewConnection + xCoordModulator[i], yCoordOfNewConnection + yCoordModulator[i])) {
                                legalPosition = true;
                            }
                        }
                    }
                }
                break;
            case "down":
                for (ModelBuilding building : buildings) {
                    int[] xCoordModulator = new int[]{-1, +1};
                    int[] yCoordModulator = new int[]{-1, +1};
                    if (building.getOwner() == player) {
                        for (int i = 0; i < 2; i++) {
                            if (building.checkCoords(xCoordOfNewConnection + xCoordModulator[i], yCoordOfNewConnection + yCoordModulator[i])) {
                                legalPosition = true;
                            }
                        }
                    }
                }
        }
        if (legalPosition) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkBuildingForBuilding(int[] coords) {
        boolean legalPosition = true;
        int xCoordOfNewBuilding = coords[0];
        int yCoordOfNewBuilding = coords[1];
        for (ModelBuilding building : buildings) {
            int[] xCoordModulator = new int[]{-2, +2, +2, -2, -4, +4};
            int[] yCoordModulator = new int[]{-2, -2, +2, +2, 0, 0};
            if (building.getOwner() != null) {
                for (int i = 0; i < 6; i++) {
                    if (building.checkCoords(xCoordOfNewBuilding + xCoordModulator[i], yCoordOfNewBuilding + yCoordModulator[i])) {
                        legalPosition = false;
                    }
                }
            }
        }

        if (legalPosition) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkConnectionForBuilding(int[] coords) {
        boolean legalPosition = false;
        int xCoordOfNewBuilding = coords[0];
        int yCoordOfNewBuilding = coords[1];
        if (xCoordOfNewBuilding % 6 == 0) {
            for (ModelConnection connection : connections) {
                int[] xCoordModulator = new int[]{-2, +1, +1};
                int[] yCoordModulator = new int[]{0, -1, +1};
                if (connection.getOwner() == player) {
                    for (int i = 0; i < 3; i++) {
                        if (connection.checkCoords(xCoordOfNewBuilding + xCoordModulator[i], yCoordOfNewBuilding + yCoordModulator[i])) {
                            legalPosition = true;
                        }
                    }
                }
            }
        } else {
            for (ModelConnection connection : connections) {
                int[] xCoordModulator = new int[]{+2, -1, -1};
                int[] yCoordModulator = new int[]{0, -1, +1};
                if (connection.getOwner() == player) {
                    for (int i = 0; i < 3; i++) {
                        if (connection.checkCoords(xCoordOfNewBuilding + xCoordModulator[i], yCoordOfNewBuilding + yCoordModulator[i])) {
                            legalPosition = true;
                        }
                    }
                }
            }
        }
        if (legalPosition) {
            return true;
        } else {
            return false;
        }
    }
    */

}

