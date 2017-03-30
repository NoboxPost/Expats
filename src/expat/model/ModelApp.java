package expat.model;

import expat.control.*;
import expat.model.board.ModelBoard;
import expat.model.board.ModelBoardFactory;
import expat.model.board.ModelHexFactory;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ModelApp {
    private PaneBoardController boardController;
    private PanePlayerController playerController;
    private PaneActionController actionController;
    private PaneMatesController matesController;
    private ControllerMainStage mainController;
    private ModelHexFactory boardGenerator;
    private ModelBoard board;
    private ModelPlayer[] players;

    public ModelApp(ControllerMainStage mainController, PaneBoardController boardController, PaneMatesController matesController, PaneActionController actionController, PanePlayerController playerController) {
        this.boardController = boardController;
        this.actionController = actionController;
        this.matesController = matesController;
        this.playerController = playerController;
        this.mainController = mainController;
        ModelBoardFactory boardGenerator = new ModelBoardFactory(9,7);
        this.board = boardGenerator.generateBoard();
    }


    public ModelBoard getBoard() {
        return board;
    }
}
