package expat.control.procedure;

import expat.control.panes.MainStageController;
import expat.model.ModelPlayer;

/**
 * Created by gallatib on 22.06.2017.
 */
public class PreGameController extends GameController {

    public PreGameController(MainStageController mainStageController) {
        super(mainStageController);
    }

    @Override
    public void processAllTurnSteps(ModelPlayer player) {

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
