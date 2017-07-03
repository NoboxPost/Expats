package expat.control.panes;

import expat.control.procedure.MainGameController;
import expat.control.procedure.PreGameController;
import expat.model.ModelMaterial;
import expat.view.ViewCardsFactory;
import expat.view.ViewPaneDropMaterial;
import expat.view.ViewPaneTradeCommon;
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
    private ViewPaneTradeCommon paneTradeCommon;
    private ViewPaneDropMaterial paneDropMaterial;
    private MainGameController mainGameController;
    private PreGameController preGameController;


    public void init(MainStageController controllerMainStage, MainGameController mainGameController, PreGameController preGameController) {
        this.controllerMainStage = controllerMainStage;
        this.mainGameController = mainGameController;
        this.preGameController = preGameController;
    }

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
        preGameController.buildAndStartGame(2);
    }

    public void drawPreGameBuildingStep() {
        middleActionPane.getChildren().clear();

        Image settlement = new Image("expat/img/Settlement.png");
        settlementImageView = new ImageView(settlement);
        settlementImageView.setFitHeight(80);
        settlementImageView.setPreserveRatio(true);
        settlementImageView.setOnMouseReleased(this::generatePreSettlementPlaces);
        settlementImageView.setCursor(Cursor.HAND);

        middleActionPane.getChildren().addAll(settlementImageView);
    }

    private void generatePreSettlementPlaces(MouseEvent event) {
        preGameController.initiateBoardElementPlacing("Settlement");
        settlementImageView.setEffect(addDropShadow());
    }

    public void drawPreGameRoadStep() {
        middleActionPane.getChildren().clear();

        Image road = new Image("expat/img/Connection.png");
        roadImageView = new ImageView(road);
        roadImageView.setFitHeight(80);
        roadImageView.setPreserveRatio(true);
        roadImageView.setOnMouseReleased(this::generatePreRoadPlaces);
        roadImageView.setCursor(Cursor.HAND);

        middleActionPane.getChildren().addAll(roadImageView);
    }

    //TODO: add ships (connection?)
    private void generatePreRoadPlaces(MouseEvent event) {
        preGameController.initiateBoardElementPlacing("Road");
        roadImageView.setEffect(addDropShadow());
    }

    private DropShadow addDropShadow() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(30);
        dropShadow.setHeight(25);
        dropShadow.setWidth(200);
        dropShadow.setSpread(0.5);

        return dropShadow;
    }

    public void btnNextStepClicked(ActionEvent actionEvent) {
        mainGameController.nextStepSelector();
    }

    public void btnEndTurnClicked(ActionEvent event) {
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
        generateDiceResultContent(currentDiceNumbersSeparately, nowPlayingDicedMaterial);

        //next button and end turn button are still disabled from rollDiceStep
        //because this step is for all events except a rolled 7, the next button must be enabled
        btnNextStep.setDisable(false);
        btnEndTurn.setDisable(false);
    }

    public void drawDiceResultDroppingStep(int[] currentDiceNumbersSeparately, ModelMaterial nowPlayingDicedMaterial){
        generateDiceResultContent(currentDiceNumbersSeparately, nowPlayingDicedMaterial);

        //next button and end turn button are still disabled from rollDiceStep
        //because this step is a rolled 7, the next step button can stay disabled, but there must be a drop materials button
        Button btnDropMaterials = new Button("drop materials");
        btnDropMaterials.setOnAction(this::btnDropMaterialsClicked);
        middleActionPane.getChildren().add(btnDropMaterials);
    }

    public void drawDiceResultRaiderStep(int[] currentDiceNumbersSeparately, ModelMaterial nowPlayingDicedMaterial){
        generateDiceResultContent(currentDiceNumbersSeparately, nowPlayingDicedMaterial);

        //next button and end turn button are still disabled from rollDiceStep
        //because this step is a rolled 7, the next step button can stay disabled, but there must be a drop materials button
        Button btnDropMaterials = new Button("move the raider");
        btnDropMaterials.setOnAction(this::btnMoveRaiderClicked);
        middleActionPane.getChildren().add(btnDropMaterials);
    }

    private void btnMoveRaiderClicked(ActionEvent actionEvent) {
        mainGameController.moveRaiderStep();
    }

    private void generateDiceResultContent(int[] currentDiceNumbersSeparately, ModelMaterial nowPlayingDicedMaterial){
        middleActionPane.getChildren().clear();
        generateDiceImageViews(currentDiceNumbersSeparately);
        generateCardImageViews(nowPlayingDicedMaterial);
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
        mainGameController.firstPlayerDroppingMaterialStep();
    }

    public void drawDropMaterialStep(int dropAmount, int[] playerMaterialAmount) {
        middleActionPane.getChildren().clear();

        paneDropMaterial = new ViewPaneDropMaterial(this, dropAmount, playerMaterialAmount);
        middleActionPane.getChildren().add(paneDropMaterial);
    }

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

    public void btnDropMaterialFinishClicked(ActionEvent event) {
        mainGameController.finishDropping(paneDropMaterial.getDroppingDifference());
    }

    /**
     * draws trade step, first lets player choose which trading action he will take, initiates display of the choosen trading action.
     */
    public void drawTradeChoiceStep() {
        middleActionPane.getChildren().clear();
        Button btnCommonTrade = new Button("common trade (4:1)");
        btnCommonTrade.setOnAction(this::btnCommonTradeClicked);
        middleActionPane.getChildren().add(btnCommonTrade);
    }

    public void drawCommonTradeStep(int[] currentPlayerMaterialAmount){
        middleActionPane.getChildren().clear();
        paneTradeCommon = new ViewPaneTradeCommon(this, currentPlayerMaterialAmount);
        middleActionPane.getChildren().add(paneTradeCommon);
    }

    /**
     * Initiates ModelBuildingAction in app so, Player can choose building fields afterwards and corresponding Building will be built.
     */
    public void drawMainGameBuildingStep() {
        middleActionPane.getChildren().clear();
        Image town = new Image("expat/img/TownColored.png");
        townImageView = new ImageView(town);
        townImageView.setFitHeight(80);
        townImageView.setPreserveRatio(true);
        townImageView.setOnMouseReleased(this::generateMainTownPlaces);
        townImageView.setCursor(Cursor.HAND);

        Image settlement = new Image("expat/img/Settlement.png");
        settlementImageView = new ImageView(settlement);
        settlementImageView.setFitHeight(80);
        settlementImageView.setPreserveRatio(true);
        settlementImageView.setOnMouseReleased(this::generateMainSettlementPlaces);
        settlementImageView.setCursor(Cursor.HAND);

        Image road = new Image("expat/img/Connection.png");
        roadImageView = new ImageView(road);
        roadImageView.setFitHeight(80);
        roadImageView.setPreserveRatio(true);
        roadImageView.setOnMouseReleased(this::generateMainRoadPlaces);
        roadImageView.setCursor(Cursor.HAND);

        middleActionPane.getChildren().addAll(townImageView, settlementImageView, roadImageView);
    }

    /**
     * OnMouseReleased called if a road is clicked. Sends correspondint String to generate Building and drows shadow around choosen building
     *
     * @param event onMouseReleased
     */
    private void generateMainRoadPlaces(MouseEvent event) {
        deactivateTurnNavigation();
        mainGameController.initiateBoardElementPlacing("Road");
        roadImageView.setEffect(addDropShadow());
    }

    /**
     * OnMouseReleased called if a settlement is clicked.Sends correspondint String to generate Building and drows shadow around choosen building
     *
     * @param event onMouseReleased
     */
    private void generateMainSettlementPlaces(MouseEvent event) {
        deactivateTurnNavigation();
        mainGameController.initiateBoardElementPlacing("Settlement");
        settlementImageView.setEffect(addDropShadow());
    }

    /**
     * OnMouseReleased called if a Town is clicked. Sends correspondint String to generate Building and drows shadow around choosen building
     *
     * @param event onMouseReleased
     */
    private void generateMainTownPlaces(MouseEvent event) {
        deactivateTurnNavigation();
        mainGameController.initiateBoardElementPlacing("Town");
        townImageView.setEffect(addDropShadow());
    }

    public void activateTurnNavigation(){
        btnNextStep.setVisible(true);
        btnEndTurn.setVisible(true);
    }

    public void deactivateTurnNavigation(){
        btnNextStep.setVisible(false);
        btnEndTurn.setVisible(false);
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
     * common trade is initialised in app. Is called by corresponding trade button.
     *
     * @param event
     */
    private void btnCommonTradeClicked(ActionEvent event) {
        mainGameController.commonTradeStep();
    }


    public void btnTradeFinishClicked(ActionEvent actionEvent) {
        mainGameController.finishTrading(paneTradeCommon.getTradingDifference());
    }

    /**
     * Sends trade result to app, which will adjust the players material amount.
     *
     * @param event
     */

    /*
    public void btnTradeFinishClicked(ActionEvent event) {
        app.finishTradeAction(paneTradeCommon.getEndDifference());
        app.resetTrade();
        paneTradeCommon = null;
        controllerMainStage.refreshMatesAndPlayerPanes();
    }
    */
}
