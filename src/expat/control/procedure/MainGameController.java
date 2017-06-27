package expat.control.procedure;

import expat.model.ModelApp;
import expat.model.ModelPlayer;

import java.util.LinkedList;

/**
 * Created by gallatib on 22.06.2017.
 */
public class MainGameController extends GameController {

    public MainGameController(ModelApp app) {
        super(app);
    }

    public void nextStepSelector(){
        switch (currentStep){
            case "diceResultStep":
                tradeStep();
                break;
            case "trade":
                buildStep();
                break;
            case "build":
                endTurnStep();
        }
    }

    @Override
    public void startTurnStep(){
        currentStep = "startTurn";
        app.mainGameNextPlayer();
        rollDiceStep();
    }

    public void rollDiceStep(){
        currentStep = "rollDice";
        paneActionController.drawRollDiceStep();
        app.rollDice();
        app.distributeMaterial();
        refreshPlayerInformation();
    }

    public void diceResultStep(){
        currentStep = "diceResultStep";

        //TODO: now the player must always navigate through the dropMaterialStep when there is a rolled 7, maybe extend this method to check if he has more than 7 materials
        if (app.getCurrentDiceNumber() == 7) {
            paneActionController.drawDiceResultNormalStep(app.getCurrentDiceNumbersSeparately(), app.getNowPlayingDicedMaterial());
        } else {
            paneActionController.drawDiceResultSpecialStep(app.getCurrentDiceNumbersSeparately(), app.getNowPlayingDicedMaterial());
        }
    }

    //TODO: reconsider this method
    public void moveRaider(int[] coords) {
        app.moveRaider(coords);
        paneBoardController.generateRaiderGroup(app.getBoard());
    }

    public void dropMaterialStep(){
        currentStep = "dropMaterial";
        LinkedList<ModelPlayer> playersThatMustDrop = app.getPlayersThatMustDrop();

        for(ModelPlayer playerThatMustDrop : playersThatMustDrop){
            int[] dropAmount = playerThatMustDrop.getMaterial().getMaterialAmount();
            //paneActionController.drawDropMaterialStep(dropAmount, playerThatMustDrop.getPlayerName());
        }

        app.getBoard().activateRaider();
        paneActionController.drawMoveRaiderStep();
    }

    public void tradeStep(){
        currentStep = "trade";

    }

    public void buildStep(){
        currentStep = "build";

    }

    public void specialStep(){
        currentStep = "special";

        /*
        if (!currentStep.equals("SpecialStep") && playersMustDrop.isEmpty()) {
            for (ModelPlayer player : players) {
                if (player.getMaterial().getSumOfAllMaterials() > 7) {
                    playersMustDrop.add(player);
                }
            }
        } else if (currentStep.equals("SpecialStep") && playersMustDrop.isEmpty()) {
            board.activateRaider();
        }
        currentStep = "SpecialStep";
        diceNumber = 0;
         */
    }

    //TODO: Siegpane schreiben
    @Override
    public void endTurnStep(){
        currentStep = "endTurn";
        if(!app.currentPlayerIsTheWinner()) {
            startTurnStep();
        } else{}
    }

    @Override
    public void initiateBoardElementPlacing(String type) {
        app.initiateMainGamePlacingAction(type, false);
    }

    @Override
    public void finishBoardElementPlacing(int[] coords, String type) {
        app.finishPlacingAction(coords, type);
        refreshBoardElements();
    }

}



/*

public void nextPlayer() {
    board.abortBuildingAction();
    players.add(players.poll());
    currentPlayer = players.peek();
}

public void gameOver() {
    //TODO: implement game over condition
}
 */
