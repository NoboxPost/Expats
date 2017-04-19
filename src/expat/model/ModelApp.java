package expat.model;

import expat.model.board.ModelBoard;
import expat.model.board.ModelBoardFactory;
import expat.model.board.ModelHex;
import expat.model.buildings.ModelBuilding;

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
 * - dice and material distirbution
 * - trading
 * - building
 * - events
 * created on 22.03.2017
 *
 * @author vanonir
 */
public class ModelApp {
    private ModelBoard board;
    private int diceNumber;
    private ModelDiceRolling diceRolling;
    private ModelPlayer localPlayer;
    private String currentStep;
    private ModelMaterial nowPlayingDicedMaterial;
    private int firstBuildingStep = 0;
    private ModelTradeAction tradeAction;
    private LinkedList<ModelPlayer> playersMustDrop = new LinkedList<>();
    private ModelPlayerHandler playerHandler;


    /**
     * constuctor for app. generates a default board of widht 9 and height 7.
     */
    public ModelApp() {
        ModelBoardFactory boardGenerator = new ModelBoardFactory(9, 7);
        this.board = boardGenerator.generateBoard();
    }



    /**
     * handles the beginning of the game
     * <p>
     * - players choose the position of their first two settlements
     * - they get the materials from all flanking hexes
     */
    public void gameBegin() {


        playerHandler = new ModelPlayerHandler(2, board);
        currentStep = "FirstBuildingStep";
    }

    /**
     * last player-step where player can build new buildings.
     */
    public void buildingStep() {
        currentStep = "BuildingStep";
    }


    /**
     * is the first player-step that distributes materials
     * <p>
     * 1. dice
     * 2. material distribution
     */
    public void resourceStep() {
        currentStep = "ResourceStep";
        diceNumber = 0;
        diceRolling = null;
    }

    /**
     * Rolls the dice and initiates distribution of materials. If dice number is 7 no materials will be distributed.
     */
    public void rollDice() {
        diceRolling = new ModelDiceRolling();
        diceNumber = diceRolling.getRolledDices();
        if (diceNumber != 7) {
            ModelMaterial materialBefore = new ModelMaterial();
            materialBefore.addMaterial(playerHandler.getCurrentPlayer().getMaterial());
            board.resourceOnDiceEvent(diceNumber);
            nowPlayingDicedMaterial = new ModelMaterial();
            nowPlayingDicedMaterial.addMaterial(playerHandler.getCurrentPlayer().getMaterial());
            nowPlayingDicedMaterial.reduceMaterial(materialBefore);
        } else {
            nowPlayingDicedMaterial = new ModelMaterial();
        }
    }

    /**
     * is the second player-step that handles trading
     * <p>
     * 1. domestic trade (playertrade)
     * 2. sea trade (2:1, 3:1, 4:1)
     */
    public void tradeStep() {
        currentStep = "TradeStep";
    }

    /**
     * is the third player-step that handles special events
     * <p>
     * 1. rolled 7 (no resources, >7 cards drop, raider move, resource robbery)
     * 2. play development cards (knights, development, victory)
     */
    public void specialStep() {
        if (!currentStep.equals("SpecialStep") && playersMustDrop.isEmpty()) {
            for (ModelPlayer player : playerHandler.getPlayers()) {
                if (player.getMaterial().getSumOfAllMaterials() > 7) {
                    playersMustDrop.add(player);
                }
            }
        } else if (currentStep.equals("SpecialStep") && playersMustDrop.isEmpty()) {
            if (!board.getRaider().isAllowRaid()){
                board.activateRaider();
            }
        }
        currentStep = "SpecialStep";
        diceNumber = 0;
    }

    /**
     * Reduces the amount of material dropped after after raider event (dice =7) according to given int array with differences to be added.
     *
     * @param endDifference
     */
    public void playerDroppedMaterial(int[] endDifference) {
        playersMustDrop.poll().addMaterial(new ModelMaterial(endDifference));
    }

    /**
     * Changes player, so next player can doo all steps.
     */
    public void nextPlayer() {
        board.abortBuildingAction();
        playerHandler.nextPlayer();
    }

