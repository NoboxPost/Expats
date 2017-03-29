package expat.model.board;

import expat.model.ModelMaterial;

/**
 * Created by gallatib on 29.03.2017.
 */
public class ModelGrain extends ModelHex {
    public ModelGrain(int xCoord, int yCoord) {
        super(xCoord,yCoord);
        type = "Grain";
        material = new ModelMaterial(new int[]{0,1,0,0,0}); //TODO: add corresponding ModelMaterial
    }
}
