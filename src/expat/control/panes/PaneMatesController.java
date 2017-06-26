package expat.control.panes;


import expat.control.procedure.GameController;
import expat.control.procedure.MainGameController;
import expat.control.procedure.PreGameController;
import expat.model.ModelApp;
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
        mainGameController.refreshMatesInformation();
    }


    //TODO: should be sorted, the player with most victory points on top
    /**
     * Sets Textareas with informations about enemy players acquired from the app.
     */
    public void refresh(LinkedList<ModelPlayer> players){
        String allPlayerStats = "";
        for (ModelPlayer element : players) {
            if (element != players.getFirst()) {
                allPlayerStats += (element.getPlayerName() + "\n");
                allPlayerStats += ("Victorypoints: " + element.getWinPointsString() + "\n");
                allPlayerStats += ("Number of Cards: " + element.getMaterial().getSumOfAllMaterials() + "\n\n");
            }
        }
        matesVictoryPointsTextArea.setText(allPlayerStats);
    }
}
