package expat.model.buildings;

import expat.model.board.ModelHex;
import expat.model.ModelMaterial;
import expat.model.ModelPlayer;

import java.io.Serializable;

/**
 * is responsible for handling all aspects of a building
 * <p>
 * created on 22.03.2017
 *
 * @author vanonir
 */
public class ModelBuilding implements Serializable {
    private ModelHex[] neighbours = new ModelHex[3];
    private String[] types = new String[]{"empty", "Settlement", "Town"};
    private String type = "empty";
    private int xBuildingCoord, yBuildingCoord;
    private int winPoints;
    private int resourceMultiplier;
    protected ModelPlayer owner;
    private boolean display = true;


    public ModelBuilding(int xBuildingCoord, int yBuildingCoord) {
        this.xBuildingCoord = xBuildingCoord;
        this.yBuildingCoord = yBuildingCoord;
    }

    /**
     * Takes a hex field and checks whether this building object is flanking it or not
     *
     * @param hexNeighbour
     * @return boolean, whether the building is flanking a specific hex or not
     */
    public boolean isFlanking(ModelHex hexNeighbour) {
        for (ModelHex hex : neighbours) {
            if (hex.equals(hexNeighbour)) {//TODO: Test if equals really works. Performance?
                return true;
            }
        }
        return false;
    }

    /**
     * Adds hex fields as neighbors of this building object
     *
     * @param hex
     * @return boolean, whether a hex field could get assigned as neighbors to this building object or not
     */
    public boolean addNeighbour(ModelHex hex) {
        if (neighbours[2] != null) {
            return false;
        } else {
            for (int i = 0; i < neighbours.length; i++) {
                if (neighbours[i] == null) {
                    neighbours[i] = hex;
                    return true;
                }

            }
            return false;
        }
    }

    /**
     * @param material
     */
    public void giveMaterialToOwner(ModelMaterial material) {
        if (!type.equals("empty")) {
            for (int i = 0; i < resourceMultiplier; i++) {
                owner.addMaterial(material);
            }
        }
    }

    /**
     * @param owner
     */
    public void buildSettlement(ModelPlayer owner) {
        this.owner = owner;
        type = "Settlement";
        winPoints = 1;
        resourceMultiplier = 1;
    }

    /**
     * @param owner
     */
    public void buildTown(ModelPlayer owner) {
        this.owner = owner;
        type = "Town";
        winPoints = 2;
        resourceMultiplier = 2;
    }

    /**
     * checks if given coordinates are equal to own building coordinates.
     *
     * @param coordsToCheck int array with first position standing for x coordinate, second position standing for y coordinate
     * @return true if coords are equal, else false.
     */
    public boolean checkCoords(int[] coordsToCheck) {
        return checkCoords(coordsToCheck[0], coordsToCheck[1]);
    }

    public boolean checkCoords(int xCoord, int yCoord) {
        if (xCoord == xBuildingCoord && yCoord == yBuildingCoord) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkHex(ModelHex foreignHex) {
        for (ModelHex ownHex : neighbours) {
            if (ownHex == foreignHex) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * @return
     */
    public int[] getCoords() {
        return new int[]{xBuildingCoord, yBuildingCoord};
    }

    /**
     * @return
     */
    public ModelPlayer getOwner() {
        return owner;
    }
}

