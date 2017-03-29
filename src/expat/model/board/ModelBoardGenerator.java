package expat.model.board;

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
}
