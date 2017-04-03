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
        this.materialPool = new ModelMaterial(new int[]{0,0,0,0,0});
        this.color = color;
        this.playerID = playerID;
    }

    public void addMaterial(ModelMaterial materialToAdd){
        materialPool.addMaterial(materialToAdd);
    }

    public void reduceMaterial(ModelMaterial materialToReduce){
        materialPool.reduceMaterial(materialToReduce);
    }

    public String getColor(){
        return color;
    }

    public ModelMaterial getMaterial() {
        return materialPool;
    }

    public String getWinPointsString(){
        String winPointsString = (Integer.toString(victoryPoints));
        return winPointsString;
    }
    public String getMaterialPoolString(){
        String materialPoolString = materialPool.toString();
        return materialPoolString;
    }

    public void changeVictoryPoints(int victoryPoints){
        this.victoryPoints += victoryPoints;
    }

}


