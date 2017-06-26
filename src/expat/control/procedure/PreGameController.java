package expat.control.procedure;

import expat.control.panes.*;
import expat.model.ModelApp;

/**
 * Created by gallatib on 22.06.2017.
 */
public class PreGameController extends GameController {

    private int numberOfPlayerTurns = 0;

    public PreGameController(ModelApp app, MainStageController mainStageController, PaneActionController paneActionController, PaneBoardController paneBoardController, PaneMatesController paneMatesController, PanePlayerController panePlayerController) {
        super(app, mainStageController, paneActionController, paneBoardController, paneMatesController, panePlayerController);

        paneBoardController.drawBoard(app.getBoard());
        paneActionController.drawCreateGame();
    }

    @Override
    public void startTurnStep() {
        numberOfPlayerTurns += 1;
        app.preGameNextPlayer();
    }

    public void setBuildingStep(){
        paneActionController.drawPreGameBuildingStep();
    }

    public void setConnectionStep(){
        paneActionController.drawPreGameRoadStep();
    }


    @Override
    public void endTurnStep() {
        //each player must set two connections and two buildings
        if(numberOfPlayerTurns < 2*(app.getPlayers().size())){
            startTurnStep();
        }
        else{

        }

    }

    @Override
    public void createElementOnBoard() {

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
