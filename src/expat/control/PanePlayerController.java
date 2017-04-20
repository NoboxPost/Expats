package expat.control;

import expat.model.ModelApp;
import expat.view.ViewCardsFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

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
    @FXML public TextArea playerResourcesTextArea;
    @FXML public TextArea playerVictoryPointsTextArea;
    @FXML public VBox playerResourcesVBox;
    @FXML public Label playerLabel;

    /**
     * Takes a reference for the ControllerMainStage (MainController) and for the apps and stores them in the corresponding fields,
     * so both can be called from within this class.
     *
     * @param controllerMainStage
     * @param app
     */
    public void init(ControllerMainStage controllerMainStage, ModelApp app){
        this.controllerMainStage = controllerMainStage;
        this.app= app;
    }

    /**
     * Sets label with informations about the current players acquired by the app.
     */
    public void setPlayerInformation(){

        if (app.getClientType().equals("solo")) {
            playerLabel.setText("Player " + app.getNowPlaying().getColor());
            playerVictoryPointsTextArea.setText(app.getNowPlaying().getWinPointsString());
        }else {
            playerLabel.setText("Player " +app.getLocalPlayer().getColor());
            playerVictoryPointsTextArea.setText(app.getLocalPlayer().getWinPointsString());
        }
    }


    /**
     * Generates the material cars representing the material stack the current player has.
     * Attaches it to the VBox
     */
    public void generateCards(){
        playerResourcesVBox.getChildren().clear();
        if (app.getClientType().equals("solo")){
            ViewCardsFactory viewCardsFactory = new ViewCardsFactory(app.getNowPlaying().getMaterial());
            playerResourcesVBox.getChildren().add(viewCardsFactory.generateSplittedCardsVBox());
        }else {
            ViewCardsFactory viewCardsFactory = new ViewCardsFactory(app.getLocalPlayer().getMaterial());
            playerResourcesVBox.getChildren().add(viewCardsFactory.generateSplittedCardsVBox());
        }
    }


    /**
     * calls both methods for player informations and his material amounts.
     */
    public void refresh() {
        setPlayerInformation();
        generateCards();
    }
}
