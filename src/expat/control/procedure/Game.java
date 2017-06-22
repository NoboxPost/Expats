package expat.control.procedure;

import expat.model.ModelApp;
import expat.model.ModelPlayer;

import java.util.LinkedList;

/**
 * Created by gallatib on 22.06.2017.
 */


public abstract class Game {

    private boolean stillPlaying;
    private ModelApp app;
    private LinkedList<ModelPlayer> players;


    public Game(ModelApp app) {
        this.app = app;
        players = app.getPlayers();

        turnBasedGameProcedure();
    }

    private void turnBasedGameProcedure(){
        while(stillPlaying){
            for(ModelPlayer player : players){
                processAllTurnSteps(player);
            }
        }
    }

    public abstract void processAllTurnSteps(ModelPlayer player);
}
