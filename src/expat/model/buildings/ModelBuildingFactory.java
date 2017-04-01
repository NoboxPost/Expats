package expat.model.buildings;

import expat.model.ModelCoordinateCalculator;
import expat.model.board.ModelBoard;
import expat.model.board.ModelHex;

import java.util.ArrayList;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ModelBuildingFactory {

    private ModelHex[][] hexes;
    private int xHexSize, yHexSize;
    private ArrayList<ModelBuilding> modelBuildings = new ArrayList<>();
    private ModelCoordinateCalculator coordinateCalculator;


    public ModelBuildingFactory(int xSize, int ySize, ModelHex[][] hexes) {
        this.hexes = hexes;
        this.xHexSize = xSize;
        this.yHexSize = ySize;
        coordinateCalculator = new ModelCoordinateCalculator(xSize, ySize);
    }


    /**
     * Runs through all hexes, checks on which corners emptyBuildingSpots must be placed and initiates generation for all possible BuildingSpots.
     */
    public void generateEmptyBuildingSpotsForAllHexes() {
        if (hexes != null && xHexSize != 0 && yHexSize != 0) {
            for (int x = 1; x < xHexSize - 1; x++) {
                for (int y = 1; y < yHexSize - 1; y++) {
                    if (!hexes[x][y].getType().equals("Water")) {
                        boolean[] positionsToFill = checkPositionsToFill(x, y);
                        generateEmptyBuildingSpots(positionsToFill, x, y);
                    }
                }
            }
        }
    }

    /**
     * Generates all buildings for a given hex and appends them to the ArrayList modelBuilding field.
     */
    public void generateEmptyBuildingSpots(boolean[] positionsToFill, int xHexCoord, int yHexCoord) {
        int[] BuildingCoords = coordinateCalculator.hexCoordToBuildingCoord(hexes[xHexCoord][yHexCoord]);
        if (positionsToFill[0]) {
            ModelBuilding middleLeftBuilding = new ModelBuilding(BuildingCoords[0], BuildingCoords[1] + 2);
            middleLeftBuilding.addNeighbour(hexes[xHexCoord][yHexCoord]);
            if (xHexCoord % 2 == 0) {
                middleLeftBuilding.addNeighbour(hexes[xHexCoord - 1][yHexCoord - 1]);
                middleLeftBuilding.addNeighbour(hexes[xHexCoord - 1][yHexCoord]);
            } else {
                middleLeftBuilding.addNeighbour(hexes[xHexCoord - 1][yHexCoord]);
                middleLeftBuilding.addNeighbour(hexes[xHexCoord - 1][yHexCoord + 1]);
            }
            modelBuildings.add(middleLeftBuilding);
        }
        if (positionsToFill[1]) {
            ModelBuilding topLeftBuilding = new ModelBuilding(BuildingCoords[0] + 2, BuildingCoords[1]);
            topLeftBuilding.addNeighbour(hexes[xHexCoord][yHexCoord]);
            topLeftBuilding.addNeighbour(hexes[xHexCoord][yHexCoord - 1]);
            if (xHexCoord % 2 == 0) {
                topLeftBuilding.addNeighbour(hexes[xHexCoord-1][yHexCoord-1]);
            } else {
                topLeftBuilding.addNeighbour(hexes[xHexCoord-1][yHexCoord]);
            }
            modelBuildings.add(topLeftBuilding);
        }
        if (positionsToFill[2]) {
            ModelBuilding topRightBuilding = new ModelBuilding(BuildingCoords[0] + 6, BuildingCoords[1]);
            topRightBuilding.addNeighbour(hexes[xHexCoord][yHexCoord]);
            topRightBuilding.addNeighbour(hexes[xHexCoord][yHexCoord - 1]);
            if (xHexCoord % 2 == 0) {
                topRightBuilding.addNeighbour(hexes[xHexCoord+1][yHexCoord-1]);
            } else {
                topRightBuilding.addNeighbour(hexes[xHexCoord+1][yHexCoord]);
            }
            modelBuildings.add(topRightBuilding);
        }
        if (positionsToFill[3]) {
            ModelBuilding middleRightBuilding = new ModelBuilding(BuildingCoords[0]+8, BuildingCoords[1] + 2);
            middleRightBuilding.addNeighbour(hexes[xHexCoord][yHexCoord]);
            if (xHexCoord % 2 == 0) {
                middleRightBuilding.addNeighbour(hexes[xHexCoord+1][yHexCoord-1]);
                middleRightBuilding.addNeighbour(hexes[xHexCoord+1][yHexCoord]);
            } else {
                middleRightBuilding.addNeighbour(hexes[xHexCoord+1][yHexCoord]);
                middleRightBuilding.addNeighbour(hexes[xHexCoord+1][yHexCoord+1]);
            }
            modelBuildings.add(middleRightBuilding);
        }
        if (positionsToFill[4]) {
            ModelBuilding bottomRightBuilding = new ModelBuilding(BuildingCoords[0]+6, BuildingCoords[1]+4);
            bottomRightBuilding.addNeighbour(hexes[xHexCoord][yHexCoord]);
            bottomRightBuilding.addNeighbour(hexes[xHexCoord][yHexCoord + 1]);
            if (xHexCoord % 2 == 0) {
                bottomRightBuilding.addNeighbour(hexes[xHexCoord+1][yHexCoord]);
            } else {
                bottomRightBuilding.addNeighbour(hexes[xHexCoord+1][yHexCoord+1]);
            }
            modelBuildings.add(bottomRightBuilding);

        }
        if (positionsToFill[5]) {
            ModelBuilding bottomLeftBuilding = new ModelBuilding(BuildingCoords[0]+2, BuildingCoords[1]+4);
            bottomLeftBuilding.addNeighbour(hexes[xHexCoord][yHexCoord]);
            bottomLeftBuilding.addNeighbour(hexes[xHexCoord][yHexCoord + 1]);
            if (xHexCoord % 2 == 0) {
                bottomLeftBuilding.addNeighbour(hexes[xHexCoord-1][yHexCoord]);
            } else {
                bottomLeftBuilding.addNeighbour(hexes[xHexCoord-1][yHexCoord+1]);
            }
            modelBuildings.add(bottomLeftBuilding);
        }
    }


    /**
     * Checks on which on which corners will be built on. By default top right and top left corner will always be built on it hex is not of type "water".
     *
     * @param x Coordinate of hex to check
     * @param y Coordinate of hex to check
     * @return returns an array of booleans with index corresponding to corner of hex as folows: 0 left middle, 1 top left, 2 top right, 3 middle right, 4 bottom right, 5 bottom left.
     */
    private boolean[] checkPositionsToFill(int x, int y) {
        boolean[] spotsToBuildOn = new boolean[]{false, true, true, false, false, false};
        spotsToBuildOn[0] = checkPosition1(x, y);
        spotsToBuildOn[3] = checkPosition4(x, y);
        spotsToBuildOn[4] = checkPosition5(x, y, spotsToBuildOn[3]);
        spotsToBuildOn[5] = checkPosition6(x, y, spotsToBuildOn[0]);

        return spotsToBuildOn;
    }


    /**
     * Checks the middle left corner of a hex and returns true if neighbouring lower left hex is of type water.
     *
     * @param x Coordinate of hex to check
     * @param y Coordinate of hex to check
     * @return boolean true if lower left neighbour hex is of type water
     */
    private boolean checkPosition1(int x, int y) {
        if (x % 2 == 0) {
            if (hexes[x - 1][y].getType().equals("Water")) {
                return true;
            }
        } else {
            if (hexes[x - 1][y + 1].getType().equals("Water")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks the middle right corner of a hex and returns true if neighbouring lower right hex is of type water.
     *
     * @param x Coordinate of hex to check
     * @param y Coordinate of hex to check
     * @return boolean true if lower left neighbour hex is of type water;
     */
    private boolean checkPosition4(int x, int y) {
        if (x % 2 == 0) {
            if (hexes[x + 1][y].getType().equals("Water")) {
                return true;
            }
        } else {
            if (hexes[x + 1][y + 1].getType().equals("Water")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks the lower left corner of a hex and returns true if neighbouring lower left hex and hex underneath is of type water.
     *
     * @param x Coordinate of hex to check
     * @param y Coordinate of hex to check
     * @return boolean true if lower left and underneath neighbour hex is of type water;
     */
    private boolean checkPosition6(int x, int y, boolean postition1) {
        if (hexes[x][y + 1].getType().equals("Water") && postition1) {
            return true;
        }
        return false;
    }

    /**
     * Checks the lower right corner of a hex and returns true if neighbouring lower right hex and hex underneath is of type water.
     *
     * @param x Coordinate of hex to check
     * @param y Coordinate of hex to check
     * @return boolean true if lower right and underneath neighbour hex is of type water;
     */
    private boolean checkPosition5(int x, int y, boolean postition4) {
        if (hexes[x][y + 1].getType().equals("Water") && postition4) {
            return true;
        }
        return false;
    }

    public ArrayList<ModelBuilding> generateBuildings() {
        generateEmptyBuildingSpotsForAllHexes();
        return modelBuildings;
    }
}
