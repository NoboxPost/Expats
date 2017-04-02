package expat.model;

import expat.model.board.ModelHex;


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
    private void moveRaider(ModelHex hex){
        this.hex.raid();
        hex.raid();
        this.hex = hex;
    }

    public ModelHex getRaiderHex() {
        return hex;
    }
}
