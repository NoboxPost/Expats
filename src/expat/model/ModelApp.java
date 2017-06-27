package expat.model;

import expat.model.board.ModelBoard;
import expat.model.board.ModelBoardFactory;
import expat.model.board.ModelHex;
import expat.model.procedure.*;

import java.util.LinkedList;

/**
 * is responsible for the game procedure
 * <p>
 * therefore it's a collection of:
 * - all the FXML-controlles
 * - the board
 * - all the players
 * <p>
 * this class is built intentionally like the player procedure
 * - dice and material distribution
 * - trading
 * - building
 * - events
 * created on 22.03.2017
 *
 * @author vanonir
 */
public class ModelApp {
    //components
    private ModelBoard board;
    private ModelDiceRoller diceRoller;
    private ModelPlayerGenerator playerGenerator;
    private ModelPreGamePlayerHandler modelPreGamePlayerSelector;
    private ModelMainGamePlayerHandler modelMainGamePlayerSelector;

    private LinkedList<ModelPlayer> players = new LinkedList<>();
    private LinkedList<ModelPlayer> playersThatMustDrop = new LinkedList<>();
    private ModelTradeAction tradeAction;

    //procedure
    //TODO: currentDiceNumber = like currentPlayer move to Model DiceRoller and make a getter
    private ModelPlayer currentPlayer;
    private int currentDiceNumber;
    private ModelMaterial nowPlayingDicedMaterial;
    private ModelBuildingAction modelBuildingAction;


    /**
     * constuctor for app. generates a default board of widht 9 and height 7.
     */
    public ModelApp() {
        initializeGame();
    }


    //TODO: Initialization
    private void initializeGame() {
        ModelBoardFactory boardGenerator = new ModelBoardFactory(9, 7);
        board = boardGenerator.generateBoard();

        diceRoller = new ModelDiceRoller();
        playerGenerator = new ModelPlayerGenerator();
    }

    /**
     * Generates a new Player with a ModelPlayerGenerator and add it to the player queue.
     */
    public void generatePlayer(int numberOfPlayers) {
        for (int i = 0; i < numberOfPlayers; i++) {
            ModelPlayer player = playerGenerator.newPlayer();
            players.add(player);
        }
    }

    public void generatePlayerHandler() {
        modelPreGamePlayerSelector = new ModelPreGamePlayerHandler(players);
        modelMainGamePlayerSelector = new ModelMainGamePlayerHandler(players);
    }

    /**
     * Rolls the dice and initiates distribution of materials. If dice number is 7 no materials will be distributed.
     */
    public void rollDice() {
        currentDiceNumber = diceRoller.getRolledDices();
    }

    public void preGameNextPlayer() {
        modelPreGamePlayerSelector.nextPlayer();
        currentPlayer = modelPreGamePlayerSelector.getCurrentPreGamePlayer();
    }

    public void mainGameNextPlayer() {
        modelMainGamePlayerSelector.nextPlayer();
        currentPlayer = modelMainGamePlayerSelector.getCurrentMainGameUser();
    }

    public void distributeMaterial() {
        if (currentDiceNumber != 7) {
            ModelMaterial materialBefore = new ModelMaterial();
            materialBefore.addMaterial(currentPlayer.getMaterial());
            board.resourceOnDiceEvent(currentDiceNumber);
            nowPlayingDicedMaterial = new ModelMaterial();
            nowPlayingDicedMaterial.addMaterial(currentPlayer.getMaterial());
            nowPlayingDicedMaterial.reduceMaterial(materialBefore);
        } else {
            nowPlayingDicedMaterial = new ModelMaterial();
        }
    }

    public boolean currentPlayerIsTheWinner() {
        if (currentPlayer.getVictoryPoints() >= 10) {
            return true;
        } else {
            return false;
        }
    }

    //TODO: change

    /**
     * Reduces the amount of material dropped after after raider event (dice =7) according to given int array with differences to be added.
     *
     * @param endDifference
     */
    public void playerDroppedMaterial(int[] endDifference) {
        playersThatMustDrop.poll().addMaterial(new ModelMaterial(endDifference));
    }


    //TODO: change

    /**
     * Initiates a new ModelTradingAction for the current player.
     * Trade types will be:
     * GeneralTrade 4:1
     * PlayerToPlayerTrade ?:?
     * GeneralPortTrade 3:1
     * SpecificPortTrade 2:1
     * <p>
     * Currently only GeneralTrade is implementetd //TODO:Change if other types are implemented.
     *
     * @param type of trading action
     */
    public void newTradeAction(String type) {
        tradeAction = new ModelTradeAction(type, currentPlayer);
    }

    //TODO: change

    /**
     * Injects integer array representing
     *
     * @param materialResultSender
     */
    public void finishTradeAction(int[] materialResultSender) {
        tradeAction.finishTradeAction(materialResultSender);
        tradeAction = null;
    }

    //TODO: change

    /**
     * Frees the reference to a ModelTradeAction, if player decides to abort the step or end his turn prior to ending the action.
     */
    public void resetTrade() {
        tradeAction = null;
    }

    //TODO: change


    /**
     * Initiates
     *
     * @param type
     */
    public void initiateMainGamePlacingAction(String type, Boolean isInPreGame) {
        board.newBuildingAction(type, currentPlayer, isInPreGame);
    }




    /**
     * Hands given coordinates over to board so raider can be moved to corresponding hex.
     *
     * @param coords
     */
    public void moveRaider(int[] coords) {
        if (board.getRaider().getAllowMovement()) {
            for (ModelHex[] hexline : board.getHexes()) {
                for (ModelHex hex : hexline) {
                    if (hex.getCoords()[0] == coords[0] && hex.getCoords()[1] == coords[1]) {
                        board.getRaider().moveRaider(hex);
                    }
                }
            }
            board.getRaider().setAllowMovement(false);
        }
    }


    /**
     * counts all buildings for current player
     *
     * @return sum of buildings
     */
    public int countBuildingsForCurrentPlayer() {
        return board.countBuildingsOwned(currentPlayer);
    }

    /**
     * counts all connections for current player
     *
     * @return sum of connections
     */
    public int countConnectionsForCurrentPlayer() {
        return board.countConnectionsOwned(currentPlayer);
    }

    /**
     * getter
     *
     * @return
     */
    public LinkedList<ModelPlayer> getPlayersThatMustDrop() {
        return playersThatMustDrop;
    }

    /**
     * getter
     *
     * @return
     */
    public int getCurrentDiceNumber() {
        return currentDiceNumber;
    }

    /**
     * getter
     *
     * @return
     */
    public int[] getCurrentDiceNumbersSeparately() {
        return diceRoller.getRolledDicesSeparately();
    }

    /**
     * getter
     *
     * @return
     */
    public ModelBoard getBoard() {
        return board;
    }

    /**
     * getter
     *
     * @return
     */
    public ModelMaterial getNowPlayingDicedMaterial() {
        return nowPlayingDicedMaterial;
    }

    /**
     * getter
     *
     * @return
     */
    public ModelPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * getter
     *
     * @return
     */
    public ModelTradeAction getTradeAction() {
        return tradeAction;
    }

    /**
     * getter
     *
     * @return
     */
    public LinkedList<ModelPlayer> getPlayers() {
        return players;
    }


    public void finishPlacingAction(int[] coords, String type) {
        if (board.finishBuildingAction(coords, type)) {
            if (type.equals("Building")) {
                currentPlayer.changeVictoryPoints(1);
            }
        }
    }


}


