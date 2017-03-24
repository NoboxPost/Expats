package expat.model;

import expat.control.ControllerBoardPane;
import expat.control.ControllerMainStage;
import expat.model.board.Board;
import expat.model.board.BoardGenerator;

/**
 * Created by vanonir on 22.03.2017.
 */
public class App {
    private ControllerBoardPane boardController;
    private ControllerMainStage mainController;
    private BoardGenerator boardGenerator;
    private expat.model.board.Board board;
    private Player[] players;

    public App(ControllerMainStage mainController, ControllerBoardPane boardController) {
        this.boardController = boardController;
        this.mainController = mainController;
    }

    public Board generateBoard() {
        BoardGenerator boardGenerator = new BoardGenerator();
        return null;
    }
}
