package expat.control;

import expat.model.ModelApp;
import expat.model.board.ModelBoard;
import expat.view.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
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
    private Group buildingGroup;
    private Group raiderGroup;
    private Group hexGroup;
    private Group diceButtonGroup;
    private Group connectionGroup;

    public void initialize() {
        attachScrollHandler();
    }

    public void drawBoard(ModelBoard modelBoard) {
        anchorPaneBoard.getChildren().removeAll();

        generateHexGroup(modelBoard);
        generateDiceButtonGroup(modelBoard);
        generateConnectionGroup(modelBoard);
        generateBuildingGroup(modelBoard);
        generateRaiderGroup(modelBoard);
    }

    public void refreshBoardElements(ModelBoard modelBoard){
        generateConnectionGroup(modelBoard);
        generateBuildingGroup(modelBoard);
        generateRaiderGroup(modelBoard);
    }

    public void generateHexGroup(ModelBoard modelBoard){
        if (hexGroup!=null) {
            anchorPaneBoard.getChildren().remove(hexGroup);
        }

        ViewHexFactory viewHexFactory = new ViewHexFactory(HEXSIZE);

        hexGroup = new Group();
        hexGroup.getChildren().addAll(viewHexFactory.generateViewHexList(modelBoard.getHexes()));
        anchorPaneBoard.getChildren().add(hexGroup);
    }

    public void generateDiceButtonGroup(ModelBoard modelBoard){
        if (diceButtonGroup!=null) {
            anchorPaneBoard.getChildren().remove(diceButtonGroup);
        }

        ViewDiceButtonFactory viewDiceButtonFactory = new ViewDiceButtonFactory(HEXSIZE, this);

        diceButtonGroup = new Group();
        diceButtonGroup.getChildren().addAll(viewDiceButtonFactory.generateDiceButtons(modelBoard.getHexes()));
        anchorPaneBoard.getChildren().add(diceButtonGroup);
    }

    public void generateBuildingGroup(ModelBoard modelBoard){
        if (buildingGroup!=null) {
            anchorPaneBoard.getChildren().remove(buildingGroup);
        }

        ViewBuildingFactory viewBuildingFactory = new ViewBuildingFactory(HEXSIZE,this);

        buildingGroup = new Group();
        buildingGroup.getChildren().addAll(viewBuildingFactory.generateBuildings(modelBoard.getBuildings()));
        anchorPaneBoard.getChildren().add(buildingGroup);
    }

    public void generateConnectionGroup(ModelBoard modelBoard){
        if (connectionGroup!=null) {
            anchorPaneBoard.getChildren().remove(connectionGroup);
        }

        ViewConnectionFactory viewConnectionFactory = new ViewConnectionFactory(HEXSIZE,this);

        connectionGroup = new Group();
        connectionGroup.getChildren().addAll(viewConnectionFactory.generateConnections(modelBoard.getConnections()));
        anchorPaneBoard.getChildren().add(connectionGroup);
    }

    public void generateRaiderGroup(ModelBoard modelBoard){
        if (raiderGroup!=null) {
            anchorPaneBoard.getChildren().remove(raiderGroup);
        }

        ViewRaiderFactory viewRaiderFactory = new ViewRaiderFactory(HEXSIZE, this);

        raiderGroup = new Group();
        raiderGroup.getChildren().addAll(viewRaiderFactory.generateRaider(modelBoard.getRaider()));
        anchorPaneBoard.getChildren().add(raiderGroup);
    }

    public void hexClicked(ActionEvent event) { //TODO: Raider
        ViewDiceNumber button = (ViewDiceNumber) event.getSource();
        System.out.println("DiceNumber clicked :"+button.getCoords()[0] + " " + button.getCoords()[1]);
    }
    public void emptyBuildingSpotClicked(MouseEvent event){
        ViewBuilding building = (ViewBuilding)event.getSource();
        app.getBoard().changeBuilding(building.getBuildingCoord()[0],building.getBuildingCoord()[1]);
        System.out.println("Empty Building clicked "+building.getBuildingCoord()[0]+" "+building.getBuildingCoord()[1]);
        refreshBoardElements(app.getBoard());

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
