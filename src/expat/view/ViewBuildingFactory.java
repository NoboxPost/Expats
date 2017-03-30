package expat.view;

import expat.model.board.ModelHex;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

/**
 * is responsible for
 * <p>
 * created on 30.03.2017
 *
 * @author vanonir
 */
@Deprecated
public class ViewBuildingFactory {
    private CoordinateCalculator coordinateCalculator;
    private int hexSize;

    public ViewBuildingFactory(int hexSize) {
        this.hexSize = hexSize;
        coordinateCalculator = new CoordinateCalculator(hexSize);
    }


    public List<ImageView> generateBuildings(ModelHex[][] hexes){
        List<ImageView> buildings= new ArrayList<ImageView>();
        int width = hexes.length;
        int height = hexes[0].length;
        for (int x = 0;x<width;x++) {
            for (int y = 0 ; y<height;y++) {
                if (true){
                    buildings = (generateEmptyBuildingsForOneHex(hexes[x][y],buildings));
                }
            }
        }
        return buildings;
    }
    public List<ImageView> generateEmptyBuildingsForOneHex(ModelHex hex,List<ImageView> buildings) {
        Double[] hexCoord = coordinateCalculator.calcCoords(hex);
        double hexCoordX = hexCoord[0];
        double hexCoordY = hexCoord[1];

        ImageView imageView, imageView1, imageView2;

        if (hexCoordX==0){
            imageView =generateEmptyBuilding();
            imageView.setLayoutX(hexCoordX);
            imageView.setLayoutY(hexCoordY+(hexSize*0.4));
            buildings.add(imageView);
        }
        imageView1 = generateEmptyBuilding();
        imageView1.setLayoutX(hexCoordX+(hexSize*0.25));
        imageView1.setLayoutY(hexCoordY);
        buildings.add(imageView1);

        imageView2 = generateEmptyBuilding();
        imageView2.setLayoutX(hexCoordX+(hexSize*0.75));
        imageView2.setLayoutY(hexCoordY);
        buildings.add(imageView2);

        return buildings;
    }
    public ImageView generateEmptyBuilding(){
        Image img= new Image("expat/img/Building_Empty.png");
        ImageView imageView = new ImageView(img);
        imageView.setX(-(hexSize*0.1));
        imageView.setY(-(hexSize*0.1));
        return imageView;
    }
}
