package expat.view;

import expat.control.panes.PaneActionController;
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
public class ViewPaneTradeGeneral extends HBox {
    private PaneActionController paneActionController;
    private String type = "GeneralTrade";
    private String[] materialTypes = new String[]{"Lehm", "Stroh", "Stein", "Holz", "Wolle"};
    private int[] materialAtStartArray = new int[5];
    private int[] materialAtEdnArray;
    private Label[] lblsmaterial = new Label[5];
    private Button[] btnsMaterialPlus = new Button[5];
    private Button[] btnsMaterialMinus = new Button[5];
    private int materialSumPlus = 0;
    private int materialSumMinus = 0;
    private Button btnTrade = new Button("Handeln");
    private Label lblMaterialSumDemanded = new Label("" + materialSumPlus);
    private Label lblMaterialSumOffered = new Label("" + materialSumMinus);


    public ViewPaneTradeGeneral(int[] materialAtStartArray, PaneActionController paneActionController) {
        this.materialAtStartArray = materialAtStartArray;
        materialAtEdnArray = materialAtStartArray.clone();
        this.paneActionController = paneActionController;
        this.setSpacing(20);
        generateFirstTimeContent();
        btnTrade.setDisable(true);
        //btnTrade.setOnAction(this.paneActionController::btnTradeFinishClicked);

        //TODO: handle this
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
