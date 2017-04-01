package expat.model.buildings;

import expat.model.ModelCoordinateCalculator;
import expat.model.board.ModelHex;

import java.util.ArrayList;

/**
 * is responsible for
 * <p>
 * created on 30.03.2017
 *
 * @author vanonir
 */
public class ModelConnectionGenerator {

    private ModelHex[][] hexes;
    private int xHexSize, yHexSize;
    private ArrayList<ModelBuilding> modelBuildings = new ArrayList<>();
    private ModelCoordinateCalculator coordinateCalculator;


    public ModelConnectionGenerator(int xSize, int ySize, ModelHex[][] hexes) {
        this.hexes = hexes;
        this.xHexSize = xSize;
        this.yHexSize = ySize;
        coordinateCalculator = new ModelCoordinateCalculator(xSize, ySize);
    }


    public void generateConnectionsForAllHexes(){
        if (hexes != null && xHexSize != 0 && yHexSize != 0) {
            for (int x = 0; x < xHexSize; x++) {
                for (int y = 0; y < yHexSize ; y++) {
                    if (!hexes[x][y].getType().equals("Water")) {
                        boolean[] spotsForConnections = checkPositionsToBuildOn(xHexSize,yHexSize,x,y);
                    }
                }
            }
        }


    }

    private boolean[] checkPositionsToBuildOn(int xHexSize, int yHexSize, int xCurrent, int yCurrent) {
        boolean[] spotsForConnections  = new boolean[]{true,true,true,false,false,false};
        if (xCurrent==0){
            spotsForConnections[5]=true;
        }
        if (xCurrent==xHexSize-1){
            spotsForConnections[3]=true;
        }
        if (yCurrent==yHexSize-1){
                spotsForConnections[4]=true;
            if (yCurrent%2==1){
                spotsForConnections[3]=true;
                spotsForConnections[5]=true;
            }
        }
        return spotsForConnections;
    }
}
