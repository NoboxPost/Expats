package expat.model;

import expat.control.*;
import expat.model.board.ModelBoard;
import expat.model.board.ModelBoardFactory;

import java.util.ArrayList;

/**
 * is responsible for the game procedure
 * <p>
 * therefore it's a collection of:
 * - all the FXML-controlles
 * - the board
 * - all the players
 * <p>
 * this class is built intentionally like the player procedure
 * - dice and material distirbution
 * - trading
 * - building
 * - events
 * created on 22.03.2017
 *
 * @author vanonir
 */
public class ModelApp {
    private PaneBoardController boardController;
    private PanePlayerController playerController;
    private PaneActionController actionController;
    private PaneMatesController matesController;
    private ControllerMainStage mainController;
    private ModelBoard board;
    private int diceNumber;
    private ArrayList<ModelPlayer> players = new ArrayList<>();
    private ModelPlayer nowPlaying;
    private ModelBuildingAction buildingAction;

    public ModelApp(ControllerMainStage mainController, PaneBoardController boardController, PaneMatesController matesController, PaneActionController actionController, PanePlayerController playerController) {
        this.boardController = boardController;
        this.actionController = actionController;
        this.matesController = matesController;
        this.playerController = playerController;
        this.mainController = mainController;
        ModelBoardFactory boardGenerator = new ModelBoardFactory(9, 7);
        this.board = boardGenerator.generateBoard();
    }

    public void generatePlayer() {
        ModelPlayerGenerator playerGen = new ModelPlayerGenerator();
        ModelPlayer player = playerGen.newPlayer();
        players.add(player);
        nowPlaying = player;
    }

    /**
     * handles the beginning of the game
     * <p>
     * - players choose the position of their first two settlements
     * - they get the materials from all flanking hexes
     */
    public void gameBegin() {
        generatePlayer();
        generatePlayer();
        actionController.drawBuildStep();
    }


    /**
     * is the first player-step that distributes materials
     * <p>
     * 1. dice
     * 2. material distribution
     */
    public void resourceStep() {
        ModelThrowDice throwDice = new ModelThrowDice();
        diceNumber = throwDice.getRandomDiceNumber();
        if (diceNumber != 7) {
            board.resourceOnDiceEvent(diceNumber);
        }


    }

    /**
     * is the second player-step that handles trading
     * <p>
     * 1. domestic trade (playertrade)
     * 2. sea trade (2:1, 3:1, 4:1)
     */
    public void tradeStep() {

    }

    /**
     * is the third player-step that handles special events
     * <p>
     * 1. rolled 7 (no resources, >7 cards drop, raider move, resource robbery)
     * 2. play development cards (knights, development, victory)
     */
    public void specialStep() {
        if (diceNumber == 7) {
            //TODO: do something. take one random material from a player.
        }
    }

    /**
     * changes player, so next player can doo all stepps.
     */
    public void nextPlayer() {
    }

    /**
     * handles the end of the game
     */
    public void gameOver() {


    }


    public void newBuildingAction(String type) {
        buildingAction = new ModelBuildingAction(nowPlaying,type,board.getBuildings(),board.getConnections());
    }
    public void injectNewBuildingCoords(int[] coords,String type){
        if (buildingAction!=null) {
            buildingAction.createBuilding(coords, type);
            boardController.refreshBoardElements(board);
            buildingAction = null;
            actionController.drawBuildStep();
        }
    }

    public ModelBoard getBoard() {
        return board;
    }

    public ModelPlayer getNowPlaying() {
        return nowPlaying;
    }

    /**
     * returns current buildingAction,
     *
     * @return buildingAction can be null, so needs to be checked.
     */
    public ModelBuildingAction getBuildingAction() {
        return buildingAction;
    }
}


