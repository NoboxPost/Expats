package expat.model.buildings;

import expat.model.ModelPlayer;
import expat.model.board.ModelBoard;

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

    private void generatePlayerBuildingsArray(ModelPlayer modelPlayer){
        playerBuildings = new ArrayList<>();

        for(ModelBuilding modelBuilding : allBuildings){
            if(modelBuilding.getOwner() == modelPlayer){
                playerBuildings.add(modelBuilding);
            }
        }
    }

    private void generatePlayerConnectionsArray(ModelPlayer modelPlayer){
        playerConnections = new ArrayList<>();

        for(ModelConnection modelConnection : allConnections){
            if(modelConnection.getOwner() == modelPlayer){
                playerConnections.add(modelConnection);
            }
        }
    }

    public ArrayList<ModelBuilding> settlementsAPlayerCouldBuild(ModelPlayer currentplayer){
        ArrayList<ModelBuilding> settlementsAPlayerCouldBuild = new ArrayList<>();


        return settlementsAPlayerCouldBuild;
    }

    public ArrayList<ModelBuilding> townsAPlayerCouldBuild(ModelPlayer currentplayer){
        ArrayList<ModelBuilding> townsAPlayerCouldBuild = new ArrayList<>();

        return townsAPlayerCouldBuild;
    }

    public ArrayList<ModelConnection> roadsAPlayerCouldBuild(ModelPlayer currentplayer){
        ArrayList<ModelConnection> connectionsAPlayerCouldBuild = new ArrayList<>();
        generatePlayerConnectionsArray(currentplayer);

        for(ModelConnection playerConnection : playerConnections){
            int xCoordOfPlayerConnection = playerConnection.getCoords()[0];
            int yCoordOfPlayerConnection = playerConnection.getCoords()[1];

            switch (playerConnection.getOrientation()) {
                case "straight":

                    for (ModelConnection connectionToCheck : allConnections) {
                        int[] xCoordModulator = new int[]{-3, +3, -3, +3};
                        int[] yCoordModulator = new int[]{-1, -1, +1, +1};
                        for (int i = 0; i < 4; i++) {
                            if (connectionToCheck.checkCoords(xCoordOfPlayerConnection + xCoordModulator[i], yCoordOfPlayerConnection + yCoordModulator[i]) && connectionToCheck.getOwner() == null) {
                                connectionsAPlayerCouldBuild.add(connectionToCheck);
                            }
                        }
                    }
                    break;
                case "up":
                    for (ModelConnection connectionToCheck : allConnections) {
                        int[] xCoordModulator = new int[]{0, +3, -3, 0};
                        int[] yCoordModulator = new int[]{-2, -1, +1, +2};
                        for (int i = 0; i < 4; i++) {
                            if (connectionToCheck.checkCoords(xCoordOfPlayerConnection + xCoordModulator[i], yCoordOfPlayerConnection + yCoordModulator[i]) && connectionToCheck.getOwner() == null) {
                                connectionsAPlayerCouldBuild.add(connectionToCheck);
                            }
                        }
                    }
                    break;
                case "down":
                    for (ModelConnection connectionToCheck : allConnections) {
                        int[] xCoordModulator = new int[]{-3, 0, +3, 0};
                        int[] yCoordModulator = new int[]{-1, -2, +1, +2};
                        for (int i = 0; i < 4; i++) {
                            if (connectionToCheck.checkCoords(xCoordOfPlayerConnection + xCoordModulator[i], yCoordOfPlayerConnection + yCoordModulator[i]) && connectionToCheck.getOwner() == null) {
                                connectionsAPlayerCouldBuild.add(connectionToCheck);
                            }
                        }
                    }
                    break;
            }
        }

        return connectionsAPlayerCouldBuild;
    }
}
