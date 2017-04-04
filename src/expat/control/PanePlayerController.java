package expat.control;

import expat.model.ModelApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * the left hand pane that works as an oversight of the actual player,
 * including his material-cards, his victory points and his development cards
 * <p>
 * created on 29.03.2017
 *
 * @author gallatib
 */

public class PanePlayerController {

    private ControllerMainStage controllerMainStage;
    private ModelApp app;
    public TextArea playerResourcesTextArea;
    public TextArea playerVictoryPointsTextArea;
    @FXML public Label playerLabel;

    public void init(ControllerMainStage controllerMainStage, ModelApp app){
        this.controllerMainStage = controllerMainStage;
        this.app= app;
    }

    public void setPlayerInformation(String playerName, String materialPoolString, String winPointsString){
        playerLabel.setText(playerName);
        playerResourcesTextArea.setText(materialPoolString);
        playerVictoryPointsTextArea.setText(winPointsString);
    }



}
