package expat.control;

import expat.model.board.ModelBoard;
import expat.view.ViewBuildingFactory;
import expat.view.ViewDiceButtonFactory;
import expat.view.ViewDiceNumber;
import expat.view.ViewHexFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;


/**
 * Created by vanonir on 22.03.2017.
 */
public class PaneBoardController {
    public StackPane stackPane;
    private ControllerMainStage controllerMainStage;

    @FXML
    private AnchorPane anchorPaneBoard;
    private int HEXSIZE = 200;
    private double SCALE_NUMBER = 1.05;
    private double mousePositionX;
    private double mousePositionY;

    public void initialize() {
        attachScrollHandler();
    }

    public void drawBoard(ModelBoard modelBoard) {
        anchorPaneBoard.getChildren().removeAll();
        ViewHexFactory hexFactory = new ViewHexFactory(HEXSIZE);
        anchorPaneBoard.getChildren().addAll(hexFactory.generateViewHexList(modelBoard.getHexes()));
        ViewDiceButtonFactory diceButtonFactory = new ViewDiceButtonFactory(HEXSIZE, this);
        anchorPaneBoard.getChildren().addAll(diceButtonFactory.generateDiceButtons(modelBoard.getHexes()));
        ViewBuildingFactory viewBuildingFactory = new ViewBuildingFactory(HEXSIZE);
        anchorPaneBoard.getChildren().addAll(viewBuildingFactory.generateBuildings(modelBoard.getHexes()));
    }

    public void hexClicked(ActionEvent event) { //TODO: Raider
        ViewDiceNumber button = (ViewDiceNumber) event.getSource();
        System.out.println(button.getCoords()[0] + " " + button.getCoords()[1]);
    }

    public void init(ControllerMainStage controllerMainStage) {
        this.controllerMainStage = controllerMainStage;
    }

    private void attachScrollHandler(){
        anchorPaneBoard.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                event.consume();
                // TODO: entfernen (Test) System.out.println("StackpaneWidth: " + stackPane.getWidth() + " StackpaneHeight: " + stackPane.getHeight() + " getSceneX: " + event.getSceneX() + " getSceneY: " + event.getSceneY() + " getScreenX: " + event.getScreenX() + " getScreenY: " + event.getScreenY() + " getX: " + event.getX() + " getY: " + event.getY());

                mousePositionX = event.getX()/1400;
                mousePositionY = event.getY()/1200;
                // TODO: Werte relativ zum ScrollPane einstellen, evt Transition

                if (event.getDeltaY() == 0) {
                    return;
                }
                else if (event.getDeltaY() > 0 && anchorPaneBoard.getScaleY() < 1.8){
                    scaleAnchorPaneBoard(SCALE_NUMBER);
                }
                else if(event.getDeltaY() < 0 && anchorPaneBoard.getScaleY() > 0.5){
                    scaleAnchorPaneBoard(1 / SCALE_NUMBER);
                }
            }
        });
    }

    private void scaleAnchorPaneBoard(double scaleSize){
        anchorPaneBoard.setScaleX(anchorPaneBoard.getScaleX() * scaleSize);
        anchorPaneBoard.setScaleY(anchorPaneBoard.getScaleY() * scaleSize);
        stackPane.setPrefHeight(anchorPaneBoard.getHeight() * anchorPaneBoard.getScaleY());
        stackPane.setPrefWidth(anchorPaneBoard.getWidth() * anchorPaneBoard.getScaleX());
        //TODO: entfernen (Test) System.out.println(anchorPaneBoard.getScaleX() + " " + anchorPaneBoard.getScaleY());
        controllerMainStage.adjustScrollPaneCenter(mousePositionX, mousePositionY);
    }

}
