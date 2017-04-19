package expat.model;

import java.util.Random;

/**
 * Created by vanonir on 22.03.2017.
 */
public class ModelMaterial {
    private String[] materialNames = new String[]{"Clay", "Grain", "Stone", "Wood", "Wool"};
    private int[] materialAmount = new int[5];


    public ModelMaterial() {
        materialAmount = new int[]{0, 0, 0, 0, 0};
    }

    public ModelMaterial(int[] materialAmount) {
        this.materialAmount = materialAmount;
    }


    /**
     * @param materialToAdd
     * @return
     */
    public boolean addMaterial(ModelMaterial materialToAdd) {
        for (int i = 0; i < 5; i++) {
            materialAmount[i] += materialToAdd.getMaterialAmount()[i];
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
        if (belowZero) {
            return false;
        } else {
            for (int i = 0; i < 5; i++) {
                materialAmount[i] -= materialToReduce.getMaterialAmount()[i];
            }
            return true;
        }
    }

    /**
     * Reduces a Random Material by 1 and returns it.
     * takes a random integer and iterates though all resources available like they where like cards on one Stack.
     *
     *
     * @return
     */
    public ModelMaterial takeRandomMaterial() {
        int[] returnIntArray = new int[5];
        if (getSumOfAllMaterials() != 0) {
            //put all resource names in an string array.
            String[] resources = new String[getSumOfAllMaterials()];
            int iterator = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < materialAmount[i]; j++) {
                    resources[iterator] = materialNames[i];
                    iterator++;
                }
            }
            // take a random int out of the array
            Random rand = new Random();
            String resourceToReduce = resources[rand.nextInt(getSumOfAllMaterials())];

            //reduce the chosen resource.
            for (int i = 0; i < 5; i++) {
                if (resourceToReduce.equals(materialNames[i])){
                    materialAmount[i]--;
                    returnIntArray[i]++;
                }
            }

        }
        //create material to be returned and add corresponding material.
        return new ModelMaterial(returnIntArray);
    }


    /**
     * @return
     */
    public String allMaterialsString() {
        String materialString = "";

        for (int i = 0; i < 5; i++) {
            materialString += Integer.toString(materialAmount[i]);
            materialString += " " + materialNames[i];
            materialString += '\n';
        }
        return materialString;
    }

    /**
     * @return
     */
    public int getSumOfAllMaterials() {
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
