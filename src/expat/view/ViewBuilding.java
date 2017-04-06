package expat.view;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * is responsible for
 * <p>
 * created on 31.03.2017
 *
 * @author vanonir
 */
public class ViewBuilding extends ImageView {
    private int xBuildingCoord, yBuildingCoord;

    public ViewBuilding(Image image, int xBuildingCoord, int yBuildingCoord) {
        super(image);
        this.xBuildingCoord = xBuildingCoord;
        this.yBuildingCoord = yBuildingCoord;
    }

    /**
     * @return
     */
    public int[] getBuildingCoord() {
        return new int[]{xBuildingCoord,yBuildingCoord};
    }
}
