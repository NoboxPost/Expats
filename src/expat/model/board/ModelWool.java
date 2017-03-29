package expat.model.board;

import expat.model.ModelMaterial;

/**
 * Created by gallatib on 29.03.2017.
 */
public class ModelWool extends ModelHex {
    public ModelWool(int xCoord, int yCoord) {
        super(xCoord,yCoord);
        type = "Wool";
        material = new ModelMaterial(new int[]{0,0,0,0,1}); //TODO: add corresponding ModelMaterial
    }
}