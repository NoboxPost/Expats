package expat.control;

import expat.model.ModelApp;
import expat.model.ModelDiceRolling;
import expat.model.ModelEvent;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Scanner;

/**
 * the main scene that works as collection-pool for all included scenes and also starts the App
 * <p>
 * created on 22.03.2017
 *
 * @author vanonir
 */
public class ControllerMainStage {
    @FXML
    private ScrollPane scrollPaneCenter;
    @FXML
    BorderPane borderPane;
    @FXML
    StackPane paneBoard;
    @FXML
    VBox panePlayer;
    @FXML
    VBox paneMates;
    @FXML
    AnchorPane paneAction;
    @FXML
    PaneBoardController paneBoardController;
    @FXML
    PaneActionController paneActionController;
    @FXML
    PaneMatesController paneMatesController;
    @FXML
    PanePlayerController panePlayerController;
    private ModelApp app;
    private ControllerServerConnection connection;
    private String gameType = "solo";
    private int playerCount = 2;


    /**
     * Runs after initialization of all controllers, initializes an app and starts model logic.
     * Is called by FXMLLoader and can't be changed.
     */
    public void initialize() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Game Type Bitte: ('solo' für screenshare, 'host' für LAN Mulitplayer Host, 'client' für LAN Multiplayer Client)");
        gameType = sc.nextLine();
        if (gameType.equals("host")||gameType.equals("solo")){
            System.out.println("Wieviele Spieler sollen mitspielen ( min 2 - max 4):");
            playerCount = sc.nextInt();
        }
        int localplayer = 0;
        if (gameType.equals("client")||gameType.equals("host")){
            try {
                connection = new ControllerServerConnection(this);
            localplayer = connection.getConnectionID();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        app = new ModelApp(localplayer,gameType,playerCount);
        paneBoardController.init(this,app);
        paneActionController.init(this,app);
        panePlayerController.init(this, app);
        paneMatesController.init(app);
        drawGame();
    }


    /**
     * draws the board and fills the other panes with infos of the app.
     */
    public void drawGame(){
        paneBoardController.drawBoard(app.getBoard());
        app.gameBegin();
        paneActionController.refreshStep();
        panePlayerController.refresh();
        paneMatesController.setMatesInformation();
    }


    /**
     * adjusts the scrollbar when zooming
     * @param width
     * @param height
     */
    public void adjustScrollPaneCenter(double width, double height) {
        scrollPaneCenter.setHvalue(width);
        scrollPaneCenter.setVvalue(height);
        // TODO: entfernen (Testing): System.out.println("vValue: " + scrollPaneCenter.getVvalue() + " hValue: " + scrollPaneCenter.getHvalue());
    }

    /**
     * Refreshes lower middle part of the screen, where the different game steps are displayed.
     */
    public void refreshActionStep() {
        paneActionController.refreshStep();
    }
    /**
     * Refreshes left side of the screen, where infos about the current player are displayed.
     */
    public void refreshGameInformations() {
        panePlayerController.refresh();
        paneMatesController.setMatesInformation();
    }


    /**
     * Enables buttons for next step and end turn again, after paneActionController waitet for Raider to be moved.
     */
    public void raiderMoved() {
        paneActionController.raiderMoved();
    }

    public void sendEvent(ModelEvent event) {
        connection.sendEvent(event);
    }

    public void eventHandler(ModelEvent modelEvent) {
        switch (modelEvent.getEventType()){
            case "rolledDice":
                System.out.println("received dice rolling event");
                //app.rolledDiceElsewhere((ModelDiceRolling) modelEvent.getSingleObject());
                break;

        }

    }
}

