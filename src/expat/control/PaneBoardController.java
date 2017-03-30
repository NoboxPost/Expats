package expat.control;

import expat.model.board.ModelBoard;
import expat.view.ViewBuildingFactory;
import expat.view.ViewDiceButtonFactory;
import expat.view.ViewDiceNumber;
import expat.view.ViewHexFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;




/**
 * Created by vanonir on 22.03.2017.
 */
public class PaneBoardController {
    private ControllerMainStage controllerMainStage;

    @FXML
    private AnchorPane anchorPaneBoard;
    private int hexSize = 200;

    public void initialize() {

    }

    public void drawBoard(ModelBoard modelBoard) {
        anchorPaneBoard.getChildren().removeAll();
        ViewHexFactory hexFactory = new ViewHexFactory(hexSize);
        anchorPaneBoard.getChildren().addAll(hexFactory.generateViewHexList(modelBoard.getHexes()));
        ViewDiceButtonFactory diceLabelFactory = new ViewDiceButtonFactory(hexSize,this);
        anchorPaneBoard.getChildren().addAll(diceLabelFactory.generateDiceButtons(modelBoard.getHexes()));
        ViewBuildingFactory viewBuildingFactory = new ViewBuildingFactory(hexSize);
        anchorPaneBoard.getChildren().addAll(viewBuildingFactory.generateBuildings(modelBoard.getHexes()));
    }

    public void hexClicked(ActionEvent event) {
        ViewDiceNumber button = (ViewDiceNumber) event.getSource();
        System.out.println(button.getCoords()[0]+" "+button.getCoords()[1]);
        System.out.println(button.getFont());
    }

    public void testoutput(MouseEvent mouseEvent) {
        System.out.println("Test");
    }
}
