package expat.control;

import expat.model.ModelApp;
import expat.model.board.ModelBoard;
import expat.view.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;


/**
 * the center scene where the game board is loaded and scrolling is handled
 * <p>
 * created on 22.03.2017
 *
 * @author vanonir
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
    private ModelApp app;

    public void initialize() {
        attachScrollHandler();
    }

    public void drawBoard(ModelBoard modelBoard) {
        anchorPaneBoard.getChildren().removeAll();
        ViewHexFactory hexFactory = new ViewHexFactory(HEXSIZE);
        anchorPaneBoard.getChildren().addAll(hexFactory.generateViewHexList(modelBoard.getHexes()));
        ViewDiceButtonFactory diceButtonFactory = new ViewDiceButtonFactory(HEXSIZE, this);
        anchorPaneBoard.getChildren().addAll(diceButtonFactory.generateDiceButtons(modelBoard.getHexes()));
        ViewConnectionFactory viewConnectionFactory= new ViewConnectionFactory(HEXSIZE,this);
        anchorPaneBoard.getChildren().addAll(viewConnectionFactory.generateConnections(modelBoard.getConnections()));
        ViewBuildingFactory viewBuildingFactory = new ViewBuildingFactory(HEXSIZE,this);
        anchorPaneBoard.getChildren().addAll(viewBuildingFactory.generateBuildings(modelBoard.getBuildings()));
        ViewRaiderFactory viewRaiderFactory = new ViewRaiderFactory(HEXSIZE, this);
        anchorPaneBoard.getChildren().add(viewRaiderFactory.generateRaider(modelBoard.getRaider()));
    }

    public void hexClicked(ActionEvent event) { //TODO: Raider
        ViewDiceNumber button = (ViewDiceNumber) event.getSource();
        System.out.println("DiceNumber clicked :"+button.getCoords()[0] + " " + button.getCoords()[1]);
    }
    public void emptyBuildingSpotClicked(MouseEvent event){
        ViewBuilding building = (ViewBuilding)event.getSource();
        app.getBoard().changeBuilding(building.getBuildingCoord()[0],building.getBuildingCoord()[1]);
        System.out.println("Empty Building clicked "+building.getBuildingCoord()[0]+" "+building.getBuildingCoord()[1]);
        drawBoard(app.getBoard());

    }


    public void init(ControllerMainStage controllerMainStage,ModelApp app) {
        this.controllerMainStage = controllerMainStage;
        this.app = app;
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
