package expat.view;

import expat.control.PaneBoardController;
import expat.model.buildings.ModelBuilding;
import javafx.scene.Cursor;
import javafx.scene.effect.ColorAdjust;
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
    PaneBoardController paneBoardController;
    private int hexSize;

    public ViewBuildingFactory(int hexSize, PaneBoardController paneBoardController) {
        this.paneBoardController = paneBoardController;
        this.hexSize = hexSize;
        viewCoordinateCalculator = new ViewCoordinateCalculator(hexSize);
    }

    public List<ViewBuilding> generateBuildings(ArrayList<ModelBuilding> modelBuildings) {
        List<ViewBuilding> viewBuildings = new ArrayList<ViewBuilding>();

        for (ModelBuilding modelBuilding : modelBuildings) {
            int[] modelBuildingCoords = modelBuilding.getCoords();
            ViewBuilding viewBuilding;
            if (modelBuilding.getType().equals("Town")) {
                viewBuilding = generateEmptyBuilding(modelBuildingCoords[0], modelBuildingCoords[1]);

            } else if (modelBuilding.getType().equals("Settlement")) {

                viewBuilding = generateSettlement(modelBuildingCoords[0], modelBuildingCoords[1]);
            } else {
                viewBuilding = generateEmptyBuilding(modelBuildingCoords[0], modelBuildingCoords[1]);
            }
            viewBuilding.setOnMouseReleased(paneBoardController::emptyBuildingSpotClicked);
            Double[] pixelCoords = viewCoordinateCalculator.calcBuildingCoords(modelBuildingCoords);
            viewBuilding.setLayoutX(pixelCoords[0]);
            viewBuilding.setLayoutY(pixelCoords[1]);
            viewBuildings.add(viewBuilding);
            //viewBuilding.setEffect(); TODO: set color depending on owner
        }


        return viewBuildings;
    }

    public ViewBuilding generateEmptyBuilding(int xCoord, int yCoord) {
        Image img = new Image("expat/img/Building_Empty.png");
        ViewBuilding viewBuilding = new ViewBuilding(img, xCoord, yCoord);
        viewBuilding.setX(-(hexSize * 0.1));
        viewBuilding.setY(-(hexSize * 0.1));
        viewBuilding.setCursor(Cursor.HAND);

        return viewBuilding;
    }

    public ViewBuilding generateSettlement(int xCoord, int yCoord) {

        Image img = new Image("expat/img/Settlement.png");
        ViewBuilding viewBuilding = new ViewBuilding(img, xCoord, yCoord);
        viewBuilding.setX(-(hexSize * 0.1));
        viewBuilding.setY(-(hexSize * 0.1));
        viewBuilding.setCursor(Cursor.HAND);

        return viewBuilding;


    }


}


//    public List<ImageView> generateBuildings(ModelHex[][] hexes){
//        List<ImageView> buildings= new ArrayList<ImageView>();
//        int width = hexes.length;
//        int height = hexes[0].length;
//        for (int x = 0;x<width;x++) {
//            for (int y = 0 ; y<height;y++) {
//                if (true){
//                    buildings = (generateEmptyBuildingsForOneHex(hexes[x][y],buildings));
//                }
//            }
//        }
//        return buildings;
//    }
//    public List<ImageView> generateEmptyBuildingsForOneHex(ModelHex hex,List<ImageView> buildings) {
//        Double[] hexCoord = viewCoordinateCalculator.calcHexCoords(hex);
//        double hexCoordX = hexCoord[0];
//        double hexCoordY = hexCoord[1];
//
//        ImageView imageView, imageView1, imageView2;
//
//        if (hexCoordX==0){
//            imageView =generateEmptyBuilding();
//            imageView.setLayoutX(hexCoordX);
//            imageView.setLayoutY(hexCoordY+(hexSize*0.4));
//            buildings.add(imageView);
//        }
//        imageView1 = generateEmptyBuilding();
//        imageView1.setLayoutX(hexCoordX+(hexSize*0.25));
//        imageView1.setLayoutY(hexCoordY);
//        buildings.add(imageView1);
//
//        imageView2 = generateEmptyBuilding();
//        imageView2.setLayoutX(hexCoordX+(hexSize*0.75));
//        imageView2.setLayoutY(hexCoordY);
//        buildings.add(imageView2);
//
//        return buildings;
//    }
//    public ImageView generateEmptyBuilding(){
//        Image img= new Image("expat/img/Building_Empty.png");
//        ImageView imageView = new ImageView(img);
//        imageView.setX(-(hexSize*0.1));
//        imageView.setY(-(hexSize*0.1));
//        return imageView;
//    }
//}
