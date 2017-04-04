package expat.view;

import expat.control.PaneActionController;
import expat.control.PanePlayerController;
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

        ImageView cardImageView;

        for(int i = 0; i<5; i++) {
            for (int j = 0; j < material.getMaterialAmount()[i]; j++) {
                switch(material.getMaterialNames()[i]){
                    case "Clay":
                        cardImageView = new ImageView("expat/img/CardClay.png");
                        unitedCardsHBox.getChildren().add(cardImageView);
                        break;
                    case "Grain":
                        cardImageView = new ImageView("expat/img/CardGrain.png");
                        unitedCardsHBox.getChildren().add(cardImageView);
                        break;
                    case "Stone":
                        cardImageView = new ImageView("expat/img/CardStone.png");
                        unitedCardsHBox.getChildren().add(cardImageView);
                        break;
                    case "Wood":
                        cardImageView = new ImageView("expat/img/CardWood.png");
                        unitedCardsHBox.getChildren().add(cardImageView);
                        break;
                    case "Wool":
                        cardImageView = new ImageView("expat/img/CardWool.png");
                        unitedCardsHBox.getChildren().add(cardImageView);
                        break;
                }

            }
        }
        return unitedCardsHBox;
    }

    public Pane generateSplittedCardsVBox() {
        VBox splittedCardsVBox = new VBox();
        splittedCardsVBox.setMaxWidth(200);

        ImageView cardImageView;

        for(int i = 0; i<5; i++) {
            HBox splittedMaterialHBox = new HBox();
            for (int j = 0; j < material.getMaterialAmount()[i]; j++) {
                switch (material.getMaterialNames()[i]) {
                    case "Clay":
                        cardImageView = new ImageView("expat/img/CardClay.png");
                        splittedMaterialHBox.getChildren().add(cardImageView);
                        break;
                    case "Grain":
                        cardImageView = new ImageView("expat/img/CardGrain.png");
                        splittedMaterialHBox.getChildren().add(cardImageView);
                        break;
                    case "Stone":
                        cardImageView = new ImageView("expat/img/CardStone.png");
                        splittedMaterialHBox.getChildren().add(cardImageView);
                        break;
                    case "Wood":
                        cardImageView = new ImageView("expat/img/CardWood.png");
                        splittedMaterialHBox.getChildren().add(cardImageView);
                        break;
                    case "Wool":
                        cardImageView = new ImageView("expat/img/CardWool.png");
                        splittedMaterialHBox.getChildren().add(cardImageView);
                        break;
                }
            }
            splittedCardsVBox.getChildren().add(splittedMaterialHBox);
        }
        return splittedCardsVBox;

    }



}
