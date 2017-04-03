package expat.model.board;

import expat.model.ModelRaider;
import expat.model.buildings.ModelBuilding;

/**
 * is responsible for the creation of a hexagonal field
 * <p>
 * created on 22.03.2017
 *
 * @author vanonir
 */
public class ModelHexFactory {

    private int xSize, ySize;
    private ModelHex[][] hexes;

    public ModelHex[][] generateHexes(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        hexes = new ModelHex[xSize][ySize];
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                hexes[x][y] = new ModelHex(x, y);

            }
        }

        hexes[3][1].setTypeAndDiceNumber("Clay",2);
        hexes[4][1].setTypeAndDiceNumber("Grain",4);
        hexes[5][1].setTypeAndDiceNumber("Wood",6);
        hexes[2][2].setTypeAndDiceNumber("Wool",9);
        hexes[3][2].setTypeAndDiceNumber("Clay",8);
        hexes[4][2].setTypeAndDiceNumber("Wood",5);
        hexes[5][2].setTypeAndDiceNumber("Wool",12);
        hexes[6][2].setTypeAndDiceNumber("Grain",9);
        hexes[2][3].setTypeAndDiceNumber("Wood",3);
        hexes[3][3].setTypeAndDiceNumber("Stone",5);
        hexes[4][3].setTypeAndDiceNumber("Desert",0);
        hexes[5][3].setTypeAndDiceNumber("Stone",8);
        hexes[6][3].setTypeAndDiceNumber("Wool",4);
        hexes[2][4].setTypeAndDiceNumber("Grain",3);
        hexes[3][4].setTypeAndDiceNumber("Grain",6);
        hexes[4][4].setTypeAndDiceNumber("Clay",10);
        hexes[5][4].setTypeAndDiceNumber("Wood",11);
        hexes[6][4].setTypeAndDiceNumber("Wool",10);
        hexes[4][5].setTypeAndDiceNumber("Stone",11);

        //hexes[7][5].setTypeAndDiceNumber("Stone",8);


        return hexes;
    }
}