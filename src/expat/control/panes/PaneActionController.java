package expat.control.panes;

import expat.control.procedure.MainGameController;
import expat.control.procedure.PreGameController;
import expat.model.ModelMaterial;
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
    private PreGameController preGameController;


    public void init(MainStageController controllerMainStage, MainGameController mainGameController, PreGameController preGameController) {
        this.controllerMainStage = controllerMainStage;
        this.mainGameController = mainGameController;
        this.preGameController = preGameController;
    }

    //Todo:

    /*
    btnNextStep.setVisible(true);
        btnEndTurn.setVisible(true);
     */

    public void drawGameSettings() {
        middleActionPane.getChildren().clear();

        //TODO: replace this button with viewPaneCreateGame
        Button createGameButton = new Button("create Game");
        createGameButton.setOnAction(this::btnCreateGameClicked);
        createGameButton.setMinHeight(60);
        createGameButton.setMinWidth(60);
        middleActionPane.getChildren().add(createGameButton);
        btnNextStep.setVisible(false);
        btnEndTurn.setVisible(false);
    }

    //todo: number of players is still hardcoded
    private void btnCreateGameClicked(ActionEvent actionEvent) {
        preGameController.buildAndStartGame(3);
    }

    public void drawPreGameBuildingStep() {
        middleActionPane.getChildren().clear();

        Image settlement = new Image("expat/img/Settlement.png");
        settlementImageView = new ImageView(settlement);
        settlementImageView.setFitHeight(80);
        settlementImageView.setPreserveRatio(true);
        settlementImageView.setOnMouseReleased(this::generatePreSettlement);
        settlementImageView.setCursor(Cursor.HAND);

        middleActionPane.getChildren().addAll(settlementImageView);
    }

    private void generatePreSettlement(MouseEvent event) {
        preGameController.initiateBoardElementPlacing("Settlement");
        settlementImageView.setEffect(addDropShadow());
    }

    public void drawPreGameRoadStep() {
        middleActionPane.getChildren().clear();

        Image road = new Image("expat/img/Connection.png");
        roadImageView = new ImageView(road);
        roadImageView.setFitHeight(80);
        roadImageView.setPreserveRatio(true);
        roadImageView.setOnMouseReleased(this::generatePreRoad);
        roadImageView.setCursor(Cursor.HAND);

        middleActionPane.getChildren().addAll(roadImageView);
    }

    //TODO: add ships (connection?)
    private void generatePreRoad(MouseEvent event) {
        preGameController.initiateBoardElementPlacing("Road");
        roadImageView.setEffect(addDropShadow());
    }

    private DropShadow addDropShadow() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(30);
        dropShadow.setHeight(250);
        dropShadow.setWidth(200);
        dropShadow.setSpread(0.5);

        return dropShadow;
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


    public void drawDiceResultNormalStep(int[] currentDiceNumbersSeparately, ModelMaterial nowPlayingDicedMaterial) {
        middleActionPane.getChildren().clear();

        //display the two rolled dices
        generateDiceImageViews(currentDiceNumbersSeparately);

        //display the materials a player gets
        generateCardImageViews(nowPlayingDicedMaterial);

        //next button and end turn button are still disabled from rollDiceStep
        //because this step is for all events except a rolled 7, the next button must be enabled
        btnNextStep.setDisable(false);
        btnEndTurn.setDisable(false);
    }

    public void drawDiceResultSpecialStep(int[] currentDiceNumbersSeparately, ModelMaterial nowPlayingDicedMaterial){
        middleActionPane.getChildren().clear();

        //display the two rolled dices
        generateDiceImageViews(currentDiceNumbersSeparately);

        //display the materials a player gets
        generateCardImageViews(nowPlayingDicedMaterial);

        //next button and end turn button are still disabled from rollDiceStep
        //because this step is a rolled 7, the next step button can stay disabled, but there must be a drop materials button
        Button btnDropMaterials = new Button("drop materials");
        btnDropMaterials.setOnAction(this::btnDropMaterialsClicked);
        middleActionPane.getChildren().add(btnDropMaterials);
    }

    private void generateDiceImageViews(int[] currentDiceNumbersSeparate){
        for (int dice : currentDiceNumbersSeparate) {
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
    }

    private void generateCardImageViews(ModelMaterial nowPlayingDicedMaterial) {
        ViewCardsFactory viewCardsFactory = new ViewCardsFactory(nowPlayingDicedMaterial);
        middleActionPane.getChildren().add(viewCardsFactory.generateUnitedCardsHBox());
    }

    private void btnDropMaterialsClicked(ActionEvent event) {
        mainGameController.dropMaterialStep();
    }

/*
    public void drawDropMaterialStep(int[] dropAmount, String playerNameThatMustDrop) {
        middleActionPane.getChildren().clear();

        paneDropMaterial = new ViewPaneDropMaterial(dropAmount, this, playerNameThatMustDrop);
        middleActionPane.getChildren().add(paneDropMaterial);

        if (paneDropMaterial.isDone()) {
            app.playerDroppedMaterial(paneDropMaterial.getEndDifference());
            paneDropMaterial = null;
        } else {
            paneDropMaterial.refresh();
            middleActionPane.getChildren().add(paneDropMaterial);
        }
    }
    */


    public void drawMoveRaiderStep(){
        middleActionPane.getChildren().clear();
        Label label = new Label("you can move the raider now");
        middleActionPane.getChildren().add(label);
    }

    public void raiderMoved() {
        btnNextStep.setDisable(false);
        btnEndTurn.setDisable(false);
        //TODO: draw auswahl von Player
    }

    /*
    public void btnDropMaterialFinishClicked(ActionEvent event) {
        paneDropMaterial.setDone(true);
        controllerMainStage.refreshMatesAndPlayerPanes();
    }
    */

    public void btnAdjustedDropMaterialAmountClicked(ActionEvent event) {
        paneDropMaterial.adjustMaterial((Button) event.getSource());
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
        townImageView.setOnMouseReleased(this::selectTown);
        townImageView.setCursor(Cursor.HAND);

        Image settlement = new Image("expat/img/Settlement.png");
        settlementImageView = new ImageView(settlement);
        settlementImageView.setFitHeight(80);
        settlementImageView.setPreserveRatio(true);
        settlementImageView.setOnMouseReleased(this::selectSettlement);
        settlementImageView.setCursor(Cursor.HAND);

        Image road = new Image("expat/img/Connection.png");
        roadImageView = new ImageView(road);
        roadImageView.setFitHeight(80);
        roadImageView.setPreserveRatio(true);
        roadImageView.setOnMouseReleased(this::selectRoad);
        roadImageView.setCursor(Cursor.HAND);

        middleActionPane.getChildren().addAll(townImageView, settlementImageView, roadImageView);
        btnNextStep.setOnAction(this::btnEndTurnClicked);
    }


    /**
     * OnMouseReleased called if a road is clicked. Sends correspondint String to generate Building and drows shadow around choosen building
     *
     * @param event onMouseReleased
     */

    private void selectRoad(MouseEvent event) {
        initiateBoardElementPlacingAction("Road");
        roadImageView.setEffect(addDropShadow());
    }


    /**
     * OnMouseReleased called if a settlement is clicked.Sends correspondint String to generate Building and drows shadow around choosen building
     *
     * @param event onMouseReleased
     */
    private void selectSettlement(MouseEvent event) {
        initiateBoardElementPlacingAction("Settlement");
        settlementImageView.setEffect(addDropShadow());
    }

    /**
     * OnMouseReleased called if a Town is clicked. Sends correspondint String to generate Building and drows shadow around choosen building
     *
     * @param event onMouseReleased
     */
    private void selectTown(MouseEvent event) {
        initiateBoardElementPlacingAction("Town");
        townImageView.setEffect(addDropShadow());
    }


    /**
     * Initiates a new ModelBuildingAciton according to received building type.
     * Calls app with String parameter and refreshes screen.
     *
     * @param type Building type
     */

    private void initiateBoardElementPlacingAction(String type) {
        mainGameController.initiateBoardElementPlacing(type);
    }



    /**
     * draws trade step, first lets player choose which trading action he will take, initiates display of the choosen trading action.
     */
    /*
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
    */

    /**
     * General trade is initialised in app. Is called by corresponding trade button.
     *
     * @param event
     */

    /*
    private void btnGeneralTradingClicked(ActionEvent event) {
        paneTradeGeneral = null;
        app.newTradeAction("GeneralTrade");
    }
    */

    /**
     * adjusts trade amount in trade screen, is called by buttons on trade screen.
     *
     * @param event AcitonEvent
     */
    public void btnTradeAdjustMaterialClicked(ActionEvent event) {
        if (paneTradeGeneral != null) {
            paneTradeGeneral.adjustMaterial((Button) event.getSource());
        }
    }

    /**
     * Sends trade result to app, which will adjust the players material amount.
     *
     * @param event
     */

    /*
    public void btnTradeFinishClicked(ActionEvent event) {
        app.finishTradeAction(paneTradeGeneral.getEndDifference());
        app.resetTrade();
        paneTradeGeneral = null;
        controllerMainStage.refreshMatesAndPlayerPanes();
    }
    */


    /**
     * is called from btnNextStep and calls methode from app for trade step
     *
     * @param event ActionEvent
     */

    /*
    private void btnNextStepClickedSetTradeStep(ActionEvent event) {
        paneTradeGeneral = null;
        app.tradeStep();
    }
    */

    /**
     * is called from btnNextStep and calls methode from app for building step
     *
     * @param event ActionEvent
     */

    /*
    public void btnNextStepClickedSetBuildingStep(ActionEvent event) {
        paneTradeGeneral = null;
        app.buildingStep();
    }
    */

}
