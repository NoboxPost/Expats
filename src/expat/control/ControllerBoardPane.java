package expat.control;

import expat.model.board.Board;
import expat.model.board.Desert;
import expat.model.board.Hex;
import expat.view.ViewHex;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


/**
 * Created by vanonir on 22.03.2017.
 */
public class ControllerBoardPane {

    @FXML private AnchorPane board;

    public void initialize(){

        ViewHex myHex = new ViewHex(0,0);
        board.getChildren().addAll(myHex);
//        ViewHex myHex2 = new ViewHex(0,100);
//        board.getChildren().addAll(myHex2);
//        ViewHex myHex3 = new ViewHex(75,50);
//        board.getChildren().addAll(myHex3);
    }
}
