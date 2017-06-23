package expat.control.procedure;

import expat.control.panes.MainStageController;
import expat.model.ModelApp;
import expat.model.ModelPlayer;

/**
 * Created by gallatib on 22.06.2017.
 */
public class MainGame extends Game {


    public MainGame(MainStageController mainStageController, ModelApp app) {
        super(mainStageController, app);
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
        rollDiceTurnStep();
        tradeTurnStep();
        buildTurnStep();
        specialTurnStep();
    }

    private void rollDiceTurnStep(){
        currentStep = "rollDice";
        app.rollDice();
        app.distributeMaterial();
        mainStageController.refreshAllInformationPanes();
    }

    private void tradeTurnStep(){
        currentStep = "trade";

    }

    private void buildTurnStep(){
        currentStep = "build";

    }

    private void specialTurnStep(){
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
