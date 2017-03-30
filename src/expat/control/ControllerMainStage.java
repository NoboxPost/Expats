package expat.control;

import expat.model.ModelApp;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ControllerMainStage {
    @FXML
    private ScrollPane scrollPaneCenter;
    @FXML BorderPane borderPane;
    @FXML
    StackPane paneBoard;
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
        paneBoardController.init(this);

    }
    public void adjustScrollPaneCenter(double height, double width){
//        scrollPaneCenter.setMinViewportHeight(1000);
//        scrollPaneCenter.setMinViewportWidth(1000);
        }
        //System.out.print(scrollPaneCenter.getViewportBounds());


}
