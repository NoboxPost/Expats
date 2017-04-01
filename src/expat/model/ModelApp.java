package expat.model;

import expat.control.*;
import expat.model.board.ModelBoard;
import expat.model.board.ModelBoardFactory;
import expat.model.board.ModelHexFactory;

import java.util.ArrayList;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ModelApp {
    private PaneBoardController boardController;
    private PanePlayerController playerController;
    private PaneActionController actionController;
    private PaneMatesController matesController;
    private ControllerMainStage mainController;
    private ModelBoard board;
    private int diceNumber;
    private ArrayList<ModelPlayer> players;

    public ModelApp(ControllerMainStage mainController, PaneBoardController boardController, PaneMatesController matesController, PaneActionController actionController, PanePlayerController playerController) {
        this.boardController = boardController;
        this.actionController = actionController;
        this.matesController = matesController;
        this.playerController = playerController;
        this.mainController = mainController;
        ModelBoardFactory boardGenerator = new ModelBoardFactory(9, 7);
        this.board = boardGenerator.generateBoard();
    }

    /**
     * 1. dice
     * 2. resource distribution
     */
    public void resourceStep() {
        ModelThrowDice throwDice = new ModelThrowDice();
        diceNumber = throwDice.getRandomDiceNumber();
        if (diceNumber != 7) {
            board.resourceOnDiceEvent(diceNumber);
        }


    }

    /**
     * 1. domestic trade (playertrade)
     * 2. sea trade (2:1, 3:1, 4:1)
     */
    public void tradeStep() {

    }

    /**
     * 1. rolled 7 (no resources, >7 cards drop, raider move, resource robbery)
     * 2. play development cards (knights, development, victory)
     */
    public void specialStep() {
        if (diceNumber==7){
            //TODO: do something. take one random material from a player.
        }
    }
    /**
     * changes player, so next player can doo all stepps.
     *
     */
    public void nextPlayer(){

    }

    /**
     * calculates all winpoints and checks if somebody has won.
     */
    public void gameOver() {

    }

    public ModelBoard getBoard() {
        return board;
    }
}
