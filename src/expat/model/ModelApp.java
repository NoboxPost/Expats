package expat.model;

import expat.control.ControllerBoardPane;
import expat.control.ControllerMainStage;
import expat.model.board.ModelBoard;
import expat.model.board.ModelBoardGenerator;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ModelApp {
    private ControllerBoardPane boardController;
    private ControllerMainStage mainController;
    private ModelBoardGenerator boardGenerator;
    private ModelBoard board;
    private ModelPlayer[] players;

    public ModelApp(ControllerMainStage mainController, ControllerBoardPane boardController) {
        this.boardController = boardController;
        this.mainController = mainController;
        ModelBoardGenerator boardGenerator = new ModelBoardGenerator();
        this.board = boardGenerator.generateBoard(9,7);
    }

    public void generateBoard() {
        ModelBoardGenerator boardGenerator = new ModelBoardGenerator();
        this.board = boardGenerator.generateBoard(9,7);

    }

    public ModelBoard getBoard() {
        return board;
    }
}
