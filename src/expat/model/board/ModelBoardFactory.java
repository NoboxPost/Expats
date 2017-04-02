package expat.model.board;

import expat.model.ModelRaider;
import expat.model.buildings.ModelBuilding;
import expat.model.buildings.ModelBuildingFactory;
import expat.model.buildings.ModelConnection;
import expat.model.buildings.ModelConnectionFactory;

import java.util.ArrayList;

/**
 * is responsible for the creation of a board.
 * <p>
 * created on 30.03.2017
 *
 * @author vanonir
 */
public class ModelBoardFactory {
    private ModelHex[][] hexes;
    private ArrayList<ModelBuilding> buildings;
    private ArrayList<ModelConnection> connections;
    private int xSize, ySize;
    private ModelBoard board;

    public ModelBoardFactory(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
    }

    /**
     * is responsible for the creation of a board
     * calls all the board part factories and unites them to a functioning game board
     *
     * board parts:
     * - hexes
     * - buildings
     * - raider (only one, that's why there is no factory)
     *
     * @return board
     */
    public ModelBoard generateBoard() {
        ModelHexFactory hexFactory = new ModelHexFactory();
        hexes = hexFactory.generateHexes(xSize,ySize);
        ModelBuildingFactory buildingFactory = new ModelBuildingFactory(xSize,ySize,hexes);
        buildings = buildingFactory.generateBuildings();
        ModelConnectionFactory connectionFactory = new ModelConnectionFactory(xSize,ySize,hexes);
        connections = connectionFactory.generateConnections();
        ModelRaider raider= new ModelRaider(); //TODO: Implement raider functionality
        board = new ModelBoard(hexes,buildings,connections,raider);
        return board;
    }
}

