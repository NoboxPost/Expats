package expat.model;

import expat.control.*;
import expat.model.board.ModelBoard;
import expat.model.board.ModelBoardGenerator;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ModelApp {
    private ControllerBoardPane boardController;
    private ControllerPanePlayer playerController;
    private ControllerPaneAction actionController;
    private ControllerPaneMates matesController;
    private ControllerMainStage mainController;
    private ModelBoardGenerator boardGenerator;
    private ModelBoard board;
    private ModelPlayer[] players;

    public ModelApp(ControllerMainStage mainController, ControllerBoardPane boardController, ControllerPaneMates matesController, ControllerPaneAction actionController, ControllerPanePlayer playerController) {
        this.boardController = boardController;
        this.actionController = actionController;
        this.matesController = matesController;
        this.playerController = playerController;
        this.mainController = mainController;
        generateBoard();
    }

    public ModelBoard generateBoard() {
        ModelBoardGenerator boardGenerator = new ModelBoardGenerator();
        boardGenerator.generateHexes(8,6);
        return null; //TODO: change this
    }
}
