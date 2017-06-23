package expat.control.procedure;

import expat.model.ModelApp;
import expat.model.ModelPlayer;

/**
 * Created by gallatib on 22.06.2017.
 */
public class PreGame extends Game {

    public PreGame(ModelApp app) {
        super(app);
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
