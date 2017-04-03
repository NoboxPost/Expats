package expat.model;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ModelMaterial {
    private String[] materialNames = new String[]{"Clay", "Grain", "Stone", "Wood", "Wool"};
    private int[] materialAmount = new int[5];

    public ModelMaterial(int[] materialAmount) {
        this.materialAmount = materialAmount;
    }


    public boolean addMaterial(ModelMaterial materialToAdd) {
        for (int i = 0 ; i<5;i++){
            materialAmount[i]+= materialToAdd.getMaterialAmount()[i];
        }
        return true;
    }

    public boolean reduceMaterial(ModelMaterial materialToReduce) {
        for (int i = 0; i < 5; i++) {
            if (materialAmount[i]- materialToReduce.getMaterialAmount()[i]>=0) {
                materialAmount[i] -= (materialToReduce.getMaterialAmount()[i]);
            }else {
                return false;
            }
        }
        return false; //TODO: Reduces all materials untill below 0, need to check whole array before some changes are done.
    }

    public String allMaterialsString() {
        String materialString = "";

        for(int i = 0; i<5; i++){
            materialString += Integer.toString(materialAmount[i]);
            materialString += " " + materialNames[i];
            materialString += '\n';
        }
        return materialString;
    }

    private int[] getMaterialAmount() {
        return materialAmount;
    }

}
