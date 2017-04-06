package expat.view;

import expat.model.ModelMaterial;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
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


    /**
     * Constructor for a new ViewCardFactory, takes a ModelMaterial so amount of type of specific material can be generated.
     *
     * @param material ModelMaterial can be a new ModelMaterial with all amounts = 0.
     */
    public ViewCardsFactory(ModelMaterial material) {
        this.material = material;
    }

    /**
     * Generates a a HBox with cards side by side which is added in the result screen of the dice roll.
     *
     * @return HBox with cards representing the amount of materials given in the constructor.
     */
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
        splittedCardsVBox.setPadding(new Insets(20, 0,0,0));

        cardsNumberFactory = new ViewCardsNumberFactory();

        for(int i = 0; i<5; i++) {


            Pane splittedMaterialPane = new Pane();
            double yOffset = 20;
            double xOffset = 60;
            splittedMaterialPane.setMinHeight(100);

            Label label = cardsNumberFactory.generateCardNumber(material.getMaterialNames()[i]);

            int labelCounter = 0;
            for (int j = 0; j < material.getMaterialAmount()[i]; j++) {
                labelCounter += 1;
                ImageView temp = generateCardImageView(i);
                temp.setLayoutX(xOffset);
                temp.setLayoutY(yOffset);
                yOffset-=2;
                xOffset+=10;
                splittedMaterialPane.getChildren().add(temp);
            }
            label.setText(Integer.toString(labelCounter));
            labelCounter = 0;
            label.setLayoutX(0);
            label.setLayoutY(30);
            splittedMaterialPane.getChildren().add(label);
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
