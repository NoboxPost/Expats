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
    @FXML public StackPane stackPane;
    @FXML
    private AnchorPane anchorPaneBoard;

    private ControllerMainStage controllerMainStage;
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

    /**
     *Run by FXMLLoader attaches ScrollHandler so zooming is possible.
     */
    public void initialize() {
        attachScrollHandler();
    }

    /**
     * Generates complete board with all elements on it and adds it to anchorPaneBoard.
     *
     */
    public void drawBoard() {

        anchorPaneBoard.getChildren().removeAll();
        ModelBoard modelBoard = app.getBoard();
        generateHexGroup(modelBoard);
        generateDiceButtonGroup(modelBoard);
        generateConnectionGroup(modelBoard);
        generateRaiderGroup(modelBoard);
        generateBuildingGroup(modelBoard);
    }

    /**
     * Refreshes all elements which will change during process of game.
     *
     */
    public void refreshBoardElements(){
        ModelBoard modelBoard = app.getBoard();
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

        ViewHexFactory viewHexFactory = new ViewHexFactory(HEXSIZE);

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
    public void generateDiceButtonGroup(ModelBoard modelBoard){
        if (diceButtonGroup!=null) {
            anchorPaneBoard.getChildren().remove(diceButtonGroup);
        }

        ViewDiceButtonFactory viewDiceButtonFactory = new ViewDiceButtonFactory(HEXSIZE, this);

        diceButtonGroup = new Group();
        diceButtonGroup.getChildren().addAll(viewDiceButtonFactory.generateDiceButtons(modelBoard.getHexes()));
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

        ViewBuildingFactory viewBuildingFactory = new ViewBuildingFactory(HEXSIZE);

        buildingGroup = new Group();
        buildingGroup.getChildren().addAll(viewBuildingFactory.generateBuildingsAndAttachesThemToCorrectLocation(modelBoard.getBuildings(),this));
        anchorPaneBoard.getChildren().add(buildingGroup);
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
    public void hexClicked(ActionEvent event) {
        ViewDiceNumber button = (ViewDiceNumber) event.getSource();
        app.moveRaider(button.getCoords());
        refreshBoardElements();
        controllerMainStage.raiderMoved();
    }

    /**
     * Is called by ViewBuilding as onMouseReleased event.
     * Gets the coordinates stored in event source and hands them over to the corresponding method in the ModelApp
     *
     * @param event MouseEvent
     */
    public void emptyBuildingSpotClicked(MouseEvent event){
        ViewBuilding building = (ViewBuilding)event.getSource();
        app.finishesBuildingActionAndChangesToNextPlayerIfNeeded(building.getBuildingCoord(),"Building");
        refreshBoardElements();
        controllerMainStage.refreshActionStep();
        controllerMainStage.refreshGameInformations();
    }
    /**
     * Is called by ViewConnection as onMouseReleased event.
     * Gets the coordinates stored in event source and hands them over to the corresponding method in the ModelApp
     *
     * @param event MouseEvent
     */
    public void connectionClicked(MouseEvent event){
        ViewConnection connection = (ViewConnection) event.getSource();
        app.finishesBuildingActionAndChangesToNextPlayerIfNeeded(connection.getConnectionCoords(),"Connection"); //TODO: If Ships are implemented, we need to check types.
        refreshBoardElements();
        controllerMainStage.refreshActionStep();
        controllerMainStage.refreshGameInformations();
    }


    /**
     *
     * Takes ControllerMainStage, the MainController, and the ModelApp. MainController is used as reference so other controllers can be called.
     * App is used as a reference so events can be handed over to ModelApp.
     *
     * @param controllerMainStage
     * @param app
     */
    public void init(ControllerMainStage controllerMainStage,ModelApp app) {
        this.controllerMainStage = controllerMainStage;
        this.app = app;
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
