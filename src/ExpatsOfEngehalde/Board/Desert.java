package ExpatsOfEngehalde.Board;
import ExpatsOfEngehalde.Materials.Material;

/**
 * Created by vanonir on 23.03.2017.
 */
public class Desert extends Hex {
    public Desert(int id) {
        super(id);
        type = "Desert";
        material = new Material(new int[]{0,0,0,0,0});
    }
}
