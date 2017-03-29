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

    @FXML public BorderPane borderPane;
    @FXML private AnchorPane boardPane;
    @FXML private VBox vBoxPlayer;
    @FXML private VBox vBoxMates;
    @FXML private AnchorPane anchorPaneAction;
    @FXML private ControllerBoardPane controllerBoardPane;
    @FXML private ControllerPaneAction controllerPaneAction;
    @FXML private ControllerPaneMates controllerPaneMates;
    @FXML private ControllerPanePlayer controllerPanePlayer;
    private ModelApp app;


    public void initialize() {

        app = new ModelApp(this, controllerBoardPane, controllerPaneMates, controllerPaneAction, controllerPanePlayer);



    }
}
