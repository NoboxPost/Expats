package expat.view;

import expat.control.PaneBoardController;
import expat.model.buildings.ModelConnection;
import javafx.scene.Cursor;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by Rino on 02.04.2017.
 */
public class ViewConnectionFactory {
    private ViewCoordinateCalculator viewCoordinateCalculator;
    PaneBoardController paneBoardController;
    private int hexSize;

    public ViewConnectionFactory(int hexSize, PaneBoardController paneBoardController) {
        this.paneBoardController = paneBoardController;
        this.hexSize = hexSize;
        viewCoordinateCalculator = new ViewCoordinateCalculator(hexSize);
    }

    public ArrayList<ViewConnection> generateConnections(ArrayList<ModelConnection> modelConnections) {
        ArrayList<ViewConnection> viewConnections = new ArrayList<>();
        for (ModelConnection modelConnection : modelConnections) {
            int[] modelBuildingCoords = modelConnection.getCoords();
            if (!modelConnection.getOnWater()) {
                ViewConnection viewConnection = generateRoad(modelBuildingCoords[0], modelBuildingCoords[1]);
                Double[] coords = viewCoordinateCalculator.calcBuildingCoords(modelBuildingCoords);
                if (modelConnection.getOrientation().equals("up")) {
                    viewConnection.setRotate(-60);
                } else if (modelConnection.getOrientation().equals("down")) {
                    viewConnection.setRotate(60);
                }
                viewConnection.setLayoutX(coords[0]);
                viewConnection.setLayoutY(coords[1]);
                if (true){//modelConnection.getOwner()!=null) { //TODO: auskommentieren.
                    viewConnection.setEffect(generatePlayerColorEffect("yellow"));//modelConnection.getOwner().getColor()));
                }
                viewConnections.add(viewConnection);
            }

        }


        return viewConnections;
    }

    private ColorAdjust generatePlayerColorEffect(String color) {
        ColorAdjust colorAdjust = new ColorAdjust();
        switch (color.toLowerCase()) {
            case "green":
                colorAdjust.setHue(0.3);
                colorAdjust.setSaturation(0.5);
                colorAdjust.setBrightness(-0.3);
                break;
            case "red":
                colorAdjust.setHue(-0.23);
                colorAdjust.setSaturation(0.8);
                colorAdjust.setBrightness(-0.15);
                break;
            case "blue":
                colorAdjust.setHue(0.95);
                colorAdjust.setSaturation(0.8);
                colorAdjust.setBrightness(-0.3);
                break;
            case "orange":
                colorAdjust.setHue(-0.1);
                colorAdjust.setSaturation(0.9);
                colorAdjust.setBrightness(-0.1);
                break;
            case "yellow":
                colorAdjust.setHue(0.005);
                colorAdjust.setSaturation(1);
                colorAdjust.setBrightness(-0.1);
                break;
            default:

        }
        return colorAdjust;

    }


    private ViewConnection generateRoad(int xCoord, int yCoord) {
        Image img = new Image("expat/img/Connection.png");
        ViewConnection viewConnection = new ViewConnection(img, xCoord, yCoord);
        viewConnection.setCursor(Cursor.HAND);
        viewConnection.setX(-hexSize * 0.2);
        viewConnection.setY(-hexSize * 0.1);
        return viewConnection;
    }
}
