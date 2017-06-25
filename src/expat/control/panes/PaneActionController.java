package expat.control.panes;

import expat.control.procedure.MainGameController;
import expat.view.ViewCardsFactory;
import expat.view.ViewPaneDropMaterial;
import expat.view.ViewPaneTradeGeneral;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


/**
 * the bottom pane where actions like trade or card events happen
 * <p>
 * created on 29.03.2017
 *
 * @author gallatib
 */
public class PaneActionController {
    @FXML
    public Button btnNextStep;
    @FXML
    public Button btnEndTurn;
    @FXML
    public AnchorPane rightActionPane;
    @FXML
    public HBox middleActionPane;
    @FXML
    public Pane leftActionPane;
    @FXML
    public AnchorPane action;
    private MainStageController controllerMainStage;
    private ImageView roadImageView;
    private ImageView townImageView;
    private ImageView settlementImageView;
    private Button diceRollButton;
    private ViewPaneTradeGeneral paneTradeGeneral;
    private ViewPaneDropMaterial paneDropMaterial;
    private MainGameController mainGameController;


    public void init(MainStageController controllerMainStage, MainGameController mainGameController) {
        this.controllerMainStage = controllerMainStage;
        this.mainGameController = mainGameController;
    }

    public void btnNextStepClicked(ActionEvent actionEvent) {
        mainGameController.nextStepSelector();
    }

    public void btnEndTurnClicked(ActionEvent event) {
        /*
        paneTradeGeneral = null;
        app.resetTrade();
        controllerMainStage.refreshMatesAndPlayerPanes();
        */
        mainGameController.endTurnStep();
    }

    public void drawRollDiceStep() {
        middleActionPane.getChildren().clear();

        diceRollButton = new Button("roll dice!");
        diceRollButton.setOnAction(this::btnRollDiceClicked);
        diceRollButton.setMinHeight(60);
        diceRollButton.setMinWidth(60);
        middleActionPane.getChildren().add(diceRollButton);
        btnNextStep.setDisable(true);
        btnEndTurn.setDisable(true);
    }

    public void btnRollDiceClicked(ActionEvent event) {
        mainGameController.diceResultStep();
    }

    public void drawDiceResultStep(int currentDiceNumber, int[] currentDiceNumbersSeperately) {
        middleActionPane.getChildren().clear();

        for (int dice : currentDiceNumbersSeperately) {
            ImageView diceImageView;

            switch (dice) {
                case 1:
                    diceImageView = new ImageView("expat/img/Dice1.png");
                    break;
                case 2:
                    diceImageView = new ImageView("expat/img/Dice2.png");
                    break;
                case 3:
                    diceImageView = new ImageView("expat/img/Dice3.png");
                    break;
                case 4:
                    diceImageView = new ImageView("expat/img/Dice4.png");
                    break;
                case 5:
                    diceImageView = new ImageView("expat/img/Dice5.png");
                    break;
                case 6:
                    diceImageView = new ImageView("expat/img/Dice6.png");
                    break;
                default:
                    diceImageView = new ImageView();
            }
            diceImageView.setFitHeight(80);
            diceImageView.setPreserveRatio(true);

            middleActionPane.getChildren().add(diceImageView);
        }

        generateCards();

        if (currentDiceNumber == 7) {
            Button btnDropMaterials = new Button("drop materials");
            btnDropMaterials.setOnAction(this::btnDropMaterialsClicked);
            middleActionPane.getChildren().add(btnDropMaterials);
        } else {
            btnNextStep.setDisable(false);
            btnEndTurn.setDisable(false);
            btnNextStep.setOnAction(this::btnNextStepClickedSetTradeStep);
        }
    }

    private void btnDropMaterialsClicked(ActionEvent event) {
        mainGameController.dropMaterialStep();
    }

    private void drawDropMaterialStep() {
        middleActionPane.getChildren().clear();
        if (paneDropMaterial == null) {
            if (!app.getPlayersMustDrop().isEmpty()) {
                paneDropMaterial = new ViewPaneDropMaterial(app.getPlayersMustDrop().peek().getMaterial().getMaterialAmount(), this, app.getPlayersMustDrop().peek().getPlayerName());
                middleActionPane.getChildren().add(paneDropMaterial);
            } else {
                app.getBoard().activateRaider();
                Label label = new Label("you can move the raider now");
                middleActionPane.getChildren().add(label);
            }
        } else if (paneDropMaterial != null) {
            if (paneDropMaterial.isDone()) {
                app.playerDroppedMaterial(paneDropMaterial.getEndDifference());
                paneDropMaterial = null;
                refresh();
            } else {
                paneDropMaterial.refresh();
                middleActionPane.getChildren().add(paneDropMaterial);
            }
        }
    }


