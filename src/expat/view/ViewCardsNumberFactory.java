package expat.view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * is responsible for
 * <p>
 * created on 05.04.2017
 *
 * @author gallatib
 */
public class ViewCardsNumberFactory {

    public ViewCardsNumberFactory() {
    }

    /**
     * @param type
     * @return
     */
    public Label generateCardNumber(String type){

        Label cardNumberLabel = new Label();

        switch (type) {
            case "Clay":
                cardNumberLabel.getStyleClass().add("cardNumberLabelClay");
                break;
            case "Grain":
                cardNumberLabel.getStyleClass().add("cardNumberLabelGrain");
                break;
            case "Stone":
                cardNumberLabel.getStyleClass().add("cardNumberLabelStone");
                break;
            case "Wood":
                cardNumberLabel.getStyleClass().add("cardNumberLabelWood");
                break;
            case "Wool":
                cardNumberLabel.getStyleClass().add("cardNumberLabelWool");
                break;
            default:
                cardNumberLabel.getStyleClass().add("cardNumberLabelWater");
        }

        return cardNumberLabel;
    }
}
