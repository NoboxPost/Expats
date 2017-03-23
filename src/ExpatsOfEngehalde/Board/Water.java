package ExpatsOfEngehalde.Board;

import ExpatsOfEngehalde.Materials.Material;

/**
 * Created by vanonir on 22.03.2017.
 */
public class Water extends Hex {
    public Water(int id) {
        super(id);
        type = "Water";
        Material material = new Material(0,0,0,0,0); //TODO: add coresponding Material
    }
}
