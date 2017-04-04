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
        boolean belowZero = false;
        int[] materialCheck = materialAmount.clone();
        for (int i = 0; i < 5; i++) {
            materialCheck[i] -= materialToReduce.getMaterialAmount()[i];
            if (materialCheck[i] < 0) {
                belowZero = true;
            }
        }
        if (belowZero){
            return false;
        }else {
            for (int i = 0; i < 5; i++) {
                materialAmount[i]-=materialToReduce.getMaterialAmount()[i];
            }
            return true;
        }
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

    public int[] getMaterialAmount() {
        return materialAmount;
    }

    public String[] getMaterialNames() {
        return materialNames;
    }

}
