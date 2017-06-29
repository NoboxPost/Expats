package expat.view;

import expat.model.board.ModelHex;

import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * generates all the ViewHexes from images according to the specific hex-type
 * provides the design of the hexagonal fields on the board
 */
public class ViewHexGenerator {
    private int hexSize;
    private ViewCoordinateCalculator coordCalculator;
    private ImagePattern[] imagePatterns;
    private LinkedList<String> hexTypes = new LinkedList<>(Arrays.asList("Water", "Desert", "Clay", "Grain", "Stone", "Wood", "Wool"));

    /**
     * @param hexSize in pixel is used as template size for all displayed hexes and buildings (for scaling)
     */
    public ViewHexGenerator(int hexSize) {
        this.hexSize = hexSize;
        coordCalculator = new ViewCoordinateCalculator(hexSize);
        assignHexImagesToImagePatterns();
    }

    /**
     * creates an image per image URL and adds that image to an ImagePattern
     */
    private void assignHexImagesToImagePatterns(){
        String[] imageUrls = new String[]{
                "expat/img/Water.png",
                "expat/img/Desert.png",
                "expat/img/Clay.png",
                "expat/img/Grain.png",
                "expat/img/Stone.png",
                "expat/img/Wood.png",
                "expat/img/Wool.png",};

        Image[] images = new Image[imageUrls.length];

        for (int i = 0; i < imageUrls.length; i++) {
            images[i] = new Image(imageUrls[i]);
        }

        imagePatterns = new ImagePattern[images.length];
        for (int i = 0; i < images.length; i++) {
            imagePatterns[i] = new ImagePattern(images[i], 0, 0, 1, 1, true);
        }

    }

    /**
     * generates the ViewHex, a subclass of javafx.scene.shape.polygon, and returns it
     *
     * @param modelHex is the modelHex which contains: type & position of the hex
     * @return a javafx.scene.shape.polygon which can be added as a child to any pane
     */
    private ViewHex generateViewHex(ModelHex modelHex) {
        String modelHexType = modelHex.getType();
        Double[] coords = coordCalculator.calcHexCoords(modelHex);
        int imagePatternsIndex = hexTypes.indexOf(modelHexType);

        ViewHex viewHex = new ViewHex(hexSize, coords[0], coords[1], imagePatterns[imagePatternsIndex]);

        //adds a shadow to all rural hexes
        viewHex.setEffect(addShadowToHex(modelHexType));


        return viewHex;
    }

    /**
     * returns a shadow that is needed in generateViewHex
     *
     * @return the shadow for rural viewHexes
     */
    private InnerShadow addShadowToHex(String modelHexType) {
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setHeight(10);
        innerShadow.setWidth(10);
        innerShadow.setRadius(30);

        //adds different color for rural hexes and water-hexes
        if (modelHexType.equals("Water")){
            innerShadow.setColor(Color.web("#a3baff"));
            innerShadow.setChoke(0.1);
            innerShadow.setRadius(50);
        }
        else{
            innerShadow.setColor(Color.web("#ffe485"));
            innerShadow.setChoke(0.2);
            innerShadow.setRadius(15);
        }

        return innerShadow;



    }


    /**
     * creates an ArrayList of Type ViewHex, which can be added to any Pane with the addAll method.
     * @param hexes is a 2 dimensional ModelHex Array. Dimensions corespond to Arrays as folows: ModelHex[xCoordinate][yCoordinate].
     * @return returns an ArrayList<ViewHex> which can be added with the addAll tag to any pane.
     */
    public List<ViewHex> generateViewHexList(ModelHex[][] hexes) {
        List<ViewHex> viewHexList = new ArrayList<>();
        for (ModelHex[] hexLine : hexes) {
            for (ModelHex hex : hexLine) {
                viewHexList.add(generateViewHex(hex));
            }
        }
        return viewHexList;
    }

}
