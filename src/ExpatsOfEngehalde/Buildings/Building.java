package ExpatsOfEngehalde.Buildings;

import ExpatsOfEngehalde.Board.Hex;
import ExpatsOfEngehalde.Materials.Material;
import ExpatsOfEngehalde.Player;

/**
 * Created by vanonir on 22.03.2017.
 */
public class Building {
    private Hex[] Neighbours;
    private java.util.List<Material> buildingCosts;
    private int winPoints;
    protected Player owner;



    public boolean isFlanking(Hex hexNeighbour){

        for (Hex hex:Neighbours){
            if (hex.equals(hexNeighbour)){//TODO: Test if equals realy works. Performance?
                return true;
            }
        }
        return false;
    }
}
