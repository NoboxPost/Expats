package expat.control;

import expat.model.board.ModelBoard;
import expat.model.board.ModelBoardGenerator;
import expat.model.board.ModelHex;
import expat.view.ViewHexFactory;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;


/**
 * Created by vanonir on 22.03.2017.
 */
public class ControllerBoardPane {
    private ControllerMainStage controllerMainStage;

    @FXML private AnchorPane anchorPaneBoard;

    public void initialize(){
        System.out.println("1");

    }
    public void drawBoard(ModelBoard modelBoard){
        anchorPaneBoard.getChildren().removeAll();
        ViewHexFactory hexFactory = new ViewHexFactory(200);
        anchorPaneBoard.getChildren().addAll(hexFactory.generateViewHexList(modelBoard.getHexes()));
    }

    public void init(ControllerMainStage controllerMainStage) {
        this.controllerMainStage=controllerMainStage;
    }
}
