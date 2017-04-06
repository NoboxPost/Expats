package expat.model;

import expat.model.board.ModelHex;

/**
 * is responsible for callculation from hex coordinates to building coordinates.
 * <p>
 * created on 31.03.2017
 *
 * @author vanonir
 */
public class ModelCoordinateCalculator {


    /**
     * Calculates the relative 0;0 Coordinate of a Hex in the Building Coordinate System, according to given Hex.
     *
     *  012345678
     * 0oo-----oo
     * 1o/ooooo\o
     * 2{ooooooo}---
     * 3o\ooooo/o
     * 4oo----{oo
     *
     * @param hex Hex which contains the coordinates.
     * @return an int[] Array int[0] corresponds to X Coordinate of Building Grid, int[1] corresponds to Y coordinate of the Building Grid.
     */
    public int[] hexCoordToBuildingCoord(ModelHex hex){
         int[] coords = hex.getCoords();
        return hexCoordToBuildingCoord(coords[0],coords[1]);
    }

    /**
     * Calculates the relative 0;0 Coordinate of a Hex in the Building Coordinate System, according to given Hex.
     *
     *  012345678
     * 0oo-----oo
     * 1o/ooooo\o
     * 2{ooooooo}---
     * 3o\ooooo/o
     * 4oo----{oo
     *
     * @param xHexCoord X Coordinate of a ModelHex
     * @param yHexCoord Y Coordinate of a ModelHex
     * @return an int[] Array int[0] corresponds to X Coordinate of Building Grid, int[1] corresponds to Y coordinate of the Building Grid.
     */
    public int[] hexCoordToBuildingCoord(int xHexCoord, int yHexCoord) {
        int[] intArray = new int[2];
        intArray[0] = xHexCoord * 6;
        intArray[1] = yHexCoord * 4;
        intArray[1] += xHexCoord % 2 == 0 ? 0 : 2;
        return intArray;
    }


}
