package expat.model.board;

import expat.model.ModelMaterial;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ModelWater extends ModelHex {
    public ModelWater(int xCoord, int yCoord) {
        super(xCoord,yCoord);
        type = "ModelWater";
        material = new ModelMaterial(new int[]{0,0,0,0,0}); //TODO: add corresponding ModelMaterial
    }
}
