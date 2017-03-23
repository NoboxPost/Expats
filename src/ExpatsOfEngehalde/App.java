package ExpatsOfEngehalde;

import ExpatsOfEngehalde.Board.BoardGenerator;
import ExpatsOfEngehalde.Controller.BoardPaneController;

/**
 * Created by vanonir on 22.03.2017.
 */
public class App {
    private BoardPaneController boardPaneController;
    private ExpatsOfEngehalde.Controller.MainStageController mainController;
    private BoardGenerator boardGenerator;
    private ExpatsOfEngehalde.Board.Board board;
    private Player[] players;

    public void generateBoard() {
        BoardGenerator boardGenerator = new BoardGenerator();
    }
}
