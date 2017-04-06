package expat.view;

import expat.control.PaneActionController;
import expat.model.ModelMaterial;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * is responsible for
 * <p>
 * created on 05.04.2017
 *
 * @author vanonir
 */
public class ViewPaneTradeGeneral extends HBox {
    PaneActionController paneActionController;
    String type = "GeneralTrade";
    String[] materialTypes = new String[]{"Lehm", "Stroh", "Stein", "Holz", "Wolle"};
    int[] materialAtStartArray = new int[5];
    int[] materialAtEdnArray;
    Label[] lblsmaterial = new Label[5];
    Button[] btnsMaterialPlus = new Button[5];
    Button[] btnsMaterialMinus = new Button[5];
    int materialSumPlus = 0;
    int materialSumMinus = 0;
    Button btnTrade = new Button("Handeln");
    Label lblMaterialSumDemanded = new Label("" + materialSumPlus);
    Label lblMaterialSumOffered = new Label("" + materialSumMinus);


    public ViewPaneTradeGeneral(int[] materialAtStartArray, PaneActionController paneActionController) {
        this.materialAtStartArray = materialAtStartArray;
        materialAtEdnArray = materialAtStartArray.clone();
        this.paneActionController = paneActionController;
        this.setSpacing(20);
        generateFirstTimeContent();
        btnTrade.setDisable(true);
        btnTrade.setOnAction(this.paneActionController::btnTradeFinishClicked);
    }


    /**
     *
     */
    public void generateFirstTimeContent() {
        this.getChildren().clear();
        ViewCardsFactory cardsFactory = new ViewCardsFactory(new ModelMaterial());
        for (int i = 0; i < 5; i++) {
            ImageView materialImageView = cardsFactory.generateCardImageView(i);
            this.getChildren().add(materialImageView);
            btnsMaterialPlus[i] = new Button("+");
            btnsMaterialPlus[i].setOnAction(paneActionController::btnTradeAdjustMaterialClicked);
            lblsmaterial[i] = new Label(materialTypes[i] + " " + materialAtEdnArray[i]);
            btnsMaterialMinus[i] = new Button("-");
            btnsMaterialMinus[i].setOnAction(paneActionController::btnTradeAdjustMaterialClicked);

            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.getChildren().addAll(btnsMaterialPlus[i], lblsmaterial[i], btnsMaterialMinus[i]);
            this.getChildren().add(vBox);
        }
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(lblMaterialSumDemanded,btnTrade,lblMaterialSumOffered);
        this.getChildren().add(vBox);
    }

    /**
     * @param btnclicked
     */
    public void adjustMaterial(Button btnclicked) {
        for (int i = 0; i < 5; i++) {
            if (btnclicked == btnsMaterialPlus[i]) {
                materialAtEdnArray[i] += 1;
            } else if (btnclicked == btnsMaterialMinus[i]&&materialAtEdnArray[i]-1>=0) {
                materialAtEdnArray[i] -= 1;
            }
            materialSumPlus = 0;
            materialSumMinus = 0;

        }
        for (int i = 0; i < 5; i++) {
            int temp = materialAtEdnArray[i]-materialAtStartArray[i];
            if (temp>0){
                materialSumPlus+=temp;
            }else if (temp<0){
                materialSumMinus+=temp;
            }
        }
        refresh();
    }

    /**
     *
     */
    public void refresh() {
        for (int i = 0; i < 5; i++) {
            lblsmaterial[i].setText(materialTypes[i] + " " + materialAtEdnArray[i]);
        }
        lblMaterialSumDemanded.setText(""+materialSumPlus);
        lblMaterialSumOffered.setText(""+materialSumMinus);
        if ((materialSumPlus*4+materialSumMinus)==0){
            btnTrade.setDisable(false);
        }else {
            btnTrade.setDisable(true);
        }
    }

    /**
     * @return
     */
    public int[] getEndDifference(){
        int[] difference = new int[5];
        for (int i = 0; i < 5; i++) {
            difference[i] = materialAtEdnArray[i]-materialAtStartArray[i];
        }
        return difference;
    }
}
