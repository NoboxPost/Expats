package expat.model.buildings;

import expat.model.ModelCoordinateCalculator;
import expat.model.board.ModelHex;

import java.util.ArrayList;

/**
 * is responsible for generation of all connections.
 * <p>
 * created on 30.03.2017
 *
 * @author vanonir
 */
public class ModelConnectionFactory {

    private ModelHex[][] hexes;
    private int xHexSize, yHexSize;
    private ArrayList<ModelConnection> modelConnections = new ArrayList<>();
    private ModelCoordinateCalculator coordinateCalculator;


    public ModelConnectionFactory(int xSize, int ySize, ModelHex[][] hexes) {
        this.hexes = hexes;
        this.xHexSize = xSize;
        this.yHexSize = ySize;
        coordinateCalculator = new ModelCoordinateCalculator();
    }


    public void generateConnectionsForAllHexes() {
        if (hexes != null && xHexSize != 0 && yHexSize != 0) {
            for (int x = 0; x < xHexSize; x++) {
                for (int y = 0; y < yHexSize; y++) {
                    boolean[] spotsForConnections = checkPositionsToBuildOn(xHexSize, yHexSize, x, y);
                    int[] buildingCoords = coordinateCalculator.hexCoordToBuildingCoord(x, y);
                    generateBuildings(hexes[x][y], spotsForConnections, x, y);

                }
            }
        }
    }

    /**
     * @param modelHex
     * @param spotsForConnections
     * @param x
     * @param y
     */
    private void generateBuildings(ModelHex modelHex, boolean[] spotsForConnections, int x, int y) {
        int[] buildingCoords = coordinateCalculator.hexCoordToBuildingCoord(x, y);
        if (spotsForConnections[0]) {
            ModelConnection upperLeft = new ModelConnection(buildingCoords[0] + 1, buildingCoords[1] + 1, "up");
            upperLeft.addNeighbour(hexes[x][y]);
            try {
                if (x % 2 == 0) {
                    upperLeft.addNeighbour(hexes[x - 1][y - 1]);
                } else {
                    upperLeft.addNeighbour(hexes[x - 1][y]);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            modelConnections.add(upperLeft);
        }
        if (spotsForConnections[1]) {
            ModelConnection upperMiddle = new ModelConnection(buildingCoords[0] + 4, buildingCoords[1], "straight");
            upperMiddle.addNeighbour(hexes[x][y]);
            try {
                upperMiddle.addNeighbour(hexes[x][y - 1]);

            } catch (ArrayIndexOutOfBoundsException e) {
            }
            modelConnections.add(upperMiddle);
        }
        if (spotsForConnections[2]) {
            ModelConnection upperRight = new ModelConnection(buildingCoords[0] + 7, buildingCoords[1] + 1, "down");
            upperRight.addNeighbour(hexes[x][y]);
            try {
                if (x % 2 == 0) {
                    upperRight.addNeighbour(hexes[x + 1][y - 1]);
                } else {
                    upperRight.addNeighbour(hexes[x + 1][y]);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            modelConnections.add(upperRight);
        }
        if (spotsForConnections[3]) {
            ModelConnection lowerRight = new ModelConnection(buildingCoords[0] + 7, buildingCoords[1] + 3, "up");
            lowerRight.addNeighbour(hexes[x][y]);
            try {
                if (x % 2 == 0) {
                    lowerRight.addNeighbour(hexes[x + 1][y]);
                } else {
                    lowerRight.addNeighbour(hexes[x + 1][y + 1]);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            modelConnections.add(lowerRight);
        }
        if (spotsForConnections[4]) {
            ModelConnection lowerMiddle = new ModelConnection(buildingCoords[0] + 4, buildingCoords[1] + 4, "straight");
            lowerMiddle.addNeighbour(hexes[x][y]);
            try {
                lowerMiddle.addNeighbour(hexes[x][y + 1]);

            } catch (ArrayIndexOutOfBoundsException e) {
            }
            modelConnections.add(lowerMiddle);
        }
        if (spotsForConnections[5]) {
            ModelConnection lowerLeft = new ModelConnection(buildingCoords[0] + 1, buildingCoords[1] + 3, "down");
            lowerLeft.addNeighbour(hexes[x][y]);
            try {
                if (x % 2 == 0) {
                    lowerLeft.addNeighbour(hexes[x + 1][y - 1]);
                } else {
                    lowerLeft.addNeighbour(hexes[x + 1][y]);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            modelConnections.add(lowerLeft);
        }
    }


    /**
     * Checks all spots whether a empty connections must be built. upper left, middel an right connections will be built by default,
     * lower connections depending on postition of hex in hexgrid.
     * returns a boolean array with values, true standing for connection musst be built and false the opposite.
     *
     * @param xHexSize int with amount of hexes on x-coordinate
     * @param yHexSize int with amount of hexes on y-coordinate
     * @param xCurrent int for current hex x-coordinate
     * @param yCurrent int for current hex y-coordinate
     * @return
     */
    private boolean[] checkPositionsToBuildOn(int xHexSize, int yHexSize, int xCurrent, int yCurrent) {
        boolean[] spotsForConnections = new boolean[]{true, true, true, false, false, false};
        if (xCurrent == 0) {
            spotsForConnections[5] = true;
        }
        if (xCurrent == xHexSize - 1) {
            spotsForConnections[3] = true;
        }
        if (yCurrent == yHexSize - 1) {
            spotsForConnections[4] = true;
            if (xCurrent % 2 == 1) {
                spotsForConnections[3] = true;
                spotsForConnections[5] = true;
            }
        }
        return spotsForConnections;
    }

    /**
     * Initiates generation of all connections and returns an ArrayList with thous generated connections.
     *
     * @return ArrayList of Type ModelConnection with all generated connections.
     */
    public ArrayList<ModelConnection> generateConnections() {
        generateConnectionsForAllHexes();
        return modelConnections;
    }


}
