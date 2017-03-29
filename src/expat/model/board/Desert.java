package expat.model.board;
import expat.model.Material;

/**
 * Created by vanonir on 23.03.2017.
 */
public class Desert extends Hex {
    public Desert(int xCoord,int yCoord) {
        super(xCoord,yCoord);
        type = "Desert";
        material = new Material(new int[]{0,0,0,0,0});
    }
}
