package expat.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Rino on 02.04.2017.
 */
public class ViewConnection extends ImageView {
    private int xConnectionCoord, yConnectionCoord;

    public ViewConnection(Image image, int xConnectionCoord, int yConnectionCoord) {
        super(image);
        this.xConnectionCoord = xConnectionCoord;
        this.yConnectionCoord = yConnectionCoord;
    }

    /**
     * @return
     */
    public int[] getConnectionCoords() {
        return new int[]{xConnectionCoord, yConnectionCoord};
    }
}
