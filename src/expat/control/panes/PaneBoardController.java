package expat.control.panes;

import expat.control.procedure.MainGameController;
import expat.control.procedure.PreGameController;
import expat.model.board.ModelBoard;
import expat.model.buildings.ModelBuilding;
import expat.model.buildings.ModelConnection;
import expat.view.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;


/**
 * the center scene where the game board is loaded and scrolling is handled
 * <p>
 * created on 22.03.2017
 *
 * @author vanonir
 */
public class PaneBoardController {
    @FXML public StackPane stackPane;
    @FXML
    private AnchorPane anchorPaneBoard;

    private MainStageController controllerMainStage;
    private int HEXSIZE = 200;
    private double SCALE_NUMBER = 1.05;
    private double mousePositionX;
    private double mousePositionY;
    private Group buildingGroup;
    private Group raiderGroup;
    private Group hexGroup;
    private Group diceButtonGroup;
    private Group connectionGroup;
    private Group buildingPlacingSpotGroup;
    private Group connectionPlacingSpotGroup;

    private MainGameController mainGameController;
    private PreGameController preGameController;

    public void init(MainStageController controllerMainStage, MainGameController mainGameController, PreGameController preGameController) {
        this.controllerMainStage = controllerMainStage;
        this.mainGameController = mainGameController;
        this.preGameController = preGameController;
    }

    /**
     *Run by FXMLLoader attaches ScrollHandler so zooming is possible.
     */
    public void initialize() {
        attachScrollHandler();
    }

    /**
     * Generates complete board with all elements on it and adds it to anchorPaneBoard.
     *
     * @param modelBoard
     */
    public void drawBoard(ModelBoard modelBoard) {
        anchorPaneBoard.getChildren().removeAll();

        generateHexGroup(modelBoard);
        generateDiceButtonDisabledGroup(modelBoard);
        generateConnectionGroup(modelBoard);
        generateRaiderGroup(modelBoard);
        generateBuildingGroup(modelBoard);
    }

    /**
     * Refreshes all elements which will change during process of game.
     *
     * @param modelBoard ModelBoard which will hand over the seperate elements to subclasses.
     */
    public void refreshBoardElements(ModelBoard modelBoard){
        generateConnectionGroup(modelBoard);
        generateRaiderGroup(modelBoard);
        generateBuildingGroup(modelBoard);
    }

    /**
     * Generates the background of the board, which will be made out of polygons representing the ModelHexes and their types.
     * Adds the polygons nodes to anchorPaneBoard.
     *
     * @param modelBoard takes the ModelBoard so Method can get the hex collection.
     */
    public void generateHexGroup(ModelBoard modelBoard){
        if (hexGroup!=null) {
            anchorPaneBoard.getChildren().remove(hexGroup);
        }

        ViewHexGenerator viewHexFactory = new ViewHexGenerator(HEXSIZE);

        hexGroup = new Group();
        hexGroup.getChildren().addAll(viewHexFactory.generateViewHexList(modelBoard.getHexes()));
        anchorPaneBoard.getChildren().add(hexGroup);
    }

    /**
     * Generates the DiceButtons which represent the numbers ontop of each resource hex.
     * Attaches all button to the anchorPaneBoard.
     *
     * @param modelBoard  takes the ModelBoard so method can acquire the diceNumber form every hex.
     */
    public void generateDiceButtonEnabledGroup(ModelBoard modelBoard){
        if (diceButtonGroup!=null) {
            anchorPaneBoard.getChildren().remove(diceButtonGroup);
        }

        ViewDiceButtonFactory viewDiceButtonFactory = new ViewDiceButtonFactory(HEXSIZE, this);

        diceButtonGroup = new Group();
        diceButtonGroup.getChildren().addAll(viewDiceButtonFactory.generateDiceButtonsEnabled(modelBoard.getHexes()));
        anchorPaneBoard.getChildren().add(diceButtonGroup);
    }

    public void generateDiceButtonDisabledGroup(ModelBoard modelBoard){
        if (diceButtonGroup!=null) {
            anchorPaneBoard.getChildren().remove(diceButtonGroup);
        }

        ViewDiceButtonFactory viewDiceButtonFactory = new ViewDiceButtonFactory(HEXSIZE, this);

        diceButtonGroup = new Group();
        diceButtonGroup.getChildren().addAll(viewDiceButtonFactory.generateDiceButtonsDisabled(modelBoard.getHexes()));
        anchorPaneBoard.getChildren().add(diceButtonGroup);
    }

    /**
     * Generates all Buildings representing the ModelBuildings given as field of parameter ModelBoard.
     * Attaches all Buildings as ViewBuilding which is an extended ImageView, directly to the anchorPaneBoard.
     *
     * @param modelBoard ModelBoard so methode can acquire the building collection
     */
    public void generateBuildingGroup(ModelBoard modelBoard){
        if (buildingGroup!=null) {
            anchorPaneBoard.getChildren().remove(buildingGroup);
        }

        ViewBuildingFactory viewBuildingFactory = new ViewBuildingFactory(HEXSIZE,this);

        buildingGroup = new Group();
        buildingGroup.getChildren().addAll(viewBuildingFactory.generateBuildings(modelBoard.getBuildings()));
        anchorPaneBoard.getChildren().add(buildingGroup);
    }

