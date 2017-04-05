package expat.model;

import expat.model.board.ModelHex;
import expat.model.buildings.ModelBuilding;


/**
 * is responsible for assigning a hex to the raider
 * <p>
 * created on 02.04.2017
 *
 * @author gallatib
 */
public class ModelRaider {
    private ModelHex hex;

    public ModelRaider(ModelHex hex) {
        this.hex = hex;
    }

    /**
     * changes a hex from "unraided" to raided and vice versa
     * (raid does also mean "unraid" when the hex is already raided)
     *
     * @param hex
     */
    public void moveRaider(ModelHex hex){
        this.hex.raid();
        hex.raid();
        this.hex = hex;
    }

    public void acitvateRaider(){

    }

    public ModelHex getRaiderHex() {
        return hex;
    }
}
