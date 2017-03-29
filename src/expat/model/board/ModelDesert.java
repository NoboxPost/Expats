package expat.model.board;
import expat.model.ModelMaterial;

/**
 * Created by vanonir on 23.03.2017.
 */
public class ModelDesert extends ModelHex {
    public ModelDesert(int xCoord, int yCoord) {
        super(xCoord,yCoord);
        type = "Desert";
        material = new ModelMaterial(new int[]{0,0,0,0,0});
    }
}
