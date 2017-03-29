package expat.view;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * Created by vanonir on 29.03.2017.
 */
public class ViewHexFactory {
    String[] imageUrls = new String[]{"expat/img/Wiese_Sheep.png",};
    Image[] images;
    ImagePattern[] imagePatterns;
    String[] types = new String[]{"Water", "Desert", "Clay", "Grain", "Stone", "Wood", "Wool"};

    /**
     * Generates all the ViewHexes, and has all the Information (IMAGES ,Types) to fill in the ViewHexes.
     * First generates all ImagePatterns according to URLs provided in the imageUrls parameter.
     */
    public ViewHexFactory() {

        images = new Image[imageUrls.length];
        for (int i = 0; i < imageUrls.length; i++) {
            images[i] = new Image(imageUrls[i]);
        }
        imagePatterns = new ImagePattern[images.length];
        for (int i = 0; i < images.length; i++) {
            imagePatterns[i] = new ImagePattern(images[i], 0, 0, 1, 1, true);
        }
    }

    public ViewHex generateViewHex(){

        return null;
    }
}
