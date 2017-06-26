package expat.control.panes;

import expat.control.procedure.MainGameController;
import expat.control.procedure.PreGameController;
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

    private PreGameController preGame;
    private MainGameController mainGame;
    private ModelApp app;


    //TODO: drawCreateGame as bottom-action should get reconsidered because File - New Game would be more intuitive

    /**
     * Runs after initialization of all controllers, initializes an app and starts model logic.
     * Is called by FXMLLoader and can't be changed.
     */
    public void initialize() {
        app = new ModelApp();
        initializeControllers();
    }

    private void initializeControllers(){
        //Control-View
        paneBoardController.init(this, mainGame, preGame);
        paneActionController.init(this, mainGame, preGame);
        panePlayerController.init(mainGame);
        paneMatesController.init(mainGame);

        //Control-Model
        preGame = new PreGameController(app, this, paneActionController, paneBoardController, paneMatesController, panePlayerController);
        mainGame = new MainGameController(app, this, paneActionController, paneBoardController, paneMatesController, panePlayerController);
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
     * Enables buttons for next step and end turn again, after paneActionController waitet for Raider to be moved.
     */
    public void raiderMoved() {
        paneActionController.raiderMoved();
    }
}

