package expat.model.buildings;

import expat.model.ModelMaterial;
import expat.model.ModelPlayer;
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
    private String buildingType;
    private ArrayList<ModelBuilding> buildings;
    private ArrayList<ModelConnection> connections;
    private boolean startStage = false;

    /**
     * to be used during game, materials will be checked alongside with connection and building positions.
     *
     * @param player
     * @param buildingType
     * @param buildings
     * @param connections
     */
    public ModelBuildingAction(ModelPlayer player, String buildingType, ArrayList<ModelBuilding> buildings, ArrayList<ModelConnection> connections) {
        this.player = player;
        this.buildingType = buildingType;
        this.buildings = buildings;
        this.connections = connections;
        // TODO: depending on buildingType, show possible locations for types
        // TODO: switch case and COST!, COST is not handled by building, so need to do i here.
    }

    /**
     * To be used at Start of Game,
     *
     * @param player
     * @param buildingType
     * @param buildings
     * @param connections
     * @param startStage   if true, no Materials will be checked and Settlements can be built independent from roads.
     */
    public ModelBuildingAction(ModelPlayer player, String buildingType, ArrayList<ModelBuilding> buildings, ArrayList<ModelConnection> connections, boolean startStage) {
        this.player = player;
        this.buildingType = buildingType;
        this.buildings = buildings;
        this.connections = connections;
        this.startStage = startStage;
    }

    /**
     * Takes building coords and finishes the building process. Don't touch!
     *
     * @param coords
     */
    public boolean createBuilding(int[] coords, String type) {
        if (type.equals("Connection") && buildingType.equals("Road")) {
            for (ModelConnection connection :
                    connections) {
                if (connection.checkCoords(coords)) {
                    if (connection.getOwner() == null) {
                        if (checkConnectionForConnection(coords, connection) && buildingCost(buildingType)) {
                            connection.buildRoad(buildingType, player);
                            return true;
                        } else if (startStage && checkSettlementforConnectionToBeBuilt(coords, connection)) {
                            connection.buildRoad(buildingType, player);
                        }
                        return false;
                    }
                }
            }
        } else if (type.equals("Building")) {
            for (ModelBuilding building : buildings) {
                if (building.checkCoords(coords)) {
                    if ((buildingType.equals("Town") && building.getOwner() == player) && building.getType().equals("Settlement")) {
                        if (buildingCost(buildingType)) {
                            building.buildTown(player);
                            return true;
                        }
                        return false;
                    } else if (buildingType.equals("Settlement") && building.getOwner() == null) {

                        if ((checkConnectionForBuilding(building.getCoords()) && checkBuildingForBuilding(coords))) {
                            if (buildingCost(buildingType)) {
                                building.buildSettlement(player);
                                return true;
                            }
                            return false;
                        } else if (startStage && checkBuildingForBuilding(coords)) {
                            building.buildSettlement(player);
                            return true;
                        }

                    }
                }
            }
        }
        return false;
    }

    /**
     * Method stores material costs for a building action to be done.
     *
     * @param type
     * @return
     */
    public boolean buildingCost(String type) {
        switch (type) {
            case "Road":
                if (player.reduceMaterial(new ModelMaterial(new int[]{1, 0, 0, 1, 0}))) {
                    return true;
                }
                break;
            case "Boat":
                if (player.reduceMaterial(new ModelMaterial(new int[]{0, 0, 0, 1, 1}))) {
                    return true;
                }
                break;
            case "Settlement":
                if (player.reduceMaterial(new ModelMaterial(new int[]{1, 1, 0, 1, 1}))) {
                    return true;
                }
                break;
            case "Town":
                if (player.reduceMaterial(new ModelMaterial(new int[]{0, 2, 3, 0, 0}))) {
                    return true;
                }
                break;
            case "Development":
                if (player.reduceMaterial(new ModelMaterial(new int[]{0, 1, 1, 0, 1}))) {
                    return true;
                }
                break;
            default:
                return false;

        }
        return false;
    }


    /**
     * checks if there is a connection in neighbourhood with the same owner.
     *
     * @param coords            coordinates of connection to be built.
     * @param connectionToBuild empty connection to be convertet to built connection.
     * @return true, if there is a neighbouring connection of same player
     */
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

    /**
     * @param coords
     * @param connection
     * @return
     */
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


    /**
     * Check's according to given coordinates if there is a building at next junction, so building would be forbidden.
     *
     * @param coords int array, first position represents x coordinate, second position represents y coordinate.
     * @return true if placement of building is allowed, false if there is a building, at next junction.
     */
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

    /**
     * Checks if there is a neighbouring connection, so a building can be placed.
     *
     * @param coords int array with coordinates of the building to be built.
     * @return true if there is a ascending connection, else false.
     */
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

}
