package expat.model.procedure;

import expat.model.ModelPlayer;
import expat.model.board.ModelBoard;
import expat.model.buildings.ModelBuilding;
import expat.model.buildings.ModelConnection;

import java.util.ArrayList;

/**
 * Created by bkope on 03.07.2017.
 */
public class ModelMainGameBuildingListCrawler {
    private ArrayList<ModelBuilding> allBuildings;
    private ArrayList<ModelConnection> allConnections;
    private ArrayList<ModelBuilding> playerBuildings;
    private ArrayList<ModelConnection> playerConnections;
    private ModelBoard modelBoard;

    public ModelMainGameBuildingListCrawler(ModelBoard modelBoard) {
        this.modelBoard = modelBoard;
        allBuildings = modelBoard.getBuildings();
        allConnections = modelBoard.getConnections();
    }

    private void generatePlayerBuildingsArray(ModelPlayer modelPlayer) {
        playerBuildings = new ArrayList<>();

        for (ModelBuilding modelBuilding : allBuildings) {
            if (modelBuilding.getOwner() == modelPlayer) {
                playerBuildings.add(modelBuilding);
            }
        }
    }

    private void generatePlayerConnectionsArray(ModelPlayer modelPlayer) {
        playerConnections = new ArrayList<>();

        for (ModelConnection modelConnection : allConnections) {
            if (modelConnection.getOwner() == modelPlayer) {
                playerConnections.add(modelConnection);
            }
        }
    }

    public ArrayList<ModelBuilding> settlementsAPlayerCouldBuild(ModelPlayer currentPlayer) {
        ArrayList<ModelBuilding> settlementsAPlayerCouldBuild = new ArrayList<>();

        for (ModelBuilding modelBuilding : allBuildings) {
            if (modelBuilding.getOwner() == null && hasAPlayerConnectionNeighbouring(modelBuilding, currentPlayer) && !hasABuildingNeighbouring(modelBuilding)) {
                settlementsAPlayerCouldBuild.add(modelBuilding);
            }
        }

        return settlementsAPlayerCouldBuild;
    }

    private boolean hasABuildingNeighbouring(ModelBuilding modelBuilding) {
        boolean hasABuildingNeighbouring = false;
        int xCoordOfModelBuilding = modelBuilding.getCoords()[0];
        int yCoordOfModelBuilding = modelBuilding.getCoords()[1];

        for (ModelBuilding buidlingToCheck : allBuildings) {

            int[] xCoordModulator = new int[]{-2, +2, +2, -2, -4, +4};
            int[] yCoordModulator = new int[]{-2, -2, +2, +2, 0, 0};


            for (int i = 0; i < 6; i++) {
                if (buidlingToCheck.getOwner()!=null && buidlingToCheck.checkCoords(xCoordOfModelBuilding + xCoordModulator[i],yCoordOfModelBuilding + yCoordModulator[i])){
                    hasABuildingNeighbouring = true;
                }
            }
        }

        return hasABuildingNeighbouring;
    }

    private boolean hasAPlayerConnectionNeighbouring(ModelBuilding modelBuilding, ModelPlayer currentPlayer) {
        boolean hasAPlayerConnectionNeighbouring = false;
        int xCoordOfNewBuilding = modelBuilding.getCoords()[0];
        int yCoordOfNewBuilding = modelBuilding.getCoords()[1];

        int[] xCoordModulator;
        int[] yCoordModulator;

        if (xCoordOfNewBuilding % 6 == 0) {
            xCoordModulator = new int[]{-2, +1, +1};
            yCoordModulator = new int[]{0, -1, +1};

        } else {
            xCoordModulator = new int[]{+2, -1, -1};
            yCoordModulator = new int[]{0, -1, +1};
        }

        hasAPlayerConnectionNeighbouring = thereIsOnePlayerConnectionNeighbouring(currentPlayer, xCoordOfNewBuilding, yCoordOfNewBuilding, xCoordModulator, yCoordModulator);

        return hasAPlayerConnectionNeighbouring;
    }

    private boolean thereIsOnePlayerConnectionNeighbouring(ModelPlayer currentPlayer, int xCoordOfNewBuilding, int yCoordOfNewBuilding, int[] xCoordModulator, int[] yCoordModulator){
        boolean thereIsOnePlayerConnectionNeighbouring = false;

        for (ModelConnection connection : allConnections) {
            if (connection.getOwner() == currentPlayer) {
                for (int i = 0; i < 3; i++) {
                    if (connection.checkCoords(xCoordOfNewBuilding + xCoordModulator[i], yCoordOfNewBuilding + yCoordModulator[i])) {
                        thereIsOnePlayerConnectionNeighbouring = true;
                    }
                }
            }
        }
        return thereIsOnePlayerConnectionNeighbouring;
    }

    public ArrayList<ModelBuilding> townsAPlayerCouldBuild(ModelPlayer currentplayer) {
        ArrayList<ModelBuilding> townsAPlayerCouldBuild = new ArrayList<>();
        generatePlayerBuildingsArray(currentplayer);

        for(ModelBuilding playerBuilding : playerBuildings){
            if(playerBuilding.getType().equals("Settlement")){
                townsAPlayerCouldBuild.add(playerBuilding);
            }
        }

        return townsAPlayerCouldBuild;
    }

    public ArrayList<ModelConnection> roadsAPlayerCouldBuild(ModelPlayer currentPlayer) {
        ArrayList<ModelConnection> connectionsAPlayerCouldBuild = new ArrayList<>();
        generatePlayerConnectionsArray(currentPlayer);

        int[] xCoordModulator;
        int[] yCoordModulator;

        for (ModelConnection playerConnection : playerConnections) {
            int xCoordOfPlayerConnection = playerConnection.getCoords()[0];
            int yCoordOfPlayerConnection = playerConnection.getCoords()[1];

            switch (playerConnection.getOrientation()) {
                case "straight":
                    xCoordModulator = new int[]{-3, +3, -3, +3};
                    yCoordModulator = new int[]{-1, -1, +1, +1};
                    break;

                case "up":
                    xCoordModulator = new int[]{0, +3, -3, 0};
                    yCoordModulator = new int[]{-2, -1, +1, +2};
                    break;

                case "down":
                    xCoordModulator = new int[]{-3, 0, +3, 0};
                    yCoordModulator = new int[]{-1, -2, +1, +2};
                    break;
                default:
                    xCoordModulator = new int[]{0, 0, 0, 0};
                    yCoordModulator = new int[]{0, 0, 0, 0};
                    break;
            }

            connectionsAPlayerCouldBuild.addAll(neighbouringConnectionsToAConnection(xCoordOfPlayerConnection, yCoordOfPlayerConnection, xCoordModulator, yCoordModulator));

        }

        return connectionsAPlayerCouldBuild;
    }

    private ArrayList<ModelConnection> neighbouringConnectionsToAConnection(int xCoordOfPlayerConnection, int yCoordOfPlayerConnection, int[] xCoordModulator, int[] yCoordModulator) {
        ArrayList<ModelConnection> neighbouringConnectionsToAConnection = new ArrayList<>();

        for (ModelConnection connectionToCheck : allConnections) {
            for (int i = 0; i < 4; i++) {
                if (connectionToCheck.checkCoords(xCoordOfPlayerConnection + xCoordModulator[i], yCoordOfPlayerConnection + yCoordModulator[i]) && connectionToCheck.getOwner() == null && !connectionToCheck.getOnWater()) {
                    neighbouringConnectionsToAConnection.add(connectionToCheck);
                }
            }
        }

        return neighbouringConnectionsToAConnection;
    }
}