    /**
     * Initiates a ViewCardFactory and generates the material cards diplayed after the dice roll event.
     */
    public void generateCards() {
        ViewCardsFactory viewCardsFactory = new ViewCardsFactory(app.getNowPlayingDicedMaterial());
        middleActionPane.getChildren().add(viewCardsFactory.generateUnitedCardsHBox());
    }


    /**
     * Generates a Pane displaying, with a settlement and a connection so player can place his fist buildings.
     */
    public void drawFirstSettlementAndRoadStep() {
        middleActionPane.getChildren().clear();


        Label playerName = new Label("Player " + app.getCurrentPlayer().getPlayerName());

        Image settlement = new Image("expat/img/Settlement.png");
        settlementImageView = new ImageView(settlement);
        settlementImageView.setFitHeight(80);
        settlementImageView.setPreserveRatio(true);
        settlementImageView.setOnMouseReleased(this::generateFirstSettlement);
        settlementImageView.setCursor(Cursor.HAND);

        Image road = new Image("expat/img/Connection.png");
        roadImageView = new ImageView(road);
        roadImageView.setFitHeight(80);
        roadImageView.setPreserveRatio(true);
        roadImageView.setOnMouseReleased(this::generateFirstRoad);
        roadImageView.setCursor(Cursor.HAND);

        middleActionPane.getChildren().addAll(playerName, settlementImageView, roadImageView);
        if (app.getFirstBuildingStep() < 2) {
            btnNextStep.setDisable(true);
            btnEndTurn.setDisable(true);
        } else {
            btnNextStep.setDisable(false);
            btnEndTurn.setDisable(false);
        }


    }

    /**
     * Initiates ModelBuildingAction in ModelApp app, so placement checks will be done and building will be built.
     * Settlement will be built if correct Position is clicked afterwards.
     *
     * @param event MouseEvent onMouseReleased
     */
    private void generateFirstSettlement(MouseEvent event) {
        refresh();
        app.firstBuildingAction("Settlement");
        settlementImageView.setEffect(addDropShadow());
    }

    /**
     * Initiates ModelBuildingAction in ModelApp app, so placement checks will be done and building will be built.
     * Road will be built if correct Position is clicked afterwards.
     *
     * @param event MouseEvent onMouseReleased
     */
    private void generateFirstRoad(MouseEvent event) {
        refresh();
        app.firstBuildingAction("Road");
        roadImageView.setEffect(addDropShadow());
    }

    /**
     * Initiates ModelBuildingAction in app so, Player can choose building fields afterwards and corresponding Building will be built.
     */
    public void drawBuildStep() {
        middleActionPane.getChildren().clear();
        Image town = new Image("expat/img/TownColored.png");
        townImageView = new ImageView(town);
        townImageView.setFitHeight(80);
        townImageView.setPreserveRatio(true);
        townImageView.setOnMouseReleased(this::generateTown);
        townImageView.setCursor(Cursor.HAND);

        Image settlement = new Image("expat/img/Settlement.png");
        settlementImageView = new ImageView(settlement);
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

        middleActionPane.getChildren().addAll(townImageView, settlementImageView, roadImageView);
        btnNextStep.setOnAction(this::btnEndTurnClicked);
    }


    /**
     * OnMouseReleased called if a road is clicked. Sends correspondint String to generate Building and drows shadow around choosen building
     *
     * @param event onMouseReleased
     */
    private void generateRoad(MouseEvent event) {
        generateBuilding("Road");
        roadImageView.setEffect(addDropShadow());
    }


    /**
     * OnMouseReleased called if a settlement is clicked.Sends correspondint String to generate Building and drows shadow around choosen building
     *
     * @param event onMouseReleased
     */
    private void generateSettlement(MouseEvent event) {
        generateBuilding("Settlement");
        settlementImageView.setEffect(addDropShadow());
    }

    /**
     * OnMouseReleased called if a Town is clicked. Sends correspondint String to generate Building and drows shadow around choosen building
     *
     * @param event onMouseReleased
     */
    private void generateTown(MouseEvent event) {
        generateBuilding("Town");
        townImageView.setEffect(addDropShadow());
    }


