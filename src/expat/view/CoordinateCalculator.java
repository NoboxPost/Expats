package expat.view;

import expat.model.board.ModelHex;

import java.awt.geom.Arc2D;

/**
 * Contains
 * Created by vanonir on 22.03.2017.
 */
public class CoordinateCalculator {
    int hexSize;

    public CoordinateCalculator(int hexSize){
        this.hexSize = hexSize;
    }


    public Double[] calcCoords(int x, int y){
        Double[] returnIntArray = new Double[2];
        returnIntArray[0] = x*hexSize*0.75;
        returnIntArray[1] = y*hexSize*0.8;
        if (x%2==1)
            returnIntArray[1]+=hexSize*0.4;
        return returnIntArray;
    }


    public Double[] calcCoords(ModelHex hex1) {

        Double[] returnIntArray = new Double[2];
        returnIntArray[0] = hex1.getCoords()[0]*hexSize*0.75;
        returnIntArray[1] = hex1.getCoords()[1]*hexSize*0.8;
        if (hex1.getCoords()[0]%2==1)
            returnIntArray[1]+=hexSize*0.4;
        return returnIntArray;
    }



    public int[] calcCoords(ModelHex hex1, ModelHex hex2) { return null;
    }

    public int[] calcCoords(ModelHex hex1, ModelHex hex2, ModelHex hex3) {
        return null;
    } //TODO: remove if not used.
}
