package expat.control;

import expat.model.ModelApp;
import expat.view.ViewCardsFactory;
import javafx.event.ActionEvent;

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
    public Button btnNextStep;
    public Button btnEndTurn;
    public AnchorPane rightActionPane;
    public HBox middleActionPane;
    public Pane leftActionPane;
    private ControllerMainStage controllerMainStage;
    public AnchorPane action;
    private ImageView roadImageView;
    private ImageView townImageView;
    private ImageView settlementImageView;
    private Button diceRollButton;
    ModelApp app;


    public void init(ControllerMainStage controllerMainStage, ModelApp app) {
        this.controllerMainStage = controllerMainStage;
        this.app = app;
    }

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

            btnNextStep.setDisable(false);
            btnEndTurn.setDisable(false);
            btnNextStep.setOnAction(this::btnNextStepClickedSetBuildingStep);

        } else {
            diceRollButton = new Button("roll dice!");
            diceRollButton.setOnAction(this::diceRollClicked);
            middleActionPane.getChildren().add(diceRollButton);
            btnNextStep.setDisable(true);
            btnEndTurn.setDisable(true);
        }
    }

    public void diceRollClicked(ActionEvent event) {
        app.rollDice();
        refreshStep();
        controllerMainStage.refreshPlayerPane();
    }

    public void generateCards() {
        ViewCardsFactory viewCardsFactory = new ViewCardsFactory(app.getNowPlayingDicedMaterial());
        middleActionPane.getChildren().add(viewCardsFactory.generateUnitedCardsHBox());
    }


    public void drawFirstSettlementAndRoadStep() {
        middleActionPane.getChildren().clear();

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

        middleActionPane.getChildren().addAll(settlementImageView, roadImageView);
        if (app.getFirstBuildingStep() < 2) {
            btnNextStep.setDisable(true);
            btnEndTurn.setDisable(true);
        } else {
            btnNextStep.setDisable(false);
            btnEndTurn.setDisable(false);
        }


    }

    private void generateFirstSettlement(MouseEvent event) {
        refreshStep();
        app.firstBuildingAction("Settlement");
        settlementImageView.setEffect(addDropShadow());
    }

    private void generateFirstRoad(MouseEvent event) {
        refreshStep();
        app.firstBuildingAction("Road");
        roadImageView.setEffect(addDropShadow());
    }

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


    private void generateRoad(MouseEvent event) {
        generateBuilding("Road");
        roadImageView.setEffect(addDropShadow());
    }


    private void generateSettlement(MouseEvent event) {
        generateBuilding("Settlement");
        settlementImageView.setEffect(addDropShadow());
    }

    private void generateTown(MouseEvent event) {
        generateBuilding("Town");
        townImageView.setEffect(addDropShadow());
    }

    private void generateBuilding(String type) {
        refreshStep();
        controllerMainStage.refreshPlayerPane();
        app.newBuildingAction(type);
    }

    private DropShadow addDropShadow() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(30);
        dropShadow.setHeight(25);
        dropShadow.setWidth(200);
        dropShadow.setSpread(0.5);

        return dropShadow;
    }

    private void drawTradeStep() {
        middleActionPane.getChildren().clear();
        if (app.getTradeAction() == null) {
            Button btnGeneralTrade = new Button("Allgemeiner Handel 4:1");
            btnGeneralTrade.setOnAction(this::btnGeneralTradingClicked);
        }else if (app.getTradeAction().getType().equals("GeneralTrade")){ }

    }

    private void drawSpecialStep() {
        middleActionPane.getChildren().clear();

        Label label = new Label("You can move the raider now");
        middleActionPane.getChildren().add(label);


    }

    private void btnGeneralTradingClicked(ActionEvent event) {
        app.newTraidingAction("GeneralTrade");
    }


    public void btnEndTurnClicked(ActionEvent event) {
//        app.nextPlayer();
//        app.resourceStep();
        app.specialStep();
        refreshStep();
        controllerMainStage.refreshPlayerPane();
    }

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
            case "TraidingStep":
                drawTradeStep();
            case "SpecialStep":
                drawSpecialStep();
        }
    }


    public void btnNextStepClickedSetBuildingStep(ActionEvent event) {
        app.buildingStep();
        refreshStep();
        controllerMainStage.refreshPlayerPane();
        controllerMainStage.refreshActionStep();
    }

    /**
     * Probably unused!
     *
     * @param event
     */
    @Deprecated
    public void btnNextStepClickedSetResourceStep(ActionEvent event) {
        app.resourceStep();
        refreshStep();
        controllerMainStage.refreshPlayerPane();
        controllerMainStage.refreshActionStep();
    }

}