    /**
     * Initiates a new ModelBuildingAciton according to received building type.
     * Calls app with String parameter and refreshes screen.
     *
     * @param type Building type
     */
    private void generateBuilding(String type) {
        refresh();
        controllerMainStage.refreshMatesAndPlayerPanes();
        app.newBuildingAction(type);
    }


    /**
     * Generates a balck Shadow Effect.
     *
     * @return
     */
    private DropShadow addDropShadow() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(30);
        dropShadow.setHeight(25);
        dropShadow.setWidth(200);
        dropShadow.setSpread(0.5);

        return dropShadow;
    }

    /**
     * draws trade step, first lets player choose which trading action he will take, initiates display of the choosen trading action.
     */
    private void drawTradeStep() {
        middleActionPane.getChildren().clear();
        btnNextStep.setOnAction(this::btnNextStepClickedSetBuildingStep);
        if (app.getTradeAction() == null) {
            paneTradeGeneral = null;
            Button btnGeneralTrade = new Button("Allgemeiner Handel 4:1");
            btnGeneralTrade.setOnAction(this::btnGeneralTradingClicked);
            middleActionPane.getChildren().add(btnGeneralTrade);
            btnNextStep.setOnAction(this::btnNextStepClickedSetBuildingStep);
        } else if (app.getTradeAction().getType().equals("GeneralTrade")) {
            if (paneTradeGeneral == null) {
                paneTradeGeneral = new ViewPaneTradeGeneral(app.getCurrentPlayer().getMaterial().getMaterialAmount(), this);
                paneTradeGeneral.refresh();
                middleActionPane.getChildren().add(paneTradeGeneral);

            } else if (paneTradeGeneral != null) {
                paneTradeGeneral.refresh();
                middleActionPane.getChildren().add(paneTradeGeneral);
            }
        }
    }

    /**
     * General trade is initialised in app. Is called by corresponding trade button.
     *
     * @param event
     */
    private void btnGeneralTradingClicked(ActionEvent event) {
        paneTradeGeneral = null;
        app.newTradeAction("GeneralTrade");
        refresh();
    }

    /**
     * adjusts trade amount in trade screen, is called by buttons on trade screen.
     *
     * @param event AcitonEvent
     */
    public void btnTradeAdjustMaterialClicked(ActionEvent event) {
        if (paneTradeGeneral != null) {
            refresh();
            paneTradeGeneral.adjustMaterial((Button) event.getSource());
        }
    }

    /**
     * Sends trade result to app, which will adjust the players material amount.
     *
     * @param event
     */
    public void btnTradeFinishClicked(ActionEvent event) {
        app.finishTradeAction(paneTradeGeneral.getEndDifference());
        app.resetTrade();
        paneTradeGeneral = null;
        controllerMainStage.refreshMatesAndPlayerPanes();
        refresh();
    }


    /**
     * is called from btnNextStep and calls methode from app for trade step
     *
     * @param event ActionEvent
     */
    private void btnNextStepClickedSetTradeStep(ActionEvent event) {
        paneTradeGeneral = null;
        app.tradeStep();
        refresh();
        controllerMainStage.refreshMatesAndPlayerPanes();
    }

    /**
     * is called from btnNextStep and calls methode from app for building step
     *
     * @param event ActionEvent
     */
    public void btnNextStepClickedSetBuildingStep(ActionEvent event) {
        app.buildingStep();
        paneTradeGeneral = null;
        refresh();
        controllerMainStage.refreshMatesAndPlayerPanes();

    }

    /**
     * Refreshes buttons after Raider is moved.
     */
    public void raiderMoved() {
        btnNextStep.setDisable(false);
        btnNextStep.setOnAction(this::btnNextStepClickedSetTradeStep);
        btnEndTurn.setDisable(false);
        //TODO: draw auswahl von Player
    }

    /**
     * After materials to drop are choosen and button to finisch is clicked this methode will be called. Calls the ViewDropMaterialAction and changes its done boolean to true.
     *
     * @param event
     */
    public void btnDropMaterialFinishClicked(ActionEvent event) {
        paneDropMaterial.setDone(true);
        refresh();
        controllerMainStage.refreshMatesAndPlayerPanes();
    }

    /**
     * Is called by buttons on drop material screen to adjust corresponding labels.
     *
     * @param event
     */
    public void btnAdjustedDropMaterialAmountClicked(ActionEvent event) {
        paneDropMaterial.adjustMaterial((Button) event.getSource());
        refresh();

    }


}
