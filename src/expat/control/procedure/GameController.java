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
    protected String currentStep;
    protected MainStageController mainStageController;
    protected PaneActionController paneActionController;
    protected PaneBoardController paneBoardController;
    protected PaneMatesController paneMatesController;
    protected PanePlayerController panePlayerController;


    public GameController(ModelApp app) {
        this.app = app;
    }

    public abstract void startTurnStep();
    public abstract void endTurnStep();

    public void refreshPlayerInformation(){
        ModelPlayer currentPlayer = app.getCurrentPlayer();
        panePlayerController.refresh(currentPlayer.getColor(), currentPlayer.getWinPointsString(), currentPlayer.getMaterial());
    }

    public void refreshMatesInformation(){
        paneMatesController.refresh(app.getPlayers());
    }

    public void refreshBoardElements(){
        paneBoardController.refreshBoardElements(app.getBoard());
    }

    public void refreshPlayerAndMatesInformation(){
        refreshMatesInformation();
        refreshPlayerInformation();
    }

    public void linkToPanes(MainStageController mainStageController,
                            PaneActionController paneActionController,
                            PaneBoardController paneBoardController,
                            PaneMatesController paneMatesController,
                            PanePlayerController panePlayerController) {

        this.mainStageController = mainStageController;
        this.paneActionController = paneActionController;
        this.paneBoardController = paneBoardController;
        this.paneMatesController = paneMatesController;
        this.panePlayerController = panePlayerController;
    }

    public abstract void initiateBoardElementPlacing(String type);
    public abstract void finishBoardElementPlacing(int[] coords, String type);
}
