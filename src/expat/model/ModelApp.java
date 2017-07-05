package expat.model;

import expat.model.board.ModelBoard;
import expat.model.board.ModelBoardFactory;
import expat.model.board.ModelHex;
import expat.model.procedure.ModelPreGameBuildingListCrawler;
import expat.model.procedure.*;

import java.util.LinkedList;

/**
 * is responsible for the game procedure
 * <p>
 * therefore it's a collection of:
 * - all the FXML-controlles
 * - the board
 * - all the allPlayers
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
    private ModelPreGamePlayerHandler modelPreGamePlayerHandler;
    private ModelMainGamePlayerHandler modelMainGamePlayerHandler;
    private ModelDroppingPlayerHandler modelRaiderPlayerHandler;
    private ModelPreGameBuildingListCrawler modelBuildingListCrawler;
    private ModelMainGamePlayerBuildingAbilitiesCalculator modelMainGamePlayerBuildingAbilitiesCalculator;

    private LinkedList<ModelPlayer> allPlayers = new LinkedList<>();
    private LinkedList<ModelPlayer> playersThatMustDrop;

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


    //TODO: x & y size should be in game-creation actionpane
    private void initializeGame() {
        ModelBoardFactory boardGenerator = new ModelBoardFactory(9, 7);
        board = boardGenerator.generateBoard();

        diceRoller = new ModelDiceRoller();
        playerGenerator = new ModelPlayerGenerator();
        modelMainGamePlayerBuildingAbilitiesCalculator = new ModelMainGamePlayerBuildingAbilitiesCalculator();
    }

    /**
     * Generates a new Player with a ModelPlayerGenerator and add it to the player queue.
     */
    public void generatePlayer(int numberOfPlayers) {
        for (int i = 0; i < numberOfPlayers; i++) {
            ModelPlayer player = playerGenerator.newPlayer();
            allPlayers.add(player);
        }
    }

    public void generatePlayerHandler() {
        modelPreGamePlayerHandler = new ModelPreGamePlayerHandler(allPlayers);
        modelMainGamePlayerHandler = new ModelMainGamePlayerHandler(allPlayers);
    }

    /**
     * Rolls the dice and initiates distribution of materials. If dice number is 7 no materials will be distributed.
     */
    public void rollDice() {
        currentDiceNumber = diceRoller.rollDices();
    }


    //Todo: nextPlayer in modelApp is redundant, all you'd need are the handlers
    public void nextPreGamePlayer() {
        modelPreGamePlayerHandler.nextPlayer();
        currentPlayer = modelPreGamePlayerHandler.getCurrentPreGamePlayer();
    }

    public void nextMainGamePlayer() {
        modelMainGamePlayerHandler.nextPlayer();
        currentPlayer = modelMainGamePlayerHandler.getCurrentMainGamePlayer();
    }

    public void currentMainGamePlayer(){
        currentPlayer = modelMainGamePlayerHandler.getCurrentMainGamePlayer();
    }

    public void nextDroppingPlayer(){
        currentPlayer = modelRaiderPlayerHandler.nextPlayer();
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

    /**
     * Initiates
     *
     * @param type
     */
    public void initiatePlacingAction(String type, Boolean isInPreGame) {
        board.newBuildingAction(type, currentPlayer, isInPreGame);
    }

    public int amountPlayerMustDrop(){
        int amountToBeDropped = 0;

        for (int i = 0; i < 5; i++) {
            amountToBeDropped += currentPlayer.getMaterial().getMaterialAmount()[i];
        }

        amountToBeDropped /= 2;

        return amountToBeDropped;
    }

    public void generatePlayersThatMustDrop(){
        modelRaiderPlayerHandler = new ModelDroppingPlayerHandler(modelMainGamePlayerHandler.getPlayers());
        playersThatMustDrop = modelRaiderPlayerHandler.getPlayersThatMustDrop();
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
    public LinkedList<ModelPlayer> getAllPlayers() {
        return allPlayers;
    }


    public void finishPlacingAction(int[] coords, String type) {
        if (board.finishBuildingAction(coords, type)) {
            if (type.equals("Building")) {
                currentPlayer.changeVictoryPoints(1);
            }
        }
    }

    /**
     * gets modelMainGamePlayerBuildingAbilitiesCalculator
     *
     * @return value of modelMainGamePlayerBuildingAbilitiesCalculator
     */
    public ModelMainGamePlayerBuildingAbilitiesCalculator getModelMainGamePlayerBuildingAbilitiesCalculator() {
        return modelMainGamePlayerBuildingAbilitiesCalculator;
    }
}


