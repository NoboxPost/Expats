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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;


/**
 * Created by vanonir on 22.03.2017.
 */
public class PaneBoardController {
    public StackPane stackPane;
    private ControllerMainStage controllerMainStage;

    @FXML
    private AnchorPane anchorPaneBoard;
    private int hexSize = 200;
    private double SCALE_NUMBER = 1.1;

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
        ViewDiceButtonFactory diceLabelFactory = new ViewDiceButtonFactory(hexSize,this);
        anchorPaneBoard.getChildren().addAll(diceLabelFactory.generateDiceButtons(modelBoard.getHexes()));
        ViewBuildingFactory viewBuildingFactory = new ViewBuildingFactory(hexSize);
        anchorPaneBoard.getChildren().addAll(viewBuildingFactory.generateBuildings(modelBoard.getHexes()));
    }

    public void scaleAnchorPaneBoard(double scaleSize){
        anchorPaneBoard.setScaleX(anchorPaneBoard.getScaleX() * scaleSize);
        anchorPaneBoard.setScaleY(anchorPaneBoard.getScaleY() * scaleSize);
        stackPane.setPrefHeight(anchorPaneBoard.getHeight() * anchorPaneBoard.getScaleY());
        stackPane.setPrefWidth(anchorPaneBoard.getWidth() * anchorPaneBoard.getScaleX());
        System.out.println(anchorPaneBoard.getScaleX() + " " + anchorPaneBoard.getScaleY());
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
