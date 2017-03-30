package expat.control;

import expat.model.board.ModelBoard;
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
    private int hexSize = 200;
    private double SCALE_NUMBER = 1.05;
    private double mousePositionX;
    private double mousePositionY;

    public void initialize() {

    }

    public void drawBoard(ModelBoard modelBoard) {
        anchorPaneBoard.getChildren().removeAll();
        ViewHexFactory hexFactory = new ViewHexFactory(hexSize);
        anchorPaneBoard.getChildren().addAll(hexFactory.generateViewHexList(modelBoard.getHexes()));
        ViewDiceButtonFactory diceButtonFactory = new ViewDiceButtonFactory(hexSize, this);
        anchorPaneBoard.getChildren().addAll(diceButtonFactory.generateDiceButtons(modelBoard.getHexes()));

        anchorPaneBoard.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                event.consume();
                System.out.println("StackpaneWidth: " + stackPane.getWidth() + " StackpaneHeight: " + stackPane.getHeight() + " getSceneX: " + event.getSceneX() + " getSceneY: " + event.getSceneY() + " getScreenX: " + event.getScreenX() + " getScreenY: " + event.getScreenY() + " getX: " + event.getX() + " getY: " + event.getY());

                mousePositionX = event.getX()/1400;
                mousePositionY = event.getY()/1200;


                if (event.getDeltaY() == 0) {
                    return;
                }
                //1.8
                else if (event.getDeltaY() > 0 && anchorPaneBoard.getScaleY() < 3){
                    scaleAnchorPaneBoard(SCALE_NUMBER);
                }
                //0.5
                else if(event.getDeltaY() < 0 && anchorPaneBoard.getScaleY() > 0.1){
                    scaleAnchorPaneBoard(1 / SCALE_NUMBER);
                }
            }
        });


    }

    public void scaleAnchorPaneBoard(double scaleSize){
        anchorPaneBoard.setScaleX(anchorPaneBoard.getScaleX() * scaleSize);
        anchorPaneBoard.setScaleY(anchorPaneBoard.getScaleY() * scaleSize);
        stackPane.setPrefHeight(anchorPaneBoard.getHeight() * anchorPaneBoard.getScaleY());
        stackPane.setPrefWidth(anchorPaneBoard.getWidth() * anchorPaneBoard.getScaleX());
        //System.out.println(anchorPaneBoard.getScaleX() + " " + anchorPaneBoard.getScaleY());
        controllerMainStage.adjustScrollPaneCenter(mousePositionX, mousePositionY);
    }

    public void hexClicked(ActionEvent event) { //TODO: Raider
        ViewDiceNumber button = (ViewDiceNumber) event.getSource();
        System.out.println(button.getCoords()[0] + " " + button.getCoords()[1]);
        System.out.println(button.getFont());
        System.out.println(anchorPaneBoard.getHeight() + " " + anchorPaneBoard.getWidth() + " " + anchorPaneBoard.getScaleY());
    }

    public void init(ControllerMainStage controllerMainStage) {
        this.controllerMainStage = controllerMainStage;
    }

}
