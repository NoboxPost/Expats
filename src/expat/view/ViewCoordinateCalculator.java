package expat.view;

import expat.model.board.ModelHex;

import java.awt.geom.Arc2D;

/**
 * is responsible for the calculation of coordinates of board elements
 * <p>
 * created on 22.03.2017
 *
 * @author vanonir
 */
public class ViewCoordinateCalculator {
    int HEXSIZE;

    public ViewCoordinateCalculator(int hexSize){
        this.HEXSIZE = hexSize;
    }

    //TODO: What's this? What's this? There's something very wrong. Whats this? There's people singing songs.
    public Double[] calcHexCoords(int x, int y){
        Double[] returnIntArray = new Double[2];
        returnIntArray[0] = x*HEXSIZE*0.75;
        returnIntArray[1] = y*HEXSIZE*0.8;
        if (x%2==1)
            returnIntArray[1]+=HEXSIZE*0.4;
        return returnIntArray;
    }

    /**
     * calculates the coordinates of a hexfield on the board
     *
     * @param hex1
     * @return two doubles, xCoordinate and yCoordinate
     */
    public Double[] calcHexCoords(ModelHex hex1) {
        Double[] returnDoubleArray = new Double[2];
         returnDoubleArray[0] = hex1.getCoords()[0]*HEXSIZE*0.75;
         returnDoubleArray[1] = hex1.getCoords()[1]*HEXSIZE*0.8;
        if (hex1.getCoords()[0]%2==1)
             returnDoubleArray[1]+=HEXSIZE*0.4;
        return  returnDoubleArray;
    }

    /**
     * calculates the coordinates of the raider-figure on the board
     * because the raider is on a hex, his coordinates are almost the same as hex-coordinates
     * (but centered)
     *
     * @param hex
     * @return two doubles, xCoordinate and yCoordinate
     */
    public Double[] calcRaiderCoords(ModelHex hex) {
        Double[] returnDoubleArray = new Double[2];
        returnDoubleArray[0] = (hex.getCoords()[0]*HEXSIZE*0.75)+(0.5*HEXSIZE);
        returnDoubleArray[1] = (hex.getCoords()[1]*HEXSIZE*0.8)+(0.4*HEXSIZE);
        if (hex.getCoords()[0]%2==1)
            returnDoubleArray[1]+=HEXSIZE*0.4;
        return returnDoubleArray;
    }

    /**
     * calculates the coordinates of a building
     *
     * @param coords
     * @return two doubles, xCoordinate and yCoordinate
     */
    public Double[] calcBuildingCoords(int[] coords){
        Double[] returnDoubleArray = new Double[2];
        returnDoubleArray[0] = coords[0]* HEXSIZE*0.125;
        returnDoubleArray[1] = coords[1]*HEXSIZE*0.2;
        return returnDoubleArray;
    }


@Deprecated
    public int[] calcHexCoords(ModelHex hex1, ModelHex hex2) { return null; //TODO: remove if not used.
    }
@Deprecated
    public int[] calcHexCoords(ModelHex hex1, ModelHex hex2, ModelHex hex3) {
        return null;
    } //TODO: remove if not used.
}
