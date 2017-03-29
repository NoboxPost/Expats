package expat.model.board;

import expat.model.ModelRaider;
import expat.model.buildings.ModelBuilding;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ModelBoardGenerator {
    private ModelBoard board;
    private  int xSize, ySize;
    private ModelHex[][] hexes;

    public ModelHex[][] generateHexes(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        hexes = new ModelHex[xSize][ySize];
        for (int x = 0; x<xSize;x++){
            for (int y = 0;y<ySize;y++){
                hexes[x][y] = new ModelWater(x,y); // TODO: change ModelWater to ModelHex, as soon as ModelHex isn't abstract anymore.

            }
        }



        return hexes;
    }

    public ModelBoard generateBoard(int xSize,int ySize) {
        hexes = generateHexes(xSize,ySize);
//        Building[] buildings = new Building[0]; //TODO: Implement Buildigns
//        ModelRaider raider= new ModelRaider(); //TODO: Implement Raider
//        buildings[0] = new Building();
        board = new ModelBoard(hexes);
        return board ;
    }

    public ModelBoard getBoard() {
        return board;
    }
}
