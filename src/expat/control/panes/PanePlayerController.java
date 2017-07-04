package expat.control.panes;

import com.sun.javafx.css.Style;
import expat.control.procedure.MainGameController;
import expat.model.ModelMaterial;
import expat.view.ViewCardsFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * the left hand pane that works as an oversight of the actual player,
 * including his material-cards, his victory points and his development cards
 * <p>
 * created on 29.03.2017
 *
 * @author gallatib
 */

public class PanePlayerController {

    private MainGameController mainGameController;
    @FXML
    public TextArea playerResourcesTextArea;
    @FXML
    public TextArea playerVictoryPointsTextArea;
    @FXML
    public VBox playerResourcesVBox;
    @FXML
    public Label playerLabel;

    /**
     * Takes a reference for the MainStageController (MainController) and for the apps and stores them in the corresponding fields,
     * so both can be called from within this class.
     */
    public void init(MainGameController mainGameController) {
        this.mainGameController = mainGameController;
    }

    /**
     * Sets label with informations about the current players acquired by the app.
     */
    public void setPlayerInformation(String playerColor, String playerWinpoints) {
        playerLabel.setText("Player " + playerColor);
        playerLabel.setStyle("-fx-background-color: " +playerColor.toLowerCase()+";");
        playerVictoryPointsTextArea.setText(playerWinpoints);
    }

    /**
     * Generates the material cars representing the material stack the current player has.
     * Attaches it to the VBox
     */
    public void generateCards(ModelMaterial playerMaterial) {
        playerResourcesVBox.getChildren().clear();
        ViewCardsFactory viewCardsFactory = new ViewCardsFactory(playerMaterial);
        playerResourcesVBox.getChildren().add(viewCardsFactory.generateSplittedCardsVBox());
    }

    /**
     * calls both methods for player informations and his material amounts.
     */
    public void refresh(String playerColor, String playerWinpoints, ModelMaterial playerMaterial) {
        setPlayerInformation(playerColor, playerWinpoints);
        generateCards(playerMaterial);
    }
}
