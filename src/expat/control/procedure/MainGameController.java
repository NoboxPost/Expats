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
            case "tradingStep":
                buildingStep();
                break;
            case "buildingStep":
                endTurnStep();
                break;
        }
    }

    @Override
    public void startTurnStep(){
        currentStep = "startTurn";

        app.nextMainGamePlayer();
        rollDiceStep();
    }

    public void rollDiceStep(){
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
        paneBoardController.generateDiceButtonEnabledGroup(app.getBoard());
        refreshBoardElements();
    }

    public void firstPlayerDroppingMaterialStep(){
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

    //TODO: reconsider this method
    public void finishMoveRaider(int[] coords) {
        paneActionController.drawMoveRaiderStep();
        app.moveRaider(coords);
        paneBoardController.generateRaiderGroup(app.getBoard());
        paneBoardController.generateDiceButtonDisabledGroup(app.getBoard());
        refreshBoardElements();
    }

    private void tradeChoiceStep(){
        currentStep = "tradingStep";
        paneActionController.drawTradeChoiceStep();
    }

    public void commonTradeStep(){
        app.newTradeAction("CommonTrade");
        paneActionController.drawCommonTradeStep(app.getCurrentPlayer().getMaterial().getMaterialAmount());
    }

    public void buildingStep(){
        currentStep = "buildingStep";
        paneActionController.drawMainGameBuildingStep(app.getModelMainGamePlayerBuildingAbilitiesCalculator().playerBuildingAbilities(app.getCurrentPlayer().getMaterial().getMaterialAmount()));
        refreshBoardElements();

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
        if(type.equals("Settlement")){
            paneBoardController.generateBuildingPlacingSpotGroup(app.getBoard().getModelMainGameBuildingListCrawler().settlementsAPlayerCouldBuild(app.getCurrentPlayer()));
        }
        else if(type.equals("Road")){
            paneBoardController.generateConnectionPlacingSpotGroup(app.getBoard().getModelMainGameBuildingListCrawler().roadsAPlayerCouldBuild(app.getCurrentPlayer()));
        }
        else if(type.equals("Town")){
            paneBoardController.generateBuildingPlacingSpotGroup(app.getBoard().getModelMainGameBuildingListCrawler().townsAPlayerCouldBuild(app.getCurrentPlayer()));
        }
    }

    @Override
    public void finishBoardElementPlacing(int[] coords, String type) {
        app.finishPlacingAction(coords, type);
        paneActionController.setTurnNavigationActive();
        refreshBoardElements();
        paneBoardController.drawBoard(app.getBoard());
        buildingStep();
    }

    public void finishDropping(int[] droppingDifference){
        app.getCurrentPlayer().addMaterial(new ModelMaterial(droppingDifference));
        nextPlayerDroppingMaterialStep();
    }

    public void finishTrading(int[] tradingDifference) {
        app.getCurrentPlayer().addMaterial(new ModelMaterial(tradingDifference));
        refreshPlayerInformation();
        tradeChoiceStep();
    }

    public void cancelBuildingAction(){
        paneActionController.setTurnNavigationActive();
        refreshBoardElements();
        paneBoardController.drawBoard(app.getBoard());
        buildingStep();
    }
}
