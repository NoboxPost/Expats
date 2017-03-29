package expat.model.board;

import expat.model.Material;

/**
 * Created by vanonir on 22.03.2017.
 */
public class Water extends Hex {
    public Water(int xCoord,int yCoord) {
        super(xCoord,yCoord);
        type = "Water";
        material = new Material(new int[]{0,0,0,0,0}); //TODO: add corresponding Material
    }
}
