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

    public Label generateCardNumber(String type){

        BackgroundImage backgroundImage;

        /*
        switch (type) {
            case "Clay":
                backgroundImage = new BackgroundImage(new Image("expat/img/Clay.png", 50, 50, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                break;
            case "Grain":
                backgroundImage = new BackgroundImage(new Image("expat/img/Grain.png", 50, 50, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                break;
            case "Stone":
                backgroundImage = new BackgroundImage(new Image("expat/img/Stone.png", 50, 50, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                break;
            case "Wood":
                backgroundImage = new BackgroundImage(new Image("expat/img/Wood.png", 50, 50, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                break;
            case "Wool":
                backgroundImage = new BackgroundImage(new Image("expat/img/Wool.png", 50, 50, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                break;
            default:
                backgroundImage = new BackgroundImage(new Image("expat/img/Water.png", 50, 50, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        }

        Label cardNumberLabel = new Label();
        cardNumberLabel.setBackground(new Background(backgroundImage));
        cardNumberLabel.setMinSize(50, 50);
        cardNumberLabel.setMaxSize(50, 50);
        cardNumberLabel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(100), BorderWidths.DEFAULT)));

        */

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
