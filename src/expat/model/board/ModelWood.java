package expat.model.board;

import expat.model.ModelMaterial;

/**
 * Created by gallatib on 29.03.2017.
 */
public class ModelWood extends ModelHex {
    public ModelWood(int xCoord, int yCoord) {
        super(xCoord,yCoord);
        type = "Wood";
        material = new ModelMaterial(new int[]{0,0,0,1,0}); //TODO: add corresponding ModelMaterial
    }
}