    /**
     * handles the end of the game , currently unused.
     */
    public void gameOver() {
        //TODO: implement game over condition
    }

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
        tradeAction = new ModelTradeAction(type, playerHandler.getCurrentPlayer());
    }

    /**
     * Injects integer array representing
     *
     * @param materialResultSender
     */
    public void finishTradeAction(int[] materialResultSender) {
        tradeAction.finishTradeAction(materialResultSender);
        tradeAction = null;
    }

    /**
     * Frees the reference to a ModelTradeAction, if player decides to abort the step or end his turn prior to ending the action.
     */
    public void resetTrade() {
        tradeAction = null;
    }

    /**
     * Createsa a new ModelBuildingAction with parameter firstStage = true, so building and connections won't cost anything.
     *
     * @param type
     */
    public void firstBuildingAction(String type) {
        board.firstBuildingAction(type, playerHandler.getCurrentPlayer());
    }


    /**
     * Initiates
     *
     * @param type
     */
    public void newBuildingAction(String type) {
        board.newBuildingAction(type, playerHandler.getCurrentPlayer());
    }

    /**
     * In first part, it will check state of game, firstStage, so building will not cost anything or otherwise normal building.
     * During first stage it will automaticaly sets next player active, so all can build their first buildings and connections.
     *
     * @param coords coordinates of building which will be built.
     * @param type building Type
     */
    public void finishesBuildingActionAndChangesToNextPlayerIfNeeded(int[] coords, String type) {
        if ((countConnectionsForCurrentPlayer() == firstBuildingStep + 1 && countBuildingsForCurrentPlayer() == firstBuildingStep + 1) && firstBuildingStep < 2) {
            playerHandler.nextPlayer();
        }
        if (firstBuildingStep >= 2 || (countBuildingsForCurrentPlayer() == firstBuildingStep && type.equals("Building") || countConnectionsForCurrentPlayer() == firstBuildingStep && type.equals("Connection"))) {
            if (board.finishBuildingAction(coords, type)) {
                if (type.equals("Building")) {
                    playerHandler.getCurrentPlayer().changeVictoryPoints(1);
                }
            }
        }
        boolean allOnSameFirstBuildingStep = true;
        if (firstBuildingStep < 2) {
            for (ModelPlayer player : playerHandler.getPlayers()) {
                if (!(board.countBuildingsOwned(player) == firstBuildingStep + 1 && board.countConnectionsOwned(player) == firstBuildingStep + 1)) {
                    allOnSameFirstBuildingStep = false;
                }
            }
            if (allOnSameFirstBuildingStep) {
                firstBuildingStep += 1;
                playerHandler.nextPlayer();
            }
            if (firstBuildingStep >= 2) {
                resourceStep();
            }
        }
        if ((countConnectionsForCurrentPlayer() == firstBuildingStep + 1 && countBuildingsForCurrentPlayer() == firstBuildingStep + 1) && firstBuildingStep < 2) {
            playerHandler.nextPlayer();
        }
    }

    /**
     * Hands given coordinates over to board so raider can be moved to corresponding hex.
     *
     * @param coords coordinates fo hex where raider will be moved to.
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
            board.getRaider().setAllowRaid(true);
            board.getRaider().setAllowMovement(false);
        }
    }

    public void takeMaterialFromPlayerAndGiveItToCurrentPlayer(int[] coords){

        for (ModelBuilding building : board.getBuildings()){
            if (building.checkCoords(coords)){
                playerHandler.getCurrentPlayer().addMaterial(building.getOwner().takeRandomMaterial());
            }
        }
        board.getRaider().setAllowRaid(false);
    }


    /**
     * counts all buildings for current player
     *
     * @return sum of buildings
     */
    private int countBuildingsForCurrentPlayer() {
        return board.countBuildingsOwned(playerHandler.getCurrentPlayer());
    }

    /**
     * counts all connections for current player
     *
     * @return sum of connections
     */
    private int countConnectionsForCurrentPlayer() {
        return board.countConnectionsOwned(playerHandler.getCurrentPlayer());
    }

    /**
     * getter
     *
     * @return
     */
    public LinkedList<ModelPlayer> getPlayersMustDrop() {
        return playersMustDrop;
    }

    /**
     * getter
     *
     * @return
     */
    public String getCurrentStep() {
        return currentStep;
    }

    /**
     * getter
     *
     * @return
     */
    public int getDiceNumber() {
        return diceNumber;
    }

    /**
     * getter
     *
     * @return
     */
    public int[] getDiceNumbersSeparately() {
        return diceRolling.getRolledDicesSeperately();
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
    public int getFirstBuildingStep() {
        return firstBuildingStep;
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
    public ModelPlayer getNowPlaying() {
        return playerHandler.getCurrentPlayer();
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
        return playerHandler.getPlayers();
    }

}


