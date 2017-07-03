package expat.view;

import expat.control.panes.PaneActionController;
import expat.model.ModelMaterial;
import javafx.event.ActionEvent;
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
public class ViewPaneTradeCommon extends HBox {
    private PaneActionController paneActionController;
    private int[] materialAtStartArray = new int[5];
    private int[] materialAtEndArray;
    private Label[] lblsMaterial = new Label[5];
    private Button[] btnsMaterialPlus = new Button[5];
    private Button[] btnsMaterialMinus = new Button[5];
    private int materialSumPlus = 0;
    private int materialSumMinus = 0;
    private Button btnTrade = new Button("trade!");
    private Label lblMaterialSumDemanded = new Label("" + materialSumPlus);
    private Label lblMaterialSumOffered = new Label("" + materialSumMinus);


    public ViewPaneTradeCommon(PaneActionController paneActionController, int[] playerMaterialAmount) {
        this.setSpacing(20);
        this.paneActionController = paneActionController;
        generateCalculationArrays(playerMaterialAmount);
        generateContent();
        btnTrade.setDisable(true);
        btnTrade.setOnAction(this.paneActionController::btnTradeFinishClicked);
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


    private void generateDroppingCardItems(ViewPaneTradeCommon viewPaneTradeCommon){
        ViewCardsFactory cardsFactory = new ViewCardsFactory(new ModelMaterial());
        for (int i = 0; i < 5; i++) {
            ImageView materialImageView = cardsFactory.generateCardImageView(i);
            viewPaneTradeCommon.getChildren().add(materialImageView);
            btnsMaterialPlus[i] = new Button("+");
            btnsMaterialPlus[i].setOnAction(this::btnAdjustedDropMaterialAmountClicked);
            lblsMaterial[i] = new Label("" + materialAtEndArray[i]);
            btnsMaterialMinus[i] = new Button("-");
            btnsMaterialMinus[i].setOnAction(this::btnAdjustedDropMaterialAmountClicked);

            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.getChildren().addAll(btnsMaterialPlus[i], lblsMaterial[i], btnsMaterialMinus[i]);
            viewPaneTradeCommon.getChildren().add(vBox);
        }
    }

    private void generateDroppingFinishItems(ViewPaneTradeCommon viewPaneTradeCommon){
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(lblMaterialSumDemanded, btnTrade, lblMaterialSumOffered);
        viewPaneTradeCommon.getChildren().add(vBox);
    }

    private void btnAdjustedDropMaterialAmountClicked(ActionEvent actionEvent) {
        adjustMaterial((Button) actionEvent.getSource());
    }

    /**
     * @param btnclicked
     */
    public void adjustMaterial(Button btnclicked) {
        for (int i = 0; i < 5; i++) {
            if (btnclicked == btnsMaterialPlus[i]) {
                materialAtEndArray[i] += 1;
            } else if (btnclicked == btnsMaterialMinus[i] && materialAtEndArray[i]-1>=0) {
                materialAtEndArray[i] -= 1;
            }
            materialSumPlus = 0;
            materialSumMinus = 0;

        }
        for (int i = 0; i < 5; i++) {
            int temp = materialAtEndArray[i] - materialAtStartArray[i];
            if (temp > 0){
                materialSumPlus += temp;
            }else if (temp < 0){
                materialSumMinus += temp;
            }
        }
        refresh();
    }

    /**
     *
     */
    public void refresh() {
        for (int i = 0; i < 5; i++) {
            lblsMaterial[i].setText("" + materialAtEndArray[i]);
        }
        lblMaterialSumDemanded.setText("" + materialSumPlus);
        lblMaterialSumOffered.setText("" + materialSumMinus);
        if ((materialSumPlus * 4 + materialSumMinus) == 0){
            btnTrade.setDisable(false);
        }else {
            btnTrade.setDisable(true);
        }
    }

    /**
     * @return
     */
    public int[] getTradingDifference() {
        int[] difference = new int[5];
        for (int i = 0; i < 5; i++) {
            difference[i] = materialAtEndArray[i]-materialAtStartArray[i];
        }
        return difference;
    }
}
