package expat.control;

import expat.model.ModelApp;
import expat.model.ModelDiceRolling;
import expat.model.ModelEvent;
import expat.model.ModelPlayerHandler;
import expat.model.board.ModelBoard;
import expat.server.ServerMain;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
    ServerMain serverMain;


    /**
     * Runs after initialization of all controllers, initializes an app and starts model logic.
     * Is called by FXMLLoader and can't be changed.
     */
    public void initialize() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Game Type Bitte: ('solo' für screenshare, 'host' für LAN Mulitplayer Host, 'client' für LAN Multiplayer Client)");
        gameType = sc.nextLine();
        if (gameType.equals("host") || gameType.equals("solo")) {
            System.out.println("Wieviele Spieler sollen mitspielen ( min 2 - max 4):");
            playerCount = sc.nextInt();
        }
        int localplayer = 0;
        if (gameType.equals("client") || gameType.equals("host")) {
            if (gameType.equals("host")) {
                serverMain = new ServerMain();
                serverMain.start();
                try {
                    connection = new ControllerServerConnection(this);
                    connection.getConnectionID();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                boolean notConnected = true;
                //loop so player can decide if he wants to repeat to try to connect if connection failed.
                while (notConnected) {
                    try {
                        connection = new ControllerServerConnection(this);
                        notConnected = false;
                    } catch (IOException e) {
                        System.out.println("No Host found, do you want to repeat? j/n");
                        if (!new Scanner(System.in).nextLine().equals("j")) {
                            e.printStackTrace();
                        }
                    }
                    connection.getConnectionID();
                }
            }

        }
        if (gameType.equals("solo")) {
            initAppAndControllers(localplayer);
            app.setLocalPlayerIDSet(true);
            startGame(new ActionEvent());
        }
    }

    public void initAppAndControllers(int localplayer) {
        app = new ModelApp(localplayer, gameType, playerCount, this);
        paneBoardController.init(this, app);
        paneActionController.init(this, app);
        panePlayerController.init(this, app);
        paneMatesController.init(app);
        if (gameType.equals("host")) {
            paneActionController.drawStartButton();
        }

    }

    public void startGame(ActionEvent event) {
        drawGame();
        if (!gameType.equals("client")) {
            app.gameBegin();
        }

        if (gameType.equals("host")) {
            sendBoard();
            sendPlayerHandler();
            sendFirstBuildingStep();
        }
        refreshActionStep();
    }

    private void sendFirstBuildingStep() {
        ModelEvent modelEvent = new ModelEvent(app.getLocalPlayerID());
        modelEvent.setEventType("FirstBuildingStep");
        modelEvent.setMessage("FirstBuildingStep");
        sendEvent(modelEvent);
    }


    /**
     * draws the board and fills the other panes with infos of the app.
     */
    public void drawGame() {
        paneBoardController.drawBoard();
        paneActionController.refreshStep();
        panePlayerController.refresh();
        paneMatesController.setMatesInformation();
    }


    /**
     * adjusts the scrollbar when zooming
     *
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

    public void drawBoard() {
        paneBoardController.drawBoard();
    }

    public void refreshBoardElements() {
        paneBoardController.refreshBoardElements();
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

    public void sendBoard() {
        ModelEvent modelEvent = new ModelEvent(app.getLocalPlayerID());
        modelEvent.setTypeAndAttachSingleObject("drawBoard", app.getBoard());
        sendEvent(modelEvent);
    }

    public void sendPlayerHandler() {
        ModelEvent modelEvent = new ModelEvent(app.getLocalPlayerID());
        modelEvent.setTypeAndAttachSingleObject("playerHandlerRefresh", app.getPlayerHandler());
        sendEvent(modelEvent);
    }

    /**
     * Entrypoint for all events received over network by ControllerInputThread.
     *
     * @param modelEvent
     */
    public void eventHandler(ModelEvent modelEvent) {
        //Call comes from another thread, but will fail if not executed in original JavaFX Application Thread, so we use Platform.runLater
        //which means it will be executed as soon as JavaFX Application Thread is read.
        Platform.runLater(() -> {
            if (app == null || app.getLocalPlayerID() != modelEvent.getSender()) {
                switch (modelEvent.getEventType()) {
                    case "getID":
                        if (app == null) {
                            int localPlayer = Integer.parseInt(modelEvent.getMessage());
                            System.out.println(localPlayer);
                            initAppAndControllers(localPlayer);
                        }
                        break;
                    case "rolledDice":
                        app.rolledDiceElsewhere((ModelDiceRolling) modelEvent.getSingleObject());
                        break;
                    case "drawBoard":
                        app.setBoard((ModelBoard) modelEvent.getSingleObject());
                        drawBoard();
                        if (app.getPlayerHandler() != null) {
                        }
                        break;
                    case "playerHandlerRefresh":
                        app.setPlayerHandler((ModelPlayerHandler) modelEvent.getSingleObject());
                        refreshGameInformations();
                        refreshActionStep();
                        break;
                    case "FirstBuildingStep":
                        app.gameBegin();
                        refreshGameInformations();
                        refreshActionStep();

                }
            }
        });

    }
}

