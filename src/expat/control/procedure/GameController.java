package expat.control.procedure;

import expat.control.panes.*;
import expat.model.ModelApp;
import expat.model.ModelPlayer;
import javafx.scene.layout.Pane;

import java.util.LinkedList;

/**
 * Created by gallatib on 22.06.2017.
 */


public abstract class GameController {

    protected boolean stillPlaying;
    protected ModelApp app;
    protected LinkedList<ModelPlayer> players;
    protected ModelPlayer currentPlayer;
    protected String currentStep;
    protected MainStageController mainStageController;
    protected PaneActionController paneActionController;
    protected PaneBoardController paneBoardController;
    protected PaneMatesController paneMatesController;
    protected PanePlayerController panePlayerController;


    public GameController(MainStageController mainStageController, PaneActionController paneActionController, PaneBoardController paneBoardController, PaneMatesController paneMatesController, PanePlayerController panePlayerController) {
        this.mainStageController = mainStageController;
        this.paneActionController = paneActionController;
        this.paneBoardController = paneBoardController;
        this.paneMatesController = paneMatesController;
        this.panePlayerController = panePlayerController;

        app = new ModelApp();
        players = app.getPlayers();

        turnBasedGameProcedure();
    }

    private void turnBasedGameProcedure(){
    }

    protected abstract void processAllTurnSteps(ModelPlayer player);
}
