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

    /**
     * Constructor for ModelBoardFactory, takes width and height of board to be generated.
     *
     * @param xSize width of board, equals aswell max x coordinate -1 for hexes.
     * @param ySize height of board, equals aswell max y coordinate -1 for hexes.
     */
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
        buildings = buildingFactory.generateAllBuildings();
        ModelConnectionFactory connectionFactory = new ModelConnectionFactory(xSize,ySize,hexes);
        connections = connectionFactory.generateConnections();
        ModelRaider raider= new ModelRaider(hexes[4][3]); //TODO: Implement raider functionality
        board = new ModelBoard(hexes,buildings,connections,raider);
        return board;
    }
}

