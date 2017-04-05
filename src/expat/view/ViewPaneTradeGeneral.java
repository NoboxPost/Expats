package expat.view;

import javafx.scene.layout.Pane;

/**
 * is responsible for
 * <p>
 * created on 05.04.2017
 *
 * @author vanonir
 */
public class ViewPaneTradeGeneral extends Pane {
    String type = "GeneralTrade";
    int[] materialAtStartArray = new int[5];
    int[] materialAtEdnArray = new int[5];

    public ViewPaneTradeGeneral(int[] materialAtStartArray) {
        this.materialAtStartArray = materialAtStartArray;
    }

}
