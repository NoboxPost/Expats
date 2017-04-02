package expat.model;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ModelPlayer {
    private int winPoints = 0;
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

    public void playerMaterialToString(){

    }
    public String getColor(){
        return color;
    }

}


