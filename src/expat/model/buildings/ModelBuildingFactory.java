package expat.model.buildings;

import expat.model.board.ModelBoard;
import expat.model.board.ModelHex;

import java.util.ArrayList;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ModelBuildingFactory {

    ModelBoard board;
    ArrayList<ModelBuilding> modelBuildings = new ArrayList<>();


    public ModelBuildingFactory(int xSize, int ySize, ModelHex[][] hexes) {
    }

    public ArrayList<ModelBuilding> generateBuildings() {

        return modelBuildings;
    }
}
