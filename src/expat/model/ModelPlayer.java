package expat.model;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ModelPlayer {
    private int victoryPoints = 0;
    private ModelMaterial materialPool;
    private String color;
    private int playerID;

    public ModelPlayer(String color, int playerID) {
        this.materialPool = new ModelMaterial();
        this.color = color;
        this.playerID = playerID;
    }

    /**
     * @param materialToAdd
     */
    public void addMaterial(ModelMaterial materialToAdd){
        materialPool.addMaterial(materialToAdd);
    }

    /**
     * @param materialToReduce
     * @return
     */
    public boolean reduceMaterial(ModelMaterial materialToReduce){
        if (materialPool.reduceMaterial(materialToReduce)){
            return true;
        }
        return false;
    }

    public ModelMaterial takeRandomMaterial(){
        return materialPool.takeRandomMaterial();
    }

    /**
     * @return
     */
    public String getColor(){
        return color;
    }

    /**
     * @return
     */
    public ModelMaterial getMaterial() {
        return materialPool;
    }

    /**
     * @return
     */
    public String getWinPointsString(){
        String winPointsString = (Integer.toString(victoryPoints));
        return winPointsString;
    }
    //TODO: display gets ALL victory Points - maybe has to be split in visibleV P & invisible VP & allVP



    /**
     * @return
     */
    public String getPlayerName(){
        String playerName = ("Player ");
        playerName += (color);
        return playerName;
    }

    /**
     * @param victoryPoints
     */
    public void changeVictoryPoints(int victoryPoints){
        this.victoryPoints += victoryPoints;
    }

}


