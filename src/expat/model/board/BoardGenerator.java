package expat.model.board;

/**
 * Created by vanonir on 22.03.2017.
 */
public class BoardGenerator {
    private Board board;
    private  int xSize, ySize;
    private Hex[] hexes;

    public Board generateBoard(int xSize,int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        hexes = new Hex[xSize*ySize];
        return null;
    }
}
