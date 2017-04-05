package expat.view;

import expat.model.ModelMaterial;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * is responsible for
 * <p>
 * created on 04.04.2017
 *
 * @author gallatib
 */
public class ViewCardsFactory {
    ModelMaterial material;
    ViewCardsNumberFactory cardsNumberFactory;

    public ViewCardsFactory(ModelMaterial material) {
        this.material = material;
    }


    public HBox generateUnitedCardsHBox() {
        HBox unitedCardsHBox = new HBox();
        unitedCardsHBox.setMaxHeight(80);
        unitedCardsHBox.setSpacing(5);

        for(int i = 0; i<5; i++) {
            for (int j = 0; j < material.getMaterialAmount()[i]; j++) {
                        unitedCardsHBox.getChildren().add(generateCardImageView(i));
            }
        }
        return unitedCardsHBox;
    }

    public Pane generateSplittedCardsVBox() {
        VBox splittedCardsVBox = new VBox();
        splittedCardsVBox.setSpacing(30);
        splittedCardsVBox.setPadding(new Insets(20));

        for(int i = 0; i<5; i++) {
            Pane splittedMaterialPane = new Pane();
            double yOffset = 20;
            double xOffset = 0;
            splittedMaterialPane.setMinHeight(100);

            for (int j = 0; j < material.getMaterialAmount()[i]; j++) {
                ImageView temp = generateCardImageView(i);
                temp.setLayoutX(xOffset);
                temp.setLayoutY(yOffset);
                yOffset-=2;
                xOffset+=10;
                splittedMaterialPane.getChildren().add(temp);
            }
            splittedCardsVBox.getChildren().add(splittedMaterialPane);
        }
        return splittedCardsVBox;
    }

    public ImageView generateCardImageView(int i){
        return generateCardImageView(material.getMaterialNames()[i]);
    }

    public ImageView generateCardImageView(String type){
        ImageView cardImageView;

        switch (type) {
            case "Clay":
                cardImageView = new ImageView("expat/img/CardClay.png");
                break;
            case "Grain":
                cardImageView = new ImageView("expat/img/CardGrain.png");
                break;
            case "Stone":
                cardImageView = new ImageView("expat/img/CardStone.png");
                break;
            case "Wood":
                cardImageView = new ImageView("expat/img/CardWood.png");
                break;
            case "Wool":
                cardImageView = new ImageView("expat/img/CardWool.png");
                break;
            default:
                cardImageView = new ImageView();
        }
        return cardImageView;
    }



}
