package expat.control.procedure;

import expat.model.ModelApp;
import expat.model.ModelMaterial;

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
                tradeChoiceStep();
                break;
            case "tradeChoice":
                buildStep();
                break;
            case "build":
                endTurnStep();
        }
    }

    @Override
    public void startTurnStep(){
        currentStep = "startTurn";
        app.nextMainGamePlayer();
        rollDiceStep();
    }

    public void rollDiceStep(){
        currentStep = "rollDice";
        refreshPlayerInformation();

        paneActionController.drawRollDiceStep();
        app.rollDice();
        app.distributeMaterial();
    }

    public void diceResultStep(){
        currentStep = "diceResultStep";
        refreshPlayerInformation();

        if (app.getCurrentDiceNumber() == 7) {
            app.generatePlayersThatMustDrop();
            specialStep();
        }
        else {
            paneActionController.drawDiceResultNormalStep(app.getCurrentDiceNumbersSeparately(), app.getNowPlayingDicedMaterial());
        }
    }

    private void specialStep(){
        currentStep = "special";

        int[] currentDiceNumbersSeparately = app.getCurrentDiceNumbersSeparately();
        ModelMaterial nowPlayingDicedMaterial = app.getNowPlayingDicedMaterial();

        if(!app.getPlayersThatMustDrop().isEmpty()){
            paneActionController.drawDiceResultDroppingStep(currentDiceNumbersSeparately, nowPlayingDicedMaterial);
        }else{
            paneActionController.drawDiceResultRaiderStep(currentDiceNumbersSeparately, nowPlayingDicedMaterial);
        }
    }

    public void moveRaiderStep(){
        app.currentMainGamePlayer();
        refreshPlayerInformation();
        app.getBoard().activateRaider();
        paneActionController.drawMoveRaiderStep();
    }

    public void firstPlayerDroppingMaterialStep(){
        currentStep = "droppingMaterial";
        app.nextDroppingPlayer();
        refreshPlayerInformation();
        paneActionController.drawDropMaterialStep(app.amountPlayerMustDrop(), app.getCurrentPlayer().getMaterial().getMaterialAmount());
    }

    public void nextPlayerDroppingMaterialStep(){
        if(app.getPlayersThatMustDrop().isEmpty()){
            moveRaiderStep();
        }else{
            app.nextDroppingPlayer();
            refreshPlayerInformation();
            paneActionController.drawDropMaterialStep(app.amountPlayerMustDrop(), app.getCurrentPlayer().getMaterial().getMaterialAmount());
        }
    }

    public void finishDropping(int[] droppingDifference){
        app.getCurrentPlayer().addMaterial(new ModelMaterial(droppingDifference));
        nextPlayerDroppingMaterialStep();
    }

    //TODO: reconsider this method
    public void moveRaider(int[] coords) {
        paneActionController.drawMoveRaiderStep();
        app.moveRaider(coords);
        paneBoardController.generateRaiderGroup(app.getBoard());
    }

    private void tradeChoiceStep(){
        currentStep = "tradeChoice";
        paneActionController.drawTradeChoiceStep();
    }

    public void commonTradeStep(){
        currentStep = "commonTradeChoice";
        app.newTradeAction("CommonTrade");
        paneActionController.drawCommonTradeStep(app.getCurrentPlayer().getMaterial().getMaterialAmount());
    }

    public void buildStep(){
        currentStep = "build";

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
        app.initiatePlacingAction(type, false);
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
