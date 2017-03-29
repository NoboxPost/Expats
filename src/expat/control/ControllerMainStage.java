package expat.control;

import expat.model.ModelApp;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ControllerMainStage {

    @FXML public BorderPane borderPane;
    @FXML private AnchorPane boardPane;
    @FXML public ControllerBoardPane controllerBoardPane;
    private ModelApp app;


    public void initialize() {

        app = new ModelApp(this, controllerBoardPane);

    }
}
