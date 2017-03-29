package expat.view;

import expat.model.board.ModelHex;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vanonir on 29.03.2017.
 */
public class ViewHexFactory {
    private int hexSize;
    private CoordinateCalculator coordCalculator;
    String[] imageUrls = new String[]{"expat/img/Water.png", "expat/img/Wiese_Sheep.png",};
    Image[] images;
    ImagePattern[] imagePatterns;
    String[] types = new String[]{"Water", "Desert", "Clay", "Grain", "Stone", "Wood", "Wool"};

    /**
     * Generates all the ViewHexes, and has all the Information (IMAGES ,Types) to fill in the ViewHexes.
     * First generates all ImagePatterns according to URLs provided in the imageUrls parameter.
     */
    public ViewHexFactory(int hexSize) {
        this.hexSize = hexSize;
        coordCalculator = new CoordinateCalculator(hexSize);


        images = new Image[imageUrls.length];
        for (int i = 0; i < imageUrls.length; i++) {
            images[i] = new Image(imageUrls[i]);
        }
        imagePatterns = new ImagePattern[images.length];
        for (int i = 0; i < images.length; i++) {
            imagePatterns[i] = new ImagePattern(images[i], 0, 0, 1, 1, true);
        }
    }

    public ViewHex generateViewHex(ModelHex mhex) {
        int index = 0;
        for (int i = 0; i < types.length; i++) {
            if (types[i].equals(mhex.getType())) {
                index = i;
                break;
            }
        }
        Double[] coords = coordCalculator.calcCoords(mhex);
        ViewHex viewHex = new ViewHex(hexSize, coords[0], coords[1], imagePatterns[index]);
        return viewHex;
    }

    public List<ViewHex> generateViewHexList(ModelHex[][] hexes) {
        List<ViewHex> viewHexList = new ArrayList<ViewHex>();
        for (ModelHex[] hexline : hexes) {
            for (ModelHex hex : hexline) {
                viewHexList.add(generateViewHex(hex));
            }
        }


        return viewHexList;
    }
}
