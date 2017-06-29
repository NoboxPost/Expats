package expat.model.board;

/**
 * is responsible for the creation of a hexagonal field
 * <p>
 * created on 22.03.2017
 *
 * @author vanonir
 */
public class  ModelHexFactory {

    private boolean defaultLayout = true;
    private boolean standardHexResources = false;
    private boolean standardDiceDistribution = false;
    private int xSize, ySize;
    private ModelHex[][] hexes;
    private ModelDiceNumberDeployer diceNumberRandomDeployer;
    private ModelHexResourceDeplyoyer resourceDeployer;

    public ModelHexFactory() {
        diceNumberRandomDeployer = new ModelDiceNumberDeployer();
        resourceDeployer = new ModelHexResourceDeplyoyer();
    }

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


        if (defaultLayout) {
            if (standardDiceDistribution && standardHexResources) {
                generateHexesDefaultLayoutDefaultDices();
            } else if (standardHexResources && !standardDiceDistribution) {
                generateDefautlLayoutRandomDices();
            } else if (!standardHexResources && standardDiceDistribution){
                generateDefaultLayoutRandomHexDefaultDices();
            } else {
                generateDefaultLayoutRandomHexRandomDices();
            }
        } else {
            generateHexesDefaultLayoutDefaultDices();
        }
        //TODO:Implement random deployment of hexes and DiceNumbers

        //hexes[7][5].assignTypeAndDiceNumber("Stone",8); //Adds an additional Hex, just to show off.

        return hexes;
    }

    /**
     * generates default island with randomly distributed resources and dice numbers.
     */
    private void generateDefaultLayoutRandomHexRandomDices() {
        hexes[3][1].assignTypeAndDiceNumber(resourceDeployer.getAResource(), diceNumberRandomDeployer.getADiceNumber());
        hexes[4][1].assignTypeAndDiceNumber(resourceDeployer.getAResource(), diceNumberRandomDeployer.getADiceNumber());
        hexes[5][1].assignTypeAndDiceNumber(resourceDeployer.getAResource(), diceNumberRandomDeployer.getADiceNumber());
        hexes[2][2].assignTypeAndDiceNumber(resourceDeployer.getAResource(), diceNumberRandomDeployer.getADiceNumber());
        hexes[3][2].assignTypeAndDiceNumber(resourceDeployer.getAResource(), diceNumberRandomDeployer.getADiceNumber());
        hexes[4][2].assignTypeAndDiceNumber(resourceDeployer.getAResource(), diceNumberRandomDeployer.getADiceNumber());
        hexes[5][2].assignTypeAndDiceNumber(resourceDeployer.getAResource(), diceNumberRandomDeployer.getADiceNumber());
        hexes[6][2].assignTypeAndDiceNumber(resourceDeployer.getAResource(), diceNumberRandomDeployer.getADiceNumber());
        hexes[2][3].assignTypeAndDiceNumber(resourceDeployer.getAResource(), diceNumberRandomDeployer.getADiceNumber());
        hexes[3][3].assignTypeAndDiceNumber(resourceDeployer.getAResource(), diceNumberRandomDeployer.getADiceNumber());
        hexes[4][3].assignTypeAndDiceNumber("Desert", 0);
        hexes[5][3].assignTypeAndDiceNumber(resourceDeployer.getAResource(), diceNumberRandomDeployer.getADiceNumber());
        hexes[6][3].assignTypeAndDiceNumber(resourceDeployer.getAResource(), diceNumberRandomDeployer.getADiceNumber());
        hexes[2][4].assignTypeAndDiceNumber(resourceDeployer.getAResource(), diceNumberRandomDeployer.getADiceNumber());
        hexes[3][4].assignTypeAndDiceNumber(resourceDeployer.getAResource(), diceNumberRandomDeployer.getADiceNumber());
        hexes[4][4].assignTypeAndDiceNumber(resourceDeployer.getAResource(), diceNumberRandomDeployer.getADiceNumber());
        hexes[5][4].assignTypeAndDiceNumber(resourceDeployer.getAResource(), diceNumberRandomDeployer.getADiceNumber());
        hexes[6][4].assignTypeAndDiceNumber(resourceDeployer.getAResource(), diceNumberRandomDeployer.getADiceNumber());
        hexes[4][5].assignTypeAndDiceNumber(resourceDeployer.getAResource(), diceNumberRandomDeployer.getADiceNumber());
    }

    /**
     * changes hexes according to default layout with randomiced resources and default diceNumbers
     */
    private void generateDefaultLayoutRandomHexDefaultDices() {
        hexes[3][1].assignTypeAndDiceNumber(resourceDeployer.getAResource(), 2);
        hexes[4][1].assignTypeAndDiceNumber(resourceDeployer.getAResource(), 4);
        hexes[5][1].assignTypeAndDiceNumber(resourceDeployer.getAResource(), 6);
        hexes[2][2].assignTypeAndDiceNumber(resourceDeployer.getAResource(), 9);
        hexes[3][2].assignTypeAndDiceNumber(resourceDeployer.getAResource(), 8);
        hexes[4][2].assignTypeAndDiceNumber(resourceDeployer.getAResource(), 5);
        hexes[5][2].assignTypeAndDiceNumber(resourceDeployer.getAResource(), 12);
        hexes[6][2].assignTypeAndDiceNumber(resourceDeployer.getAResource(), 9);
        hexes[2][3].assignTypeAndDiceNumber(resourceDeployer.getAResource(), 3);
        hexes[3][3].assignTypeAndDiceNumber(resourceDeployer.getAResource(), 5);
        hexes[4][3].assignTypeAndDiceNumber("Desert", 0);
        hexes[5][3].assignTypeAndDiceNumber(resourceDeployer.getAResource(), 8);
        hexes[6][3].assignTypeAndDiceNumber(resourceDeployer.getAResource(), 4);
        hexes[2][4].assignTypeAndDiceNumber(resourceDeployer.getAResource(), 3);
        hexes[3][4].assignTypeAndDiceNumber(resourceDeployer.getAResource(), 6);
        hexes[4][4].assignTypeAndDiceNumber(resourceDeployer.getAResource(), 10);
        hexes[5][4].assignTypeAndDiceNumber(resourceDeployer.getAResource(), 11);
        hexes[6][4].assignTypeAndDiceNumber(resourceDeployer.getAResource(), 10);
        hexes[4][5].assignTypeAndDiceNumber(resourceDeployer.getAResource(), 11);

    }

    /**
     * changes hexes and dice numbers according to basic game layout.
     */
    private void generateHexesDefaultLayoutDefaultDices() {
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
    public void generateDefautlLayoutRandomDices() {
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
