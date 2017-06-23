package expat.control.panes;

import expat.model.ModelApp;
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
    @FXML public Button btnNextStep;
    @FXML public Button btnEndTurn;
    @FXML public AnchorPane rightActionPane;
    @FXML public HBox middleActionPane;
    @FXML public Pane leftActionPane;
    @FXML public AnchorPane action;
    private MainStageController controllerMainStage;
    private ImageView roadImageView;
    private ImageView townImageView;
    private ImageView settlementImageView;
    private Button diceRollButton;
    private ViewPaneTradeGeneral paneTradeGeneral;
    private ViewPaneDropMaterial paneDropMaterial;
    private ModelApp app;


    /**
     * Needs to be called at program start. Injects MainStageController and ModelApp into this controller, so he can call back.
     *
     * @param controllerMainStage reference to MainStageController.
     * @param app reference to ModelApp generatet by ControlerMainStage.
     */
    public void init(MainStageController controllerMainStage, ModelApp app) {
        this.controllerMainStage = controllerMainStage;
        this.app = app;
        refreshStep();
    }

    /**
     *Generates the different views according to state acquired from app. First displays the the Button which will roll the dices.
     * Second will display the dice result and the materials the actual player has got.
     */
    public void drawResourceStep() {
        middleActionPane.getChildren().clear();
        if (app.getDiceNumber() != 0) {
            for (int dice : app.getDiceNumbersSeparately()) {
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
            if (app.getDiceNumber() == 7) {
                Button btnDropMaterials = new Button("Materialien abgeben");
                btnDropMaterials.setOnAction(this::btnDropMaterialsClicked);
                middleActionPane.getChildren().add(btnDropMaterials);
            } else {
                btnNextStep.setDisable(false);
                btnEndTurn.setDisable(false);
                btnNextStep.setOnAction(this::btnNextStepClickedSetTradeStep);
            }

        } else {
            diceRollButton = new Button("roll dice!");
            diceRollButton.setOnAction(this::diceRollClicked);
            diceRollButton.setMinHeight(60);
            diceRollButton.setMinWidth(60);
            middleActionPane.getChildren().add(diceRollButton);
            btnNextStep.setDisable(true);
            btnEndTurn.setDisable(true);
        }
    }

    /**
     * After a player decides which materials he will drop incases of dices = 7 and materials > 7.
     * This method will be called by the btnDropMaterial. Methode will initiate next step in app and refreshes the screen.
     *
     * @param event ActionEvent
     */
    private void btnDropMaterialsClicked(ActionEvent event) {
        app.specialStep();
        refreshStep();
        controllerMainStage.refreshGameInformations();

    }

    /**
     * Will initiate app diceRoll and all further stepps. Is called by the btnRollDice.
     * Refreshes the actionPane.
     *
     * @param event
     */
    public void diceRollClicked(ActionEvent event) {
        app.rollDice();
        refreshStep();
        controllerMainStage.refreshGameInformations();
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


        Label playerName = new Label("Player "+app.getNowPlaying().getPlayerName());

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

        middleActionPane.getChildren().addAll(playerName,settlementImageView, roadImageView);
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
        refreshStep();
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
        refreshStep();
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
        refreshStep();
        controllerMainStage.refreshGameInformations();
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
                paneTradeGeneral = new ViewPaneTradeGeneral(app.getNowPlaying().getMaterial().getMaterialAmount(), this);
                paneTradeGeneral.refresh();
                middleActionPane.getChildren().add(paneTradeGeneral);

            } else if (paneTradeGeneral != null) {
                paneTradeGeneral.refresh();
                middleActionPane.getChildren().add(paneTradeGeneral);
            }
        }
    }

    /**
     * If dice number is 7 drop material screen will be displayed for each player who has to much cards.
     *
     *
     */
    private void drawSpecialStep() {
        middleActionPane.getChildren().clear();
        if (paneDropMaterial == null) {
            if (!app.getPlayersMustDrop().isEmpty()) {
                paneDropMaterial = new ViewPaneDropMaterial(app.getPlayersMustDrop().peek().getMaterial().getMaterialAmount(), this,app.getPlayersMustDrop().peek().getPlayerName());
                middleActionPane.getChildren().add(paneDropMaterial);
            } else {
                app.getBoard().activateRaider();
                Label label = new Label("You can move the raider now");
                middleActionPane.getChildren().add(label);
            }
        } else if (paneDropMaterial != null) {
            if (paneDropMaterial.isDone()) {
                app.playerDroppedMaterial(paneDropMaterial.getEndDifference());
                paneDropMaterial = null;
                refreshStep();
            } else {
                paneDropMaterial.refresh();
                middleActionPane.getChildren().add(paneDropMaterial);
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
        refreshStep();
    }

    /**
     * adjusts trade amount in trade screen, is called by buttons on trade screen.
     *
     * @param event AcitonEvent
     */
    public void btnTradeAdjustMaterialClicked(ActionEvent event) {
        if (paneTradeGeneral != null) {
            refreshStep();
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
        controllerMainStage.refreshGameInformations();
        refreshStep();
    }


    /**
     * Can be called during most turn steps so next player will be displayed an can start his action.
     *
     * @param event
     */
    public void btnEndTurnClicked(ActionEvent event) {
        paneTradeGeneral = null;
        app.resetTrade();
        app.nextPlayer();
        app.resourceStep();
        refreshStep();
        controllerMainStage.refreshGameInformations();
    }

    /**
     * gets the current step from app, calls the correspoinding draw methode.
     */
    public void refreshStep() {
        String currentStep = app.getCurrentStep();
        switch (currentStep) {
            case "FirstBuildingStep":
                drawFirstSettlementAndRoadStep();
                break;
            case "BuildingStep":
                drawBuildStep();
                break;
            case "ResourceStep":
                drawResourceStep();
                break;
            case "TradeStep":
                drawTradeStep();
                break;
            case "SpecialStep":
                drawSpecialStep();
                break;
        }
    }

    /**
     * is called from btnNextStep and calls methode from app for trade step
     *
     * @param event ActionEvent
     */
    private void btnNextStepClickedSetTradeStep(ActionEvent event) {
        paneTradeGeneral = null;
        app.tradeStep();
        refreshStep();
        controllerMainStage.refreshGameInformations();
    }

    /**
     * is called from btnNextStep and calls methode from app for building step
     *
     * @param event ActionEvent
     */
    public void btnNextStepClickedSetBuildingStep(ActionEvent event) {
        app.buildingStep();
        paneTradeGeneral = null;
        refreshStep();
        controllerMainStage.refreshGameInformations();

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
        refreshStep();
        controllerMainStage.refreshGameInformations();
    }

    /**
     * Is called by buttons on drop material screen to adjust corresponding labels.
     *
     * @param event
     */
    public void btnAdjustedDropMaterialAmountClicked(ActionEvent event) {
        paneDropMaterial.adjustMaterial((Button) event.getSource());
        refreshStep();

    }
}
