package expat.model.buildings;

import expat.model.ModelMaterial;
import expat.model.ModelPlayer;
import expat.model.board.ModelHex;

/**
 * is responsible for
 * <p>
 * created on 30.03.2017
 *
 * @author vanonir
 */
public class ModelConnection {
    private ModelHex[] neighbours = new ModelHex[2];
    private String[] types = new String[]{"empty","Road","Ship"};
    private String type = "empty";
    private boolean onWater=true;
    private int xBuildingCoord, yBuildingCoord;
    protected ModelPlayer owner;
    private String orientation;

    public ModelConnection(int xBuildingCoord, int yBuildingCoord, String orientation) {
        this.xBuildingCoord = xBuildingCoord;
        this.yBuildingCoord = yBuildingCoord;
        this.orientation = orientation;
    }


    public boolean getOnWater(){
        return onWater;
    }


    public boolean addNeighbour(ModelHex hex){
        if (neighbours[1]!=null){
            return false;
        }else {
            for (int i = 0; i < neighbours.length; i++) {
                if (neighbours[i] == null) {
                    neighbours[i] = hex;
                    if (!hex.getType().equals("Water")){
                        onWater=false;
                    }
                    return true;
                }

            }
            return false;
        }
    }

    public void buildRoad(String type,ModelPlayer owner){
        this.owner = owner;
        this.type = type;
    }


    public int[] getCoords() {

        return new int[]{xBuildingCoord,yBuildingCoord};
    }

    public ModelPlayer getOwner(){
        return owner;
    }
    public String getOrientation() {
        return orientation;
    }
}


