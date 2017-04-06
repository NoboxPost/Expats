package expat.model;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ModelMaterial {
    private String[] materialNames = new String[]{"Clay", "Grain", "Stone", "Wood", "Wool"};
    private int[] materialAmount = new int[5];



    public ModelMaterial(){
        materialAmount = new int[]{0,0,0,0,0};
    }

    public ModelMaterial(int[] materialAmount) {
        this.materialAmount = materialAmount;
    }


    /**
     * @param materialToAdd
     * @return
     */
    public boolean addMaterial(ModelMaterial materialToAdd) {
        for (int i = 0 ; i<5;i++){
            materialAmount[i]+= materialToAdd.getMaterialAmount()[i];
        }
        return true;
    }

    /**
     * @param materialToReduce
     * @return
     */
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

    /**
     * @return
     */
    public String allMaterialsString() {
        String materialString = "";

        for(int i = 0; i<5; i++){
            materialString += Integer.toString(materialAmount[i]);
            materialString += " " + materialNames[i];
            materialString += '\n';
        }
        return materialString;
    }

    /**
     * @return
     */
    public int getSumOfAllMaterials(){
        int materialSum = 0;
        for (int i = 0; i < 5; i++) {
            materialSum += materialAmount[i];
        }
        return materialSum;
    }

    /**
     * @return
     */
    public int[] getMaterialAmount() {
        return materialAmount;
    }

    /**
     * @return
     */
    public String[] getMaterialNames() {
        return materialNames;
    }

}
