package expat.control;

import expat.model.ModelApp;
import expat.model.ModelMaterial;
import expat.model.ModelPlayer;
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

    public void init(ControllerMainStage controllerMainStage, ModelApp app){
        this.controllerMainStage = controllerMainStage;
        this.app= app;
    }

    public void setPlayerInformation(String materialPoolString, String winPointsString){
        playerResourcesTextArea.setText(materialPoolString);
        playerVictoryPointsTextArea.setText(winPointsString);
    }



}
