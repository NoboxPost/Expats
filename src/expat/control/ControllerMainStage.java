package expat.control;

import expat.model.ModelApp;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ControllerMainStage {

    @FXML BorderPane borderPane;
    @FXML AnchorPane paneBoard;
    @FXML VBox panePlayer;
    @FXML VBox paneMates;
    @FXML AnchorPane paneAction;
    @FXML
    PaneBoardController paneBoardController;
    @FXML
    PaneActionController paneActionController;
    @FXML
    PaneMatesController paneMatesController;
    @FXML
    PanePlayerController panePlayerController;
    private ModelApp app;


    public void initialize() {
        app = new ModelApp(this, paneBoardController, paneMatesController, paneActionController, panePlayerController);
        paneBoardController.drawBoard(app.getBoard());
    }
}
