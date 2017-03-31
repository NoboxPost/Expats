package expat.model.buildings;

import expat.model.board.ModelHex;
import expat.model.ModelMaterial;
import expat.model.ModelPlayer;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ModelBuilding {
    private ModelHex[] neighbours = new ModelHex[3];
    private String[] types = new String[]{"empty","Settlement","Town"};
    private String type = "empty";
    private int xBuildingCoord, yBuildingCoord;
    private int winPoints;
    protected ModelPlayer owner;

    public ModelBuilding(int xBuildingCoord, int yBuildingCoord) {
        this.xBuildingCoord = xBuildingCoord;
        this.yBuildingCoord = yBuildingCoord;
    }

    public boolean isFlanking(ModelHex hexNeighbour){
        for (ModelHex hex: neighbours){
            if (hex.equals(hexNeighbour)){//TODO: Test if equals really works. Performance?
                return true;
            }
        }
        return false;
    }
    public boolean addNeighbour(ModelHex hex){
        if (neighbours[2]!=null){
            return false;
        }else {
            for (int i = 0; i < neighbours.length; i++) {
                if (neighbours[i] == null) {
                    neighbours[i] = hex;
                    return true;
                }

            }
            return false;
        }
    }

    public void giveMaterialToOwner(ModelMaterial material){
        if(!type.equals("empty")){
            //TODO: town/settlement player gewusel
        }
    }

    public String getType() {
        return type;
    }
    public int[] getCoords(){
        return new int[]{xBuildingCoord,yBuildingCoord};
    }
    public void changeBuildingType(){
        type = type.equals("empty")? "Settlement":"empty";
    }
}
