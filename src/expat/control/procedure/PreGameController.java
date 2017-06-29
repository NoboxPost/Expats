package expat.control.procedure;

import expat.model.ModelApp;

/**
 * Created by gallatib on 22.06.2017.
 */
public class PreGameController extends GameController {

    private int numberOfPlayerTurns = 0;

    public PreGameController(ModelApp app) {
        super(app);
    }

    public void gameSettings(){
        paneActionController.drawGameSettings();
    }

    //Todo: server should be able to choose how many players will participate
    public void buildAndStartGame(int numberOfPlayers){
        app.generatePlayer(numberOfPlayers);
        paneBoardController.drawBoard(app.getBoard());
        app.generatePlayerHandler();
        startTurnStep();
    }

    public void nextStepSelector(){
        switch (currentStep){
            case "setBuilding":
                setConnectionStep();
                break;
            case "setConnection":
                endTurnStep();
                break;
        }
    }

    @Override
    public void startTurnStep() {
        currentStep = "startTurn";
        numberOfPlayerTurns += 1;
        app.preGameNextPlayer();
        refreshPlayerAndMatesInformation();
        setBuildingStep();
    }

    private void setBuildingStep(){
        currentStep = "setBuilding";
        paneActionController.drawPreGameBuildingStep();
    }

    public void setConnectionStep(){
        currentStep = "setConnection";
        paneActionController.drawPreGameRoadStep();
    }


    @Override
    public void endTurnStep() {
        currentStep = "endTurn";

        //each player must set two connections and two buildings
        if(numberOfPlayerTurns < 2*(app.getPlayers().size())){
            startTurnStep();
        }
        else{
            mainStageController.switchGameMode();
        }
    }
    @Override
    public void initiateBoardElementPlacing(String type) {
        app.initiateMainGamePlacingAction(type, true);
        if(type.equals("Settlement")){
            paneBoardController.generateBuildingPlacingSpotGroup(app.getBoard().getModelBuildingListCrawler().settlementsAPlayerCouldBuild());
        }
        else if(type.equals("Road")){
            paneBoardController.generateConnectionPlacingSpotGroup(app.getBoard().getModelBuildingListCrawler().roadsAPlayerCouldBuild(app.getCurrentPlayer()));
        }
    }

    @Override
    public void finishBoardElementPlacing(int[] coords, String type) {
        app.finishPlacingAction(coords, type);
        refreshBoardElements();
        nextStepSelector();
        paneBoardController.drawBoard(app.getBoard());
    }

    /*

    Maybe another stage: Pregame should also handle the number of players?

    generatePlayer();
    generatePlayer();

    firstBuildingActionSequence = new ModelFirstBuildingActionSequence(players, board);
    currentPlayer = firstBuildingActionSequence.getCurrentPlayer();
    currentStep = "FirstBuildingStep";

     */
}
