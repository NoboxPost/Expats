package expat.view;

import expat.control.panes.PaneBoardController;
import expat.model.buildings.ModelBuilding;
import javafx.scene.Cursor;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * is responsible for
 * <p>
 * created on 30.03.2017
 *
 * @author vanonir
 */

public class ViewBuildingFactory {
    private ViewCoordinateCalculator viewCoordinateCalculator;
    private PaneBoardController paneBoardController;
    private int hexSize;

    public ViewBuildingFactory(int hexSize, PaneBoardController paneBoardController) {
        this.paneBoardController = paneBoardController;
        this.hexSize = hexSize;
        viewCoordinateCalculator = new ViewCoordinateCalculator(hexSize);
    }

    /**
     * Generates a ViewBuilding for each ModelBilding given in an ArrayList.
     * Returns an ArrayList containing,
     *
     * @param modelBuildings ModelBuilding list which leads to generation of ViewBuildings.
     * @return ViewBuilding list, containing all Nodes representing their Model counterpart.
     */
    public List<ViewBuilding> generateBuildings(ArrayList<ModelBuilding> modelBuildings) {

        List<ViewBuilding> viewBuildings = new ArrayList<>();

        for (ModelBuilding modelBuilding : modelBuildings) {
            int[] modelBuildingCoords = modelBuilding.getCoords();
            ViewBuilding viewBuilding;
            if (modelBuilding.getType().equals("Town")) {
                viewBuilding = generateTown(modelBuildingCoords[0], modelBuildingCoords[1]);
                addQualitiesToBuilding(viewBuilding, modelBuildingCoords, modelBuilding);
                viewBuildings.add(viewBuilding);
            } else if (modelBuilding.getType().equals("Settlement")) {
                viewBuilding = generateSettlement(modelBuildingCoords[0], modelBuildingCoords[1]);
                addQualitiesToBuilding(viewBuilding, modelBuildingCoords, modelBuilding);
                viewBuildings.add(viewBuilding);
            }
        }

        //
        return viewBuildings;
    }

    private void addQualitiesToBuilding(ViewBuilding viewBuilding, int[] modelBuildingCoords, ModelBuilding modelBuilding){
        viewBuilding.setEffect(generatePlayerColorEffectForSettlement(modelBuilding.getOwner().getColor()));
        Double[] pixelCoords = viewCoordinateCalculator.calcBuildingCoords(modelBuildingCoords);
        viewBuilding.setLayoutX(pixelCoords[0]);
        viewBuilding.setLayoutY(pixelCoords[1]);
    }

    public ViewBuilding generateBuildingPlacingSpot(int[] coords) {

        ViewBuilding viewBuilding;
        viewBuilding = generateEmptyBuilding(coords[0], coords[1]);
        Double[] pixelCoords = viewCoordinateCalculator.calcBuildingCoords(coords);
        viewBuilding.setOnMouseReleased(paneBoardController::buildingSpotClicked);
        viewBuilding.setLayoutX(pixelCoords[0]);
        viewBuilding.setLayoutY(pixelCoords[1]);

        return viewBuilding;
    }

    public List<ViewBuilding> generateBuildingPlacingSpots(ArrayList<ModelBuilding> modelBuildings) {

        List<ViewBuilding> viewBuildings = new ArrayList<>();

        for (ModelBuilding modelBuilding : modelBuildings) {
            int[] modelBuildingCoords = modelBuilding.getCoords();
            ViewBuilding viewBuilding;
            if (modelBuilding.getType().equals("empty")) {
                viewBuilding = generateEmptyBuilding(modelBuildingCoords[0], modelBuildingCoords[1]);
                viewBuilding.setOnMouseReleased(paneBoardController::buildingSpotClicked);
                Double[] pixelCoords = viewCoordinateCalculator.calcBuildingCoords(modelBuildingCoords);
                viewBuilding.setLayoutX(pixelCoords[0]);
                viewBuilding.setLayoutY(pixelCoords[1]);
                viewBuildings.add(viewBuilding);
            }
        }
        return viewBuildings;
    }


    /**
     * Generates a ViewBuilding, which extends ImageView, with ability to store the model coordinates so we can acquire it at click event.
     * Generates a empty building spot.
     *
     * @param xCoord takes ModelCoordinate and stores it in ViewBuilding, so event can get it.
     * @param yCoord takes ModelCoordinate and stores it in ViewBuilding, so event can get it.
     * @return ViewBuilding Node, which can be added to any Pane
     */
    public ViewBuilding generateEmptyBuilding(int xCoord, int yCoord) {
        Image img = new Image("expat/img/Building_Empty.png");
        ViewBuilding viewBuilding = new ViewBuilding(img, xCoord, yCoord);
        viewBuilding.setX(-(hexSize * 0.1));
        viewBuilding.setY(-(hexSize * 0.1));
        viewBuilding.setCursor(Cursor.HAND);

        return viewBuilding;
    }

