package expat.control.panes;

import expat.control.procedure.MainGameController;
import expat.control.procedure.PreGameController;
import expat.model.ModelMaterial;
import expat.view.ViewCardsFactory;
import expat.view.ViewPaneBuild;
import expat.view.ViewPaneDropMaterial;
import expat.view.ViewPaneTradeCommon;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.HashMap;


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

        btnNextStep.setCursor(Cursor.HAND);
        btnEndTurn.setCursor(Cursor.HAND);
    }

    public void drawGameSettings() {
        middleActionPane.getChildren().clear();

        //TODO: replace this button with viewPaneCreateGame
        Button createGameButton = new Button("create Game");
        createGameButton.setCursor(Cursor.HAND);
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
        diceRollButton.setCursor(Cursor.HAND);
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
        btnDropMaterials.setCursor(Cursor.HAND);
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
        controllerMainStage.changeCursorOverAll(new ImageCursor(new Image("expat/img/Raider.png")));
    }

    public void raiderMoved() {
        btnNextStep.setDisable(false);
        btnEndTurn.setDisable(false);
        mainGameController.nextStepSelector();
        controllerMainStage.changeCursorOverAll(Cursor.DEFAULT);
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
        btnCommonTrade.setCursor(Cursor.HAND);
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
    public void drawMainGameBuildingStep(HashMap<String, Boolean> playerBuildingAbilities) {
        middleActionPane.getChildren().clear();
        controllerMainStage.changeCursorOverAll(Cursor.DEFAULT);

        ViewPaneBuild viewPaneBuild = new ViewPaneBuild(playerBuildingAbilities, this);

        roadImageView = viewPaneBuild.generateRoadImageView();
        settlementImageView = viewPaneBuild.generateSettlementImageView();
        townImageView = viewPaneBuild.generateTownImageView();

        Button btnCancelBuildingAction = new Button("cancel building");
        btnCancelBuildingAction.setOnAction(this::btnCancelBuildingAction);
        btnCancelBuildingAction.setCursor(Cursor.HAND);

        middleActionPane.getChildren().addAll(roadImageView, settlementImageView, townImageView, btnCancelBuildingAction);
    }

    private void btnCancelBuildingAction(ActionEvent actionEvent) {
        mainGameController.buildingStep();
    }

    /**
     * OnMouseReleased called if a road is clicked. Sends correspondint String to generate Building and drows shadow around choosen building
     *
     * @param event onMouseReleased
     */
    public void generateMainRoadPlaces(MouseEvent event) {
        deactivateTurnNavigation();
        mainGameController.initiateBoardElementPlacing("Road");
        Image roadImage = roadImageView.getImage();
        controllerMainStage.changeCursorOverAll(new ImageCursor(roadImage, 0, 0));
    }

    /**
     * OnMouseReleased called if a settlement is clicked.Sends correspondint String to generate Building and drows shadow around choosen building
     *
     * @param event onMouseReleased
     */
    public void generateMainSettlementPlaces(MouseEvent event) {
        deactivateTurnNavigation();
        mainGameController.initiateBoardElementPlacing("Settlement");
        Image settlementImage = settlementImageView.getImage();
        controllerMainStage.changeCursorOverAll(new ImageCursor(settlementImage, 0, 0));
    }

    /**
     * OnMouseReleased called if a Town is clicked. Sends correspondint String to generate Building and drows shadow around choosen building
     *
     * @param event onMouseReleased
     */
    public void generateMainTownPlaces(MouseEvent event) {
        deactivateTurnNavigation();
        mainGameController.initiateBoardElementPlacing("Town");
        Image townImage = townImageView.getImage();
        controllerMainStage.changeCursorOverAll(new ImageCursor(townImage, 0, 0));
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
}
