package expat.view;

import expat.control.PaneActionController;
import expat.model.ModelMaterial;
import javafx.geometry.Pos;
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
    PaneActionController paneActionController;
    String type = "GeneralTrade";
    String[] materialTypes = new String[]{"Lehm", "Stroh", "Stein", "Holz", "Wolle"};
    int[] materialAtStartArray = new int[5];
    int[] materialAtEdnArray;
    Label[] lblsmaterial = new Label[5];
    Button[] btnsMaterialPlus = new Button[5];
    Button[] btnsMaterialMinus = new Button[5];
    int amountToBeDropped = 0;
    int materialSumMinus = 0;
    Button btnTrade = new Button("Abgeben");
    Label lblAmountToBeDropped = new Label("");
    Label lblMaterialSumOffered = new Label("" + materialSumMinus);
    Label lblPlayerName = new Label("");
    private boolean isDone= false;


    public ViewPaneDropMaterial(int[] materialAtStartArray, PaneActionController paneActionController,String name) {
        this.materialAtStartArray = materialAtStartArray;
        materialAtEdnArray = materialAtStartArray.clone();
        this.paneActionController = paneActionController;
        lblPlayerName.setText(name);
        this.setSpacing(20);
        for (int i = 0; i < 5; i++) {
            amountToBeDropped+=materialAtStartArray[i];
        }
        amountToBeDropped/=2;
        lblAmountToBeDropped.setText("Müssen abgegeben werden: "+amountToBeDropped);
        generateFirstTimeContent();
        btnTrade.setDisable(true);
        btnTrade.setOnAction(this.paneActionController::btnDropMaterialFinishClicked);
    }


    public void generateFirstTimeContent() {
        this.getChildren().clear();
        this.getChildren().add(lblPlayerName);
        ViewCardsFactory cardsFactory = new ViewCardsFactory(new ModelMaterial());
        for (int i = 0; i < 5; i++) {
            ImageView materialImageView = cardsFactory.generateCardImageView(i);
            this.getChildren().add(materialImageView);
            btnsMaterialPlus[i] = new Button("+");
            btnsMaterialPlus[i].setOnAction(paneActionController::btnAdjustedDropMaterialAmountClicked);
            lblsmaterial[i] = new Label(materialTypes[i] + " " + materialAtEdnArray[i]);
            btnsMaterialMinus[i] = new Button("-");
            btnsMaterialMinus[i].setOnAction(paneActionController::btnAdjustedDropMaterialAmountClicked);

            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.getChildren().addAll(btnsMaterialPlus[i], lblsmaterial[i], btnsMaterialMinus[i]);
            this.getChildren().add(vBox);
        }
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(lblAmountToBeDropped, btnTrade, lblMaterialSumOffered);
        this.getChildren().add(vBox);
    }

    public void adjustMaterial(Button btnclicked) {
        for (int i = 0; i < 5; i++) {
            if (btnclicked == btnsMaterialPlus[i] &&materialAtEdnArray[i]<=materialAtStartArray[i]) {
                materialAtEdnArray[i] += 1;
            } else if (btnclicked == btnsMaterialMinus[i] && materialAtEdnArray[i] - 1 >= 0) {
                materialAtEdnArray[i] -= 1;
            }
            materialSumMinus = 0;

        }
        for (int i = 0; i < 5; i++) {
            int temp = materialAtEdnArray[i] - materialAtStartArray[i];
            if (temp < 0) {
                materialSumMinus += temp;
            }
        }
        refresh();
    }

    public void refresh() {
        for (int i = 0; i < 5; i++) {
            lblsmaterial[i].setText(materialTypes[i] + " " + materialAtEdnArray[i]);
        }

        lblMaterialSumOffered.setText("" + materialSumMinus);
        if (amountToBeDropped + materialSumMinus==0) {
            btnTrade.setDisable(false);
        } else {
            btnTrade.setDisable(true);
        }
    }

    public int[] getEndDifference() {
        int[] difference = new int[5];
        for (int i = 0; i < 5; i++) {
            difference[i] = materialAtEdnArray[i] - materialAtStartArray[i];
        }
        return difference;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }
}
