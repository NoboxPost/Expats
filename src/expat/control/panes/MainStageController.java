package expat.control.panes;

import expat.control.procedure.MainGame;
import expat.model.ModelApp;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * the main scene that works as collection-pool for all included scenes and also starts the App
 * <p>
 * created on 22.03.2017
 *
 * @author vanonir
 */
public class MainStageController {
    @FXML
    private ScrollPane scrollPaneCenter;
    @FXML
    BorderPane borderPane;
    @FXML
    StackPane paneBoard;
    @FXML
    VBox panePlayer;
    @FXML
    VBox paneMates;
    @FXML
    AnchorPane paneAction;
    @FXML
    PaneBoardController paneBoardController;
    @FXML
    PaneActionController paneActionController;
    @FXML
    PaneMatesController paneMatesController;
    @FXML
    PanePlayerController panePlayerController;
    private ModelApp app;
    private MainGame mainGame;


    /**
     * Runs after initialization of all controllers, initializes an app and starts model logic.
     * Is called by FXMLLoader and can't be changed.
     */
    public void initialize() {
        app = new ModelApp();
        initializeControllers();
        mainGame = new MainGame(this, app);
    }

    private void initializeControllers(){
        paneBoardController.init(this,app);
        paneActionController.init(this,app);
        panePlayerController.init(this, app);
        paneMatesController.init(app);
    }

    /**
     * adjusts the scrollbar when zooming
     * @param width
     * @param height
     */
    public void adjustScrollPaneCenter(double width, double height) {
        scrollPaneCenter.setHvalue(width);
        scrollPaneCenter.setVvalue(height);
        // TODO: entfernen (Testing): System.out.println("vValue: " + scrollPaneCenter.getVvalue() + " hValue: " + scrollPaneCenter.getHvalue());
    }

    /**
     * Refreshes Action-Pane, where the different game steps are displayed
     */
    public void refreshActionPane() {
        paneActionController.refresh();
    }

    /**
     * Refreshes Mates-Pane and Player-Pane
     * Mates: info about game process
     * Player: infos about the current player
     *
     * not all panes are refreshed at the same time because of ressource-reasons
     */
    public void refreshMatesAndPlayerPanes() {
        panePlayerController.refresh();
        paneMatesController.refresh();
    }

    /**
     * Enables buttons for next step and end turn again, after paneActionController waitet for Raider to be moved.
     */
    public void raiderMoved() {
        paneActionController.raiderMoved();
    }
}

