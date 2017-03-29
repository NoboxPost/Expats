package expat.model.board;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ModelBoardGenerator {
    private ModelBoard board;
    private  int xSize, ySize;
    private ModelHex[] hexes;

    public ModelBoard generateBoard(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        hexes = new ModelHex[xSize*ySize];
        return null;
    }
}
