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
        this.materialPool = new ModelMaterial(new int[]{10,10,10,10,10});
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
    //TODO: display gets ALL victory Points - maybe has to be split in visibleVP & invisible VP & allVP

    public String getMaterialPoolString(){
        String materialPoolString = materialPool.allMaterialsString();
        return materialPoolString;
    }

    public String getPlayerName(){
        String playerName = ("Player ");
        playerName += (color);
        return playerName;
    }

    public void changeVictoryPoints(int victoryPoints){
        this.victoryPoints += victoryPoints;
    }

}


