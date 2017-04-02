package expat.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * the bottom pane where actions like trade or card events happen
 * <p>
 * created on 29.03.2017
 *
 * @author gallatib
 */
public class PaneActionController {
    private ControllerMainStage controllerMainStage;
    @FXML private AnchorPane action;

    public void init(ControllerMainStage controllerMainStage){
        this.controllerMainStage = controllerMainStage;
    }
    public void drawDiceTurn(){

    }
    public void drawBuildTurn(){
        action.getChildren().removeAll();
        Image settlement =new Image("expat/img/Settlement.png");
        ImageView settelmentImageView= new ImageView(settlement);
        Image road = new Image("expat/img/Connection.png");
        ImageView roadImageView = new ImageView(road);
        HBox hBox = new HBox();
        Region regionLeft= new Region();
        hBox.getChildren().addAll(regionLeft,settelmentImageView,roadImageView);
        action.getChildren().add(hBox);
    }
}
