package expat.control.procedure;

import expat.control.panes.*;
import expat.model.ModelApp;
import expat.model.ModelPlayer;

/**
 * Created by gallatib on 22.06.2017.
 */
public class MainGameController extends GameController {

    private ModelApp app;

    public MainGameController(MainStageController mainStageController, PaneActionController paneActionController, PaneBoardController paneBoardController, PaneMatesController paneMatesController, PanePlayerController panePlayerController, ModelApp app) {
        super(mainStageController, paneActionController, paneBoardController, paneMatesController, panePlayerController);
        this.app = app;
    }


    /*
    public void finishesBuildingActionAndChangesToNextPlayerIfNeeded(int[] coords, String type) {
        if ((countConnectionsForCurrentPlayer() == firstBuildingStep + 1 && countBuildingsForCurrentPlayer() == firstBuildingStep + 1) && firstBuildingStep < 2) {
            if (firstBuildingActionSequence.nextPlayer()) {
                currentPlayer = firstBuildingActionSequence.getCurrentPlayer();
            } else {
                nextPlayer();
            }
        }
        if (firstBuildingStep >= 2 || (countBuildingsForCurrentPlayer() == firstBuildingStep && type.equals("Building") || countConnectionsForCurrentPlayer() == firstBuildingStep && type.equals("Connection"))) {
            if (board.finishBuildingAction(coords, type)) {
                if (type.equals("Building")) {
                    currentPlayer.changeVictoryPoints(1);
                }
            }
        }
        boolean allOnSameFirstBuildingStep = true;
        if (firstBuildingStep < 2) {
            for (ModelPlayer player : players) {
                if ((board.countBuildingsOwned(player) == firstBuildingStep + 1 && board.countConnectionsOwned(player) == firstBuildingStep + 1)) {

                } else {
                    allOnSameFirstBuildingStep = false;
                }
            }
            if (allOnSameFirstBuildingStep) {
                firstBuildingStep += 1;
                if (firstBuildingActionSequence.nextPlayer()) {
                    currentPlayer = firstBuildingActionSequence.getCurrentPlayer();
                } else {
                    nextPlayer();
                }
            }
            if (firstBuildingStep >= 2) {
                resourceStep();
            }
        }
        if ((countConnectionsForCurrentPlayer() == firstBuildingStep + 1 && countBuildingsForCurrentPlayer() == firstBuildingStep + 1) && firstBuildingStep < 2) {
            if (firstBuildingActionSequence.nextPlayer()) {
                currentPlayer = firstBuildingActionSequence.getCurrentPlayer();
            } else {
                nextPlayer();
            }
        }
    }
     */


    @Override
    public void processAllTurnSteps(ModelPlayer player) {
    }

    public void nextStepSelector(){
        switch (currentStep){
            case "diceResultStep":
                tradeStep();
                break;
            case "trade":
                buildStep();
                break;
            case "build":
                endTurnStep();
        }
    }

    public void startTurnStep(){
        currentStep = "startTurn";
        currentPlayer = players.getFirst();
        rollDiceStep();
    }

    public void rollDiceStep(){
        currentStep = "rollDice";
        paneActionController.drawRollDiceStep();
        app.rollDice();
        app.distributeMaterial();
    }

    public void diceResultStep(){
        currentStep = "diceResultStep";
        paneActionController.drawDiceResultStep(app.getCurrentDiceNumber(), app.getCurrentDiceNumbersSeparately());
    }

    public void dropMaterialStep(){
        currentStep = "dropMaterial";

        /*
        app.specialStep();
        refresh();
        controllerMainStage.refreshMatesAndPlayerPanes();
         */

    }

    public void tradeStep(){
        currentStep = "trade";

    }

    public void buildStep(){
        currentStep = "build";

    }

    public void specialStep(){
        currentStep = "special";

        /*
        if (!currentStep.equals("SpecialStep") && playersMustDrop.isEmpty()) {
            for (ModelPlayer player : players) {
                if (player.getMaterial().getSumOfAllMaterials() > 7) {
                    playersMustDrop.add(player);
                }
            }
        } else if (currentStep.equals("SpecialStep") && playersMustDrop.isEmpty()) {
            board.activateRaider();
        }
        currentStep = "SpecialStep";
        diceNumber = 0;
         */
    }

    public void endTurnStep(){
        currentStep = "endTurn";
        players.addLast(players.getFirst());
        players.removeFirst();
        startTurnStep();
    }
}



/*

public void nextPlayer() {
    board.abortBuildingAction();
    players.add(players.poll());
    currentPlayer = players.peek();
}

public void gameOver() {
    //TODO: implement game over condition
}
 */
