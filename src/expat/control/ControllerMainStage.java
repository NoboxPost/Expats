package expat.control;


import expat.control.ControllerBoardPane;
import expat.model.ModelApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ControllerMainStage {

    @FXML public BorderPane borderPane;
    @FXML AnchorPane boardPane;
    @FXML ControllerBoardPane controllerBoardPane;
    private ModelApp app;


    public void initialize() {
        controllerBoardPane.init(this);
        app = new ModelApp(this, controllerBoardPane);
        controllerBoardPane.drawBoard(app.getBoard());
    }
}
