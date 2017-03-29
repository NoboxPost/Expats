package expat.model.board;

import expat.model.ModelMaterial;

/**
 * Created by gallatib on 29.03.2017.
 */
public class ModelClay extends ModelHex {
    public ModelClay(int xCoord, int yCoord) {
        super(xCoord,yCoord);
        type = "Clay";
        material = new ModelMaterial(new int[]{1,0,0,0,0}); //TODO: add corresponding ModelMaterial
    }
}