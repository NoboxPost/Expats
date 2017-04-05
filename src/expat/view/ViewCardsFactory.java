package expat.view;

import expat.model.ModelMaterial;
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

    public ViewCardsFactory(ModelMaterial material) {
        this.material = material;
    }


    public HBox generateUnitedCardsHBox() {
        HBox unitedCardsHBox = new HBox();
        unitedCardsHBox.setMaxHeight(80);

        for(int i = 0; i<5; i++) {
            for (int j = 0; j < material.getMaterialAmount()[i]; j++) {
                        unitedCardsHBox.getChildren().add(generateCardImageView(i));
            }
        }
        return unitedCardsHBox;
    }

    public Pane generateSplittedCardsVBox() {
        VBox splittedCardsVBox = new VBox();
        splittedCardsVBox.setMaxWidth(200);

        for(int i = 0; i<5; i++) {
            HBox splittedMaterialHBox = new HBox();
            for (int j = 0; j < material.getMaterialAmount()[i]; j++) {
                splittedMaterialHBox.getChildren().add(generateCardImageView(i));
            }
            splittedCardsVBox.getChildren().add(splittedMaterialHBox);
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
