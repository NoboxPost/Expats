package expat.model.board;

import expat.model.buildings.ModelBuilding;
import expat.model.ModelRaider;
import expat.model.buildings.ModelBuilding;

import java.util.ArrayList;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ModelBoard {
    private ModelHex[][] hexes;
    private ArrayList<ModelBuilding> buildings;
    private ModelRaider raider;

    public ModelBoard(ModelHex[][] hexes , ArrayList<ModelBuilding> buildings){//, ModelRaider raider) {
        this.hexes = hexes;
        this.buildings = buildings;
//        this.raider = raider;

    }

    public ModelHex[][] getHexes() {
        return hexes;
    }
}
