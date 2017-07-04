package expat.view;

import expat.control.panes.PaneActionController;
import expat.model.ModelMaterial;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * is responsible for
 * <p>
 * created on 05.04.2017
 *
 * @author vanonir
 */
public class ViewPaneDropMaterial extends HBox {
    private PaneActionController paneActionController;
    private int[] materialAtStartArray = new int[5];
    private int[] materialAtEndArray;
    private Label[] lblsMaterial = new Label[5];
    private Button[] btnsMaterialPlus = new Button[5];
    private Button[] btnsMaterialMinus = new Button[5];
    private int amountToBeDropped = 0;
    private int materialSumMinus = 0;
    private final Button btnDrop = new Button("drop materials and finish step");
    private Label lblAmountToBeDropped = new Label("");
    private Label lblMaterialSumOffered = new Label("" + materialSumMinus);

    public ViewPaneDropMaterial(PaneActionController paneActionController, int amountToBeDropped, int[] playerMaterialAmount) {
        this.setSpacing(20);
        this.paneActionController = paneActionController;
        this.amountToBeDropped = amountToBeDropped;
        generateCalculationArrays(playerMaterialAmount);
        generateContent();
        lblAmountToBeDropped.setText("to drop: " + amountToBeDropped);
        btnDrop.setCursor(Cursor.HAND);
        btnDrop.setDisable(true);
        btnDrop.setOnAction(this.paneActionController::btnDropMaterialFinishClicked);
    }

    private void generateCalculationArrays(int[] playerMaterialAmount){
        materialAtStartArray = playerMaterialAmount;
        materialAtEndArray = materialAtStartArray.clone();
    }


    /**
     *
     */
    private void generateContent() {
        this.getChildren().clear();
        generateDroppingCardItems(this);
        generateDroppingFinishItems(this);
    }

    private void generateDroppingCardItems(ViewPaneDropMaterial viewPaneDropMaterial){
        ViewCardsFactory cardsFactory = new ViewCardsFactory(new ModelMaterial());
        for (int i = 0; i < 5; i++) {
            ImageView materialImageView = cardsFactory.generateCardImageView(i);
            viewPaneDropMaterial.getChildren().add(materialImageView);
            btnsMaterialPlus[i] = new Button("+");
            btnsMaterialPlus[i].setCursor(Cursor.HAND);
            btnsMaterialPlus[i].setOnAction(this::btnAdjustedDropMaterialAmountClicked);
            lblsMaterial[i] = new Label("" + materialAtEndArray[i]);
            btnsMaterialMinus[i] = new Button("-");
            btnsMaterialMinus[i].setCursor(Cursor.HAND);
            btnsMaterialMinus[i].setOnAction(this::btnAdjustedDropMaterialAmountClicked);

            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.getChildren().addAll(btnsMaterialPlus[i], lblsMaterial[i], btnsMaterialMinus[i]);
            viewPaneDropMaterial.getChildren().add(vBox);
        }
    }

    private void generateDroppingFinishItems(ViewPaneDropMaterial viewPaneDropMaterial){
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(lblAmountToBeDropped, btnDrop, lblMaterialSumOffered);
        viewPaneDropMaterial.getChildren().add(vBox);
    }

    private void btnAdjustedDropMaterialAmountClicked(ActionEvent actionEvent) {
        adjustMaterial((Button) actionEvent.getSource());
    }

    /**
     * @param btnclicked
     */
    public void adjustMaterial(Button btnclicked) {
        for (int i = 0; i < 5; i++) {
            if (btnclicked == btnsMaterialPlus[i] && materialAtEndArray[i] +1 <= materialAtStartArray[i]) {
                materialAtEndArray[i] += 1;
            } else if (btnclicked == btnsMaterialMinus[i] && materialAtEndArray[i] - 1 >= 0) {
                materialAtEndArray[i] -= 1;
            }
            materialSumMinus = 0;
        }
        for (int i = 0; i < 5; i++) {
            int temp = materialAtEndArray[i] - materialAtStartArray[i];
            if (temp < 0) {
                materialSumMinus += temp;
            }
        }
        refresh();
    }

    /**
     *
     */
    public void refresh() {
        lblMaterialSumOffered.setText("yet dropped: " + materialSumMinus);
        if (amountToBeDropped + materialSumMinus == 0) {
            generateContent();
            btnDrop.setDisable(false);
        } else {
            generateContent();
            btnDrop.setDisable(true);
        }
    }

    /**
     * @return
     */
    public int[] getDroppingDifference() {
        int[] difference = new int[5];
        for (int i = 0; i < 5; i++) {
            difference[i] = materialAtEndArray[i] - materialAtStartArray[i];
        }
        return difference;
    }
}
