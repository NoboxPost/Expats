package expat.control;

import expat.model.ModelApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


/**
 * the bottom pane where actions like trade or card events happen
 * <p>
 * created on 29.03.2017
 *
 * @author gallatib
 */
public class PaneActionController {
    public Button btnNextStep;
    public Button btnEndTurn;
    public AnchorPane rightActionPane;
    public HBox middleActionPane;
    public Pane leftActionPane;
    private ControllerMainStage controllerMainStage;
    public AnchorPane action;
    private ImageView roadImageView;
    private ImageView townImageView;
    private ImageView settlementImageView;
    ModelApp app;


    public void init(ControllerMainStage controllerMainStage, ModelApp app){
        this.controllerMainStage = controllerMainStage;
        this.app= app;
    }
    public void drawDiceStep(){

    }
    public void drawBuildStep(){
        middleActionPane.getChildren().clear();
        Image town = new Image("expat/img/TownColored.png");
        townImageView= new ImageView(town);
        townImageView.setFitHeight(80);
        townImageView.setPreserveRatio(true);
        townImageView.setOnMouseReleased(this::generateTown);
        townImageView.setCursor(Cursor.HAND);

        Image settlement =new Image("expat/img/Settlement.png");
        settlementImageView= new ImageView(settlement);
        settlementImageView.setFitHeight(80);
        settlementImageView.setPreserveRatio(true);
        settlementImageView.setOnMouseReleased(this::generateSettlement);
        settlementImageView.setCursor(Cursor.HAND);

        Image road = new Image("expat/img/Connection.png");
        roadImageView = new ImageView(road);
        roadImageView.setFitHeight(80);
        roadImageView.setPreserveRatio(true);
        roadImageView.setOnMouseReleased(this::generateRoad);
        roadImageView.setCursor(Cursor.HAND);

        middleActionPane.getChildren().addAll(townImageView,settlementImageView,roadImageView);
    }

    private void generateRoad(MouseEvent event) {
        generateBuilding("Road", (ImageView) event.getSource());
        roadImageView.setEffect(addDropShadow());
    }


    private void generateSettlement(MouseEvent event) {
        generateBuilding("Settlement",(ImageView) event.getSource());
        settlementImageView.setEffect(addDropShadow());
    }

    private void generateTown(MouseEvent event) {
        generateBuilding("Town",(ImageView) event.getSource());
        townImageView.setEffect(addDropShadow());
    }

    private void generateBuilding(String type, ImageView imageView){
        drawBuildStep();
        System.out.println("generateBuilding");
        app.newBuildingAction(type);
        //TODO: Draw frame around ImageView;
    }

    private DropShadow addDropShadow(){
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(30);
        dropShadow.setHeight(25);
        dropShadow.setWidth(200);
        dropShadow.setSpread(0.5);

        return dropShadow;
    }




    public void btnNextStepClicked(ActionEvent event) {
        System.out.println("next stepp clicked");
    }

    public void btnEndTurnClicked(ActionEvent event) {
        System.out.println("end turn clicked");
    }
}
