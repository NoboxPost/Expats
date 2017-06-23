package expat.control.procedure;

import expat.control.panes.MainStageController;
import expat.model.ModelApp;
import expat.model.ModelPlayer;

import java.util.LinkedList;

/**
 * Created by gallatib on 22.06.2017.
 */


public abstract class Game {

    protected boolean stillPlaying;
    protected ModelApp app;
    protected LinkedList<ModelPlayer> players;
    protected ModelPlayer currentPlayer;
    protected String currentStep;
    protected MainStageController mainStageController;


    public Game(MainStageController mainStageController, ModelApp app) {
        this.mainStageController = mainStageController;

        this.app = app;
        players = app.getPlayers();

        turnBasedGameProcedure();
    }

    private void turnBasedGameProcedure(){
        while(stillPlaying){
            for(ModelPlayer player : players){
                currentPlayer = player;
                processAllTurnSteps(player);
            }
        }
    }

    protected abstract void processAllTurnSteps(ModelPlayer player);
}
