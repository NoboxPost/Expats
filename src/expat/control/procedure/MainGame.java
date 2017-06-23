package expat.control.procedure;

import expat.model.ModelApp;
import expat.model.ModelPlayer;

/**
 * Created by gallatib on 22.06.2017.
 */
public class MainGame extends Game {

    public MainGame(ModelApp app) {
        super(app);
    }

    @Override
    public void processAllTurnSteps(ModelPlayer player) {
        rollDiceTurnStep();
        buildTurnStep();
        resourceTurnStep();
        tradeTurnStep();
        specialTurnStep();
    }

    private void rollDiceTurnStep(){

    }

    private void buildTurnStep(){

    }

    private void resourceTurnStep(){

    }

    private void tradeTurnStep(){

    }

    private void specialTurnStep(){

    }
}
