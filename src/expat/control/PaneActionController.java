package expat.control;

import expat.model.ModelApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


/**
 * the bottom pane where actions like trade or card events happen
 * <p>
 * created on 29.03.2017
 *
 * @author gallatib
 */
public class PaneActionController {
    @FXML private Button btnNextStep;
    @FXML private Button btnEndTurn;
    @FXML private AnchorPane rightActionPane;
    @FXML private Pane middleActionPane;
    @FXML private Pane leftActionPane;
    private ControllerMainStage controllerMainStage;
    @FXML private AnchorPane action;
    ModelApp app;


    public void init(ControllerMainStage controllerMainStage, ModelApp app){
        this.controllerMainStage = controllerMainStage;
        this.app= app;
    }
    public void drawDiceStep(){

    }
    public void drawBuildStep(){
        middleActionPane.getChildren().removeAll();
        Image town = new Image("expat/img/TownColored.png");
        ImageView townImageView= new ImageView(town);
        townImageView.setFitHeight(80);
        townImageView.setPreserveRatio(true);
        townImageView.setOnMouseReleased(this::generateTown);
        townImageView.setCursor(Cursor.HAND);

        Image settlement =new Image("expat/img/Settlement.png");
        ImageView settelmentImageView= new ImageView(settlement);
        settelmentImageView.setFitHeight(80);
        settelmentImageView.setPreserveRatio(true);
        settelmentImageView.setOnMouseReleased(this::generateSettlement);
        settelmentImageView.setCursor(Cursor.HAND);

        Image road = new Image("expat/img/Connection.png");
        ImageView roadImageView = new ImageView(road);
        roadImageView.setFitHeight(80);
        roadImageView.setPreserveRatio(true);
        roadImageView.setOnMouseReleased(this::generateRoad);
        roadImageView.setCursor(Cursor.HAND);

        middleActionPane.getChildren().addAll(townImageView,settelmentImageView,roadImageView);
    }

    private void generateRoad(MouseEvent event) {
        generateBuilding("Road",(ImageView) event.getSource());
    }

    private void generateSettlement(MouseEvent event) {
        generateBuilding("Settlement",(ImageView) event.getSource());
    }

    private void generateTown(MouseEvent event) {
        generateBuilding("Town",(ImageView) event.getSource());
    }

    private void generateBuilding(String type, ImageView imageView){
        drawBuildStep();
        System.out.println("generateBuilding");
        app.newBuildingAction(type);
        //TODO: Draw frame around ImageView;

    }




    public void btnNextStepClicked(ActionEvent event) {
        System.out.println("next stepp clicked");
    }

    public void btnEndTurnClicked(ActionEvent event) {
        System.out.println("end turn clicked");
    }
}
