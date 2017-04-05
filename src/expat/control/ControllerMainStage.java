package expat.control;

import expat.model.ModelApp;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * the main scene that works as collection-pool for all included scenes and also starts the App
 * <p>
 * created on 22.03.2017
 *
 * @author vanonir
 */

//TODO: Mainstage? Or MainPane/Scene?

public class ControllerMainStage {
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


    /**
     * Runs after initialization of all controllers, initializes an app and starts model logic.
     * Is called by FXMLLoader and can't be changed.
     */
    public void initialize() {
        app = new ModelApp(this, paneBoardController, paneMatesController, paneActionController, panePlayerController);
        paneBoardController.init(this,app);
        paneActionController.init(this,app);
        panePlayerController.init(this, app);
        paneMatesController.init(app);
        paneBoardController.drawBoard(app.getBoard());
        app.gameBegin();
        paneActionController.refreshStep();
        panePlayerController.refresh();
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

    public void refreshActionStep() {
        paneActionController.refreshStep();
    }

    public void refreshGameInformations() {
        panePlayerController.refresh();
        paneMatesController.setMatesInformation();
    }

    public void raiderMoved() {
        paneActionController.raiderMoved();
    }
}

