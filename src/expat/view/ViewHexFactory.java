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
    private ViewCoordinateCalculator coordCalculator;
    String[] imageUrls = new String[]{"expat/img/Water.png","expat/img/Desert.png","expat/img/Clay.png","expat/img/Grain.png","expat/img/Stone.png","expat/img/Wood.png", "expat/img/Wool.png",};
    Image[] images;
    ImagePattern[] imagePatterns;
    String[] types = new String[]{"Water", "Desert", "Clay", "Grain", "Stone", "Wood", "Wool"};

    /**
     * Generates all the ViewHexes, and has all the Information (IMAGES ,Types) to fill in the ViewHexes.
     * First generates all ImagePatterns according to URLs provided in the imageUrls parameter.
     * @param hexSize in pixel is used as template size for all displayed Hexes and Buildings
     */
    public ViewHexFactory(int hexSize) {
        this.hexSize = hexSize;
        coordCalculator = new ViewCoordinateCalculator(hexSize);


        images = new Image[imageUrls.length];
        for (int i = 0; i < imageUrls.length; i++) {
            images[i] = new Image(imageUrls[i]);
        }
        imagePatterns = new ImagePattern[images.length];
        for (int i = 0; i < images.length; i++) {
            imagePatterns[i] = new ImagePattern(images[i], 0, 0, 1, 1, true);
        }
    }

    /**
     * Generates a ViewHex which is a subclass of javafx.scene.shape.polygon and returns it, it can be added as a child.
     * @param modelHex is the modelHex which contains the type of the hex, aswell as its Position.
     * @return returns a javafx.scene.shape.polygon which can be added as a child to any pane.
     */
    public ViewHex generateViewHex(ModelHex modelHex) {
        int index = 0;
        for (int i = 0; i < types.length; i++) {
            if (types[i].equals(modelHex.getType())) {
                index = i;
                break;
            }
        }
        Double[] coords = coordCalculator.calcHexCoords(modelHex);
        ViewHex viewHex = new ViewHex(hexSize, coords[0], coords[1], imagePatterns[index]);
        return viewHex;
    }

    /**
     * Generates an ArrayList of Type ViewHex, which can be added to any Pane with the addAll method.
     * @param hexes is a 2 dimensional ModelHex Array. Dimensions corespond to Arrays as folows: ModelHex[xCoordinate][yCoordinate].
     * @return returns an ArrayList<ViewHex> which can be added with the addAll tag to any pane.
     */
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
