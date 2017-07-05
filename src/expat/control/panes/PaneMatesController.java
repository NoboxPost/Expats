package expat.control.panes;


import expat.control.procedure.MainGameController;
import expat.model.ModelPlayer;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.LinkedList;

/**
 * the right hand pane that works as a display for other player progress (victory points)
 * <p>
 * created on 29.03.2017
 *
 * @author gallatib
 */
public class PaneMatesController {

    private MainGameController mainGameController;
    @FXML public TextArea matesVictoryPointsTextArea;



    public void init(MainGameController mainGameController){
        this.mainGameController = mainGameController;
    }


    //TODO: should be sorted, the player with most victory points on top
    /**
     * Sets Textareas with informations about enemy players acquired from the app.
     */
    public void refresh(LinkedList<ModelPlayer> allPlayersUnsorted, ModelPlayer currentPlayers){

        String allPlayerStats = "";
        for (ModelPlayer player : allPlayersUnsorted) {
            if (player != currentPlayers) {
                allPlayerStats += (player.getPlayerName() + "\n");
                allPlayerStats += ("Victorypoints: " + player.getWinPointsString() + "\n");
                allPlayerStats += ("Number of Cards: " + player.getMaterial().getSumOfAllMaterials() + "\n\n");
            }
        }
        matesVictoryPointsTextArea.setText(allPlayerStats);


        LinkedList<ModelPlayer> allPlayersSorted = sortForHighestDecreasing(allPlayersUnsorted);



    }

    public static LinkedList<ModelPlayer> sortForHighestDecreasing(LinkedList<ModelPlayer> sortingList) {


        int temp;
        for (int i = 1; i < sortingList.size(); i++) {
            temp = sortingList.get(i).getVictoryPoints();
            int j = i;
            while (j > 0 && sortingList.get(j - 1).getVictoryPoints() > temp) {
                ModelPlayer sortingList.get(j) = sortingList.get(j - 1);
                j--;
            }
            sortingList.get(j).getVictoryPoints() = temp;
        }
        return sortingList;
    }
}
