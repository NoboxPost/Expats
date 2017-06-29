package expat.model.buildings;

import expat.model.ModelPlayer;
import expat.model.board.ModelBoard;

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

    public ModelPreGameBuildingListCrawler(ModelBoard modelBoard) {
        this.modelBoard = modelBoard;
        allBuildings = modelBoard.getBuildings();
        allConnections = modelBoard.getConnections();
    }

    public ArrayList<ModelBuilding> settlementsAPlayerCouldBuild(){
        createBuildingsArray();

        ArrayList<ModelBuilding> buildingsAPlayerCouldBuild = new ArrayList<>();

        //creates a model list with all places a player could build
        //a place ("building") where a player could build has no owner (is empty) and is not right next to a building with owner
        buildingLoop:
        for(ModelBuilding buildingWithoutOwner : buildingsWithoutOwner){
            for(ModelBuilding buildingWithOwner : buildingsWithOwner){
                if(buildingWithoutOwnerIsNextToABuildingWithOwner(buildingWithoutOwner, buildingWithOwner)){
                    continue buildingLoop;
                }
            }
            buildingsAPlayerCouldBuild.add(buildingWithoutOwner);
        }

        return buildingsAPlayerCouldBuild;
    }

    public void createBuildingsArray(){
        buildingsWithOwner = new ArrayList<>();
        buildingsWithoutOwner = new ArrayList<>();
        for(ModelBuilding modelBuilding : allBuildings){
            if(modelBuilding.getOwner() != null){
                buildingsWithOwner.add(modelBuilding);
            }
            else{
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
            if (buildingWithOwner.checkCoords(xCoordOfNewBuilding + xCoordModulator[i] , yCoordOfNewBuilding + yCoordModulator[i])) {
                hasANeighbour = true;
            }
        }
        return hasANeighbour;
    }


    public ArrayList<ModelConnection> roadsAPlayerCouldBuild(ModelPlayer player){
        ArrayList<ModelConnection> roadsAPlayerCouldBuild = new ArrayList<>();

        for(ModelConnection modelConnection : allConnections){
            int xCoordOfNewConnection = modelConnection.getCoords()[0];
            int yCoordOfNewConnection = modelConnection.getCoords()[1];
            int[] xCoordModulator;
            int[] yCoordModulator;

            switch (modelConnection.getOrientation()) {
                case "straight":
                    xCoordModulator = new int[]{-2, +2};
                    yCoordModulator = new int[]{0, 0};
                    if(asdasd(xCoordOfNewConnection, yCoordOfNewConnection, xCoordModulator, yCoordModulator, player)){
                        roadsAPlayerCouldBuild.add(modelConnection);
                    }
                    break;

                case "up":
                    xCoordModulator = new int[]{+1, -1};
                    yCoordModulator = new int[]{-1, +1};
                    if(asdasd(xCoordOfNewConnection, yCoordOfNewConnection, xCoordModulator, yCoordModulator, player)){
                        roadsAPlayerCouldBuild.add(modelConnection);
                    }
                    break;

                case "down":
                    xCoordModulator = new int[]{-1, +1};
                    yCoordModulator = new int[]{-1, +1};
                    if(asdasd(xCoordOfNewConnection, yCoordOfNewConnection, xCoordModulator, yCoordModulator, player)){
                        roadsAPlayerCouldBuild.add(modelConnection);
                    }
            }

        }
        return roadsAPlayerCouldBuild;
    }

    public boolean asdasd(int xCoordOfNewConnection, int yCoordOfNewConnection, int[] xCoordModulator, int[] yCoordModulator, ModelPlayer player){
        boolean dings = false;
        for (ModelBuilding building : allBuildings) {
            if (building.getOwner() == player) {
                for (int i = 0; i < 2; i++) {
                    if (building.checkCoords(xCoordOfNewConnection + xCoordModulator[i], yCoordOfNewConnection + yCoordModulator[i])) {
                        dings = true;
                    }
                }
            }
        }
        return dings;
    }

}
