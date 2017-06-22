package expat.view;

import expat.control.panes.PaneBoardController;
import expat.model.ModelRaider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * is responsible for the generation of the raider figure on board
 * <p>
 * created on 02.04.2017
 *
 * @author vanonir
 */
public class ViewRaiderFactory {
    private ViewCoordinateCalculator viewCoordinateCalculator;
    PaneBoardController paneBoardController;
    private int hexSize;

    public ViewRaiderFactory(int hexSize, PaneBoardController paneBoardController) {
        this.paneBoardController = paneBoardController;
        this.hexSize = hexSize;
        viewCoordinateCalculator = new ViewCoordinateCalculator(hexSize);
    }

    /**
     * 1. takes the mdelraider (is in fact a hex that is raided)
     * 2. generates a pane and an imageview with the proper coordinates
     * of the specific hex that is raided
     *
     * @param modelRaider
     * @return raiderPane that includes the raiderImageView
     */
    public Pane generateRaider(ModelRaider modelRaider) {
        Double[] coords = viewCoordinateCalculator.calcRaiderCoords(modelRaider.getRaiderHex());
        Pane raiderPane = new Pane();
        ImageView raiderImageView = new ImageView("expat/img/Raider.png");
        raiderPane.setLayoutX(coords[0]);
        raiderPane.setLayoutY(coords[1]);
        raiderPane.setMaxHeight(70);
        raiderPane.setMaxWidth(50);
        raiderImageView.setX(-(hexSize * 0.1));
        raiderImageView.setY(-(hexSize * 0.2));
        raiderPane.getChildren().add(raiderImageView);

        return raiderPane;
    }
}
