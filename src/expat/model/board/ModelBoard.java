package expat.model.board;

import expat.model.buildings.ModelBuilding;
import expat.model.ModelRaider;
import expat.model.buildings.ModelBuilding;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ModelBoard {
    private ModelHex[][] hexes;
    private ModelBuilding[] buildings;
    private ModelRaider raider;

    public ModelBoard(ModelHex[][] hexes){//, Building[] buildings, ModelRaider raider) {
        this.hexes = hexes;
//        this.buildings = buildings;
//        this.raider = raider;
    }

    public ModelHex[][] getHexes() {
        return hexes;
    }
}
