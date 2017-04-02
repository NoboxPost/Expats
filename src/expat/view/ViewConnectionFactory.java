package expat.view;

import expat.control.PaneBoardController;
import expat.model.buildings.ModelConnection;
import javafx.scene.Cursor;
import javafx.scene.image.Image;

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
                ViewConnection viewConnection = generateRoad(modelBuildingCoords[0],modelBuildingCoords[1]);
                Double[] coords = viewCoordinateCalculator.calcBuildingCoords(modelBuildingCoords);
                if (modelConnection.getOrientation().equals("up")){
                    viewConnection.setRotate(-60);
                }else if (modelConnection.getOrientation().equals("down")){
                    viewConnection.setRotate(60);
                }
                viewConnection.setLayoutX(coords[0]);
                viewConnection.setLayoutY(coords[1]);
                viewConnections.add(viewConnection);
            }

        }


        return viewConnections;
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
