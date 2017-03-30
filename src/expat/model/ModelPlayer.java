package expat.model;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ModelPlayer {
    private int winPoints;
    private ModelMaterial materialPool;

    public ModelPlayer(int winPoints, ModelMaterial materialPool) {
        this.winPoints = winPoints;
        this.materialPool = materialPool;
    }

    public void addMaterial(ModelMaterial materialToAdd){
        materialPool.addMaterial(materialToAdd);
    }

}


