package expat.model.board;

/**
 * is responsible for the creation of a hexagonal field
 * <p>
 * created on 22.03.2017
 *
 * @author vanonir
 */
public class ModelHexFactory {

    private boolean standardHexLayout = true;
    private boolean standardDiceDistribution = true;
    private int xSize, ySize;
    private ModelHex[][] hexes;

    /**
     * Constructor for hex Factory, if default layout of hexes is choosen atleas a width of 9 and height of 7 is required.
     *
     * @param xSize width of board, corresponds to hex which will be generated side by side.
     * @param ySize height of board, correspond to hex which will be generated underneath each other.
     * @return
     */
    public ModelHex[][] generateHexes(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        hexes = new ModelHex[xSize][ySize];
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                hexes[x][y] = new ModelHex(x, y);

            }
        }


        if (standardDiceDistribution && standardHexLayout) {
            generateHexesBasicLayout();
        } else if (standardHexLayout && !standardDiceDistribution) {
            generateBasicLayoutRandomDiceNumbers();
        } else {
            generateHexesBasicLayout();
        }
        //TODO:Implement random deployment of hexes and DiceNumbers

        //hexes[7][5].assignTypeAndDiceNumber("Stone",8); //Adds an additional Hex, just to show off.

        return hexes;
    }

    /**
     * changes hexes and dice numbers according to basic game layout.
     */
    private void generateHexesBasicLayout() {

        hexes[3][1].assignTypeAndDiceNumber("Clay", 2);
        hexes[4][1].assignTypeAndDiceNumber("Grain", 4);
        hexes[5][1].assignTypeAndDiceNumber("Wood", 6);
        hexes[2][2].assignTypeAndDiceNumber("Wool", 9);
        hexes[3][2].assignTypeAndDiceNumber("Clay", 8);
        hexes[4][2].assignTypeAndDiceNumber("Wood", 5);
        hexes[5][2].assignTypeAndDiceNumber("Wool", 12);
        hexes[6][2].assignTypeAndDiceNumber("Grain", 9);
        hexes[2][3].assignTypeAndDiceNumber("Wood", 3);
        hexes[3][3].assignTypeAndDiceNumber("Stone", 5);
        hexes[4][3].assignTypeAndDiceNumber("Desert", 0);
        hexes[5][3].assignTypeAndDiceNumber("Stone", 8);
        hexes[6][3].assignTypeAndDiceNumber("Wool", 4);
        hexes[2][4].assignTypeAndDiceNumber("Grain", 3);
        hexes[3][4].assignTypeAndDiceNumber("Grain", 6);
        hexes[4][4].assignTypeAndDiceNumber("Clay", 10);
        hexes[5][4].assignTypeAndDiceNumber("Wood", 11);
        hexes[6][4].assignTypeAndDiceNumber("Wool", 10);
        hexes[4][5].assignTypeAndDiceNumber("Stone", 11);
    }

    /**
     * changes hexes to basic material distribution and deploys random dice numbers.
     */
    public void generateBasicLayoutRandomDiceNumbers(){
        ModelDiceNumberRandomDeployer diceNumberRandomDeployer = new ModelDiceNumberRandomDeployer();
        hexes[3][1].assignTypeAndDiceNumber("Clay", diceNumberRandomDeployer.getADiceNumber());
        hexes[4][1].assignTypeAndDiceNumber("Grain", diceNumberRandomDeployer.getADiceNumber());
        hexes[5][1].assignTypeAndDiceNumber("Wood", diceNumberRandomDeployer.getADiceNumber());
        hexes[2][2].assignTypeAndDiceNumber("Wool", diceNumberRandomDeployer.getADiceNumber());
        hexes[3][2].assignTypeAndDiceNumber("Clay", diceNumberRandomDeployer.getADiceNumber());
        hexes[4][2].assignTypeAndDiceNumber("Wood", diceNumberRandomDeployer.getADiceNumber());
        hexes[5][2].assignTypeAndDiceNumber("Wool", diceNumberRandomDeployer.getADiceNumber());
        hexes[6][2].assignTypeAndDiceNumber("Grain", diceNumberRandomDeployer.getADiceNumber());
        hexes[2][3].assignTypeAndDiceNumber("Wood", diceNumberRandomDeployer.getADiceNumber());
        hexes[3][3].assignTypeAndDiceNumber("Stone", diceNumberRandomDeployer.getADiceNumber());
        hexes[4][3].assignTypeAndDiceNumber("Desert", 0);
        hexes[5][3].assignTypeAndDiceNumber("Stone", diceNumberRandomDeployer.getADiceNumber());
        hexes[6][3].assignTypeAndDiceNumber("Wool", diceNumberRandomDeployer.getADiceNumber());
        hexes[2][4].assignTypeAndDiceNumber("Grain", diceNumberRandomDeployer.getADiceNumber());
        hexes[3][4].assignTypeAndDiceNumber("Grain", diceNumberRandomDeployer.getADiceNumber());
        hexes[4][4].assignTypeAndDiceNumber("Clay", diceNumberRandomDeployer.getADiceNumber());
        hexes[5][4].assignTypeAndDiceNumber("Wood", diceNumberRandomDeployer.getADiceNumber());
        hexes[6][4].assignTypeAndDiceNumber("Wool", diceNumberRandomDeployer.getADiceNumber());
        hexes[4][5].assignTypeAndDiceNumber("Stone", diceNumberRandomDeployer.getADiceNumber());
    }
}
