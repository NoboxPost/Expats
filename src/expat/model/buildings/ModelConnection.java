package expat.model.buildings;

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
    private boolean onWater=false;
    private int xBuildingCoord, yBuildingCoord;
    protected ModelPlayer owner;


    public void onWater(){
        onWater=true;
    }
    public boolean getOnWater(){
        return onWater;
    }
}

