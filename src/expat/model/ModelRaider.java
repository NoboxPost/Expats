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
    private boolean allowRaid = false;
    private boolean allowMovement =false;

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

    /**
     * @param movementIsAllowed
     */
    public void setAllowMovement(boolean movementIsAllowed){
        this.allowMovement = movementIsAllowed;
    }

    /**
     * @return
     */
    public boolean getAllowMovement() {
        return allowMovement;
    }

    /**
     * @return
     */
    public ModelHex getRaiderHex() {
        return hex;
    }

    public boolean isAllowRaid() {
        return allowRaid;
    }

    public void setAllowRaid(boolean allowRaid) {
        this.allowRaid = allowRaid;
    }
}