    /**
     * Generates a ViewBuilding, which extends ImageView, with ability to store the model coordinates so we can acquire it at click event.
     * Generates a empty building spot.
     *
     * @param xCoord takes ModelCoordinate and stores it in ViewBuilding, so event can get it.
     * @param yCoord takes ModelCoordinate and stores it in ViewBuilding, so event can get it.
     * @return ViewBuilding Node, which can be added to any Pane
     */
    public ViewBuilding generateSettlement(int xCoord, int yCoord) {

        Image img = new Image("expat/img/BuildingUncolored.png");
        ViewBuilding viewBuilding = new ViewBuilding(img, xCoord, yCoord);
        viewBuilding.setX(-(hexSize * 0.1));
        viewBuilding.setY(-(hexSize * 0.1));

        return viewBuilding;


    }

    /**
     * Generates a ViewBuilding, which extends ImageView, with ability to store the model coordinates so we can acquire it at click event.
     * Generates a empty building spot.
     *
     * @param xCoord takes ModelCoordinate and stores it in ViewBuilding, so event can get it.
     * @param yCoord takes ModelCoordinate and stores it in ViewBuilding, so event can get it.
     * @return ViewBuilding Node, which can be added to any Pane
     */
    public ViewBuilding generateTown(int xCoord, int yCoord) {


        Image img = new Image("expat/img/TownUncolored.png");
        ViewBuilding viewBuilding = new ViewBuilding(img, xCoord, yCoord);
        viewBuilding.setX(-(hexSize * 0.1));
        viewBuilding.setY(-(hexSize * 0.1));

        return viewBuilding;


    }

    /**
     * @param color
     * @return
     */
    private ColorAdjust generatePlayerColorEffectForTown(String color) {
        ColorAdjust colorAdjust = new ColorAdjust();
        switch (color.toLowerCase()) {
            case "green":
                colorAdjust.setHue(0.3);
                colorAdjust.setSaturation(0.5);
                colorAdjust.setBrightness(-0.3);
                break;
            case "red":
                colorAdjust.setHue(-0.3);
                colorAdjust.setSaturation(0.8);
                colorAdjust.setBrightness(-0.15);
                break;
            case "blue":
                colorAdjust.setHue(0.95);
                colorAdjust.setSaturation(0.8);
                colorAdjust.setBrightness(-0.3);
                break;
            case "orange":
                colorAdjust.setHue(-0.15);
                colorAdjust.setSaturation(0.9);
                colorAdjust.setBrightness(-0.1);
                break;
            case "yellow":
                colorAdjust.setHue(-0.04);
                colorAdjust.setSaturation(1);
                colorAdjust.setBrightness(0.1);
                break;
            default:
                colorAdjust.setHue(0);
                colorAdjust.setSaturation(0);
                colorAdjust.setBrightness(0);
                break;

        }
        return colorAdjust;

    }

    /**
     * @param color
     * @return
     */
    private Effect generatePlayerColorEffectForSettlement(String color) {
        ColorAdjust colorAdjust = new ColorAdjust();
        switch (color.toLowerCase()) {
            case "green":
                colorAdjust.setHue(0.3);
                colorAdjust.setSaturation(0.5);
                colorAdjust.setBrightness(-0.3);
                break;
            case "red":
                colorAdjust.setHue(-0.3);
                colorAdjust.setSaturation(0.8);
                colorAdjust.setBrightness(-0.15);
                break;
            case "blue":
                colorAdjust.setHue(0.95);
                colorAdjust.setSaturation(0.8);
                colorAdjust.setBrightness(-0.3);
                break;
            case "orange":
                colorAdjust.setHue(-0.15);
                colorAdjust.setSaturation(1);
                colorAdjust.setBrightness(-0.3);
                break;
            case "yellow":
                colorAdjust.setHue(-0.025);
                colorAdjust.setSaturation(1);
                colorAdjust.setBrightness(0.1);
                break;
            default:
                colorAdjust.setHue(0);
                colorAdjust.setSaturation(0);
                colorAdjust.setBrightness(0);
                break;

        }
        return colorAdjust;
    }


}
