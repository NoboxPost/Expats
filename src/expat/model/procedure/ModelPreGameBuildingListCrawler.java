package expat.model.procedure;

import expat.model.ModelPlayer;
import expat.model.board.ModelBoard;
import expat.model.buildings.ModelBuilding;
import expat.model.buildings.ModelConnection;

import java.util.ArrayList;

/**
 * Created by gallatib on 29.06.2017.
 */
public class ModelPreGameBuildingListCrawler {

    private ArrayList<ModelBuilding> allBuildings;
    private ArrayList<ModelBuilding> buildingsWithOwner;
    private ArrayList<ModelBuilding> buildingsWithoutOwner;
    private ArrayList<ModelConnection> allConnections;
    private ModelBoard modelBoard;
    private boolean foundOwner;

    public ModelPreGameBuildingListCrawler(ModelBoard modelBoard) {
        this.modelBoard = modelBoard;
        allBuildings = modelBoard.getBuildings();
        allConnections = modelBoard.getConnections();
    }

    public ArrayList<ModelBuilding> settlementsAPlayerCouldBuild() {
        createBuildingsArray();

        ArrayList<ModelBuilding> buildingsAPlayerCouldBuild = new ArrayList<>();

        //creates a model list with all places a player could build
        //a place ("building") where a player could build has no owner (is empty) and is not right next to a building with owner
        buildingLoop:
        for (ModelBuilding buildingWithoutOwner : buildingsWithoutOwner) {
            for (ModelBuilding buildingWithOwner : buildingsWithOwner) {
                if (buildingWithoutOwnerIsNextToABuildingWithOwner(buildingWithoutOwner, buildingWithOwner)) {
                    continue buildingLoop;
                }
            }
            buildingsAPlayerCouldBuild.add(buildingWithoutOwner);
        }

        return buildingsAPlayerCouldBuild;
    }

    public void createBuildingsArray() {
        buildingsWithOwner = new ArrayList<>();
        buildingsWithoutOwner = new ArrayList<>();
        for (ModelBuilding modelBuilding : allBuildings) {
            if (modelBuilding.getOwner() != null) {
                buildingsWithOwner.add(modelBuilding);
            } else {
                buildingsWithoutOwner.add(modelBuilding);
            }
        }
    }

    public boolean buildingWithoutOwnerIsNextToABuildingWithOwner(ModelBuilding buildingWithoutOwner, ModelBuilding buildingWithOwner) {
        boolean hasANeighbour = false;
        int[] coords = buildingWithoutOwner.getCoords();
        int xCoordOfNewBuilding = coords[0];
        int yCoordOfNewBuilding = coords[1];
        int[] xCoordModulator = new int[]{-2, +2, +2, -2, -4, +4};
        int[] yCoordModulator = new int[]{-2, -2, +2, +2, 0, 0};

        for (int i = 0; i < 6; i++) {
            if (buildingWithOwner.checkCoords(xCoordOfNewBuilding + xCoordModulator[i], yCoordOfNewBuilding + yCoordModulator[i])) {
                hasANeighbour = true;
            }
        }
        return hasANeighbour;
    }

    public ArrayList<ModelConnection> connectionAPlayerCouldBuild(ModelPlayer player) {

        ArrayList<ModelConnection> roadsAPlayerCouldBuild = new ArrayList<>();

        for (ModelBuilding modelBuilding : allBuildings) {
            ArrayList<ModelConnection> roadsAroundPlayerBuilding = new ArrayList<>();
            int[] xCoordModulator;
            int[] yCoordModulator;

            if (modelBuilding.getOwner() == player) {
                int xCoordOfPlayerBuilding = modelBuilding.getCoords()[0];
                int yCoordOfPlayerBuilding = modelBuilding.getCoords()[1];
                foundOwner = false;

                if (xCoordOfPlayerBuilding % 6 == 0) {
                    xCoordModulator = new int[]{-2, +1, +1};
                    yCoordModulator = new int[]{0, -1, +1};

                } else {
                    xCoordModulator = new int[]{+2, -1, -1};
                    yCoordModulator = new int[]{0, -1, +1};
                }

                roadsAroundPlayerBuilding = connectionsAroundBuilding(xCoordModulator, yCoordModulator, xCoordOfPlayerBuilding, yCoordOfPlayerBuilding);

                if (!foundOwner) {
                    roadsAPlayerCouldBuild.addAll(roadsAroundPlayerBuilding);
                } else {
                    roadsAroundPlayerBuilding.clear();
                }

            }
        }
        return roadsAPlayerCouldBuild;
    }


    private ArrayList<ModelConnection> connectionsAroundBuilding(int[] xCoordModulator, int[] yCoordModulator, int xCoordOfPlayerBuilding, int yCoordOfPlayerBuilding){
        ArrayList<ModelConnection> roadsAroundPlayerBuilding = new ArrayList<>();
        for (ModelConnection modelConnection : allConnections) {
            for (int i = 0; i < 3; i++) {
                if (modelConnection.checkCoords(xCoordOfPlayerBuilding + xCoordModulator[i], yCoordOfPlayerBuilding + yCoordModulator[i])) {
                    if (modelConnection.getOwner() == null) {
                        if(!modelConnection.getOnWater()){
                            roadsAroundPlayerBuilding.add(modelConnection);
                        }
                    } else {
                        foundOwner = true;
                    }
                }
            }
        }
        return roadsAroundPlayerBuilding;
    }
}