    public void generateBuildingPlacingSpotGroup(ArrayList<ModelBuilding> buildingsAPlayerCouldBuild){
        if (buildingPlacingSpotGroup!=null) {
            anchorPaneBoard.getChildren().remove(buildingPlacingSpotGroup);
        }
        ViewBuildingFactory viewBuildingFactory = new ViewBuildingFactory(HEXSIZE,this);

        buildingPlacingSpotGroup = new Group();

        for(ModelBuilding buildingAPlayerCouldBuild : buildingsAPlayerCouldBuild){
            buildingPlacingSpotGroup.getChildren().add(viewBuildingFactory.generateBuildingPlacingSpot(buildingAPlayerCouldBuild.getCoords()));
        }

        anchorPaneBoard.getChildren().add(buildingPlacingSpotGroup);
    }

    /**
     * Generates all Connections representing the ModelBuildings given as field of parameter ModelBoard.
     * Attaches all Connections as ViewBuilding which is an extended ImageView, directly to the anchorPaneBoard.
     *
     * @param modelBoard ModelBoard so methode can acquire the connection collection
     */
    public void generateConnectionGroup(ModelBoard modelBoard){
        if (connectionGroup!=null) {
            anchorPaneBoard.getChildren().remove(connectionGroup);
        }

        ViewConnectionFactory viewConnectionFactory = new ViewConnectionFactory(HEXSIZE,this);

        connectionGroup = new Group();
        connectionGroup.getChildren().addAll(viewConnectionFactory.generateConnections(modelBoard.getConnections()));
        anchorPaneBoard.getChildren().add(connectionGroup);
    }

    public void generateConnectionPlacingSpotGroup(ArrayList<ModelConnection> connectionsAPlayerCouldBuild){
        if (connectionPlacingSpotGroup!=null) {
            anchorPaneBoard.getChildren().remove(connectionPlacingSpotGroup);
        }
        ViewConnectionFactory viewConnectionFactory = new ViewConnectionFactory(HEXSIZE,this);

        connectionPlacingSpotGroup = new Group();

        for(ModelConnection connectionAPlayerCouldBuild : connectionsAPlayerCouldBuild){
            connectionPlacingSpotGroup.getChildren().add(viewConnectionFactory.generateConnectionPlacingSpot(connectionAPlayerCouldBuild.getOrientation(), connectionAPlayerCouldBuild.getCoords()));
        }

        anchorPaneBoard.getChildren().add(connectionPlacingSpotGroup);
    }

    /**
     * Generates the ImageView representing the ModelRaider at his current location.
     *
     * @param modelBoard ModelBoard so methode can acquire the ModelRaider.
     */
    public void generateRaiderGroup(ModelBoard modelBoard){
        if (raiderGroup!=null) {
            anchorPaneBoard.getChildren().remove(raiderGroup);
        }

        ViewRaiderFactory viewRaiderFactory = new ViewRaiderFactory(HEXSIZE, this);

        raiderGroup = new Group();
        raiderGroup.getChildren().addAll(viewRaiderFactory.generateRaider(modelBoard.getRaider()));
        anchorPaneBoard.getChildren().add(raiderGroup);
    }

    /**
     * Is called by the ViewDiceButton and gets its coordinates which will be handed over to the corresponding methode in ModelApp
     *
     * @param event ActonEvent
     */
    public void btnHexNumberClicked(ActionEvent event) {
        ViewDiceNumber button = (ViewDiceNumber) event.getSource();
        mainGameController.finishMoveRaider(button.getCoords());
        controllerMainStage.raiderMoved();
    }

    /**
     * Is called by ViewBuilding as onMouseReleased event.
     * Gets the coordinates stored in event source and hands them over to the corresponding method in the ModelApp
     *
     * @param event MouseEvent
     */
    public void buildingSpotClicked(MouseEvent event){
        ViewBuilding building = (ViewBuilding)event.getSource();
        controllerMainStage.selectGameControllerForFinishPlacingElement(building.getBuildingCoord(),"Building");
    }


    public void connectionSpotClicked(MouseEvent event){
        ViewConnection connection = (ViewConnection) event.getSource();
        controllerMainStage.selectGameControllerForFinishPlacingElement(connection.getConnectionCoords(),"Connection"); //TODO: If Ships are implemented, we need to check types.
    }

    /**
     * Attaches ScrollHandler to the anchorPaneBoard, so scrolling will be possible as long as the mouse is over the Board.
     */
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

    /**
     * Scales the board according to given input.
     *
     * @param scaleSize double generated after ScrollEvent.
     */
    private void scaleAnchorPaneBoard(double scaleSize){
        anchorPaneBoard.setScaleX(anchorPaneBoard.getScaleX() * scaleSize);
        anchorPaneBoard.setScaleY(anchorPaneBoard.getScaleY() * scaleSize);
        stackPane.setPrefHeight(anchorPaneBoard.getHeight() * anchorPaneBoard.getScaleY());
        stackPane.setPrefWidth(anchorPaneBoard.getWidth() * anchorPaneBoard.getScaleX());
        //TODO: entfernen (Test) System.out.println(anchorPaneBoard.getScaleX() + " " + anchorPaneBoard.getScaleY());
        controllerMainStage.adjustScrollPaneCenter(mousePositionX, mousePositionY);
    }
}
