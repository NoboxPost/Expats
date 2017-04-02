package expat.view;

import expat.model.board.ModelHex;

import java.awt.geom.Arc2D;

/**
 * Contains
 * Created by vanonir on 22.03.2017.
 */
public class ViewCoordinateCalculator {
    int hexSize;

    public ViewCoordinateCalculator(int hexSize){
        this.hexSize = hexSize;
    }


    public Double[] calcHexCoords(int x, int y){
        Double[] returnIntArray = new Double[2];
        returnIntArray[0] = x*hexSize*0.75;
        returnIntArray[1] = y*hexSize*0.8;
        if (x%2==1)
            returnIntArray[1]+=hexSize*0.4;
        return returnIntArray;
    }


    public Double[] calcHexCoords(ModelHex hex1) {

        Double[] returnIntArray = new Double[2];
        returnIntArray[0] = hex1.getCoords()[0]*hexSize*0.75;
        returnIntArray[1] = hex1.getCoords()[1]*hexSize*0.8;
        if (hex1.getCoords()[0]%2==1)
            returnIntArray[1]+=hexSize*0.4;
        return returnIntArray;
    }

    public Double[] calcBuildingCoords(int[] coords){
        Double[] returnArray = new Double[2];
        returnArray[0] = coords[0]* hexSize*0.125;
        returnArray[1] = coords[1]*hexSize*0.2;
        return returnArray;
    }


@Deprecated
    public int[] calcHexCoords(ModelHex hex1, ModelHex hex2) { return null; //TODO: remove if not used.
    }
@Deprecated
    public int[] calcHexCoords(ModelHex hex1, ModelHex hex2, ModelHex hex3) {
        return null;
    } //TODO: remove if not used.
}
