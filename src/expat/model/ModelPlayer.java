package expat.model;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ModelPlayer {
    private int winPoints = 0;
    private ModelMaterial materialPool;
    private String color;

    public ModelPlayer(String color) {
        this.materialPool = new ModelMaterial(new int[]{0,0,0,0,0});
        this.color = color;
    }

    public void addMaterial(ModelMaterial materialToAdd){
        materialPool.addMaterial(materialToAdd);
    }

    public void playerMaterialToString(){

    }

}


