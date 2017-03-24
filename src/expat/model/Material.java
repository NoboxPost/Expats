package expat.model;

/**
 * Created by vanonir on 22.03.2017.
 */
public class Material {
    private String[] materialNames = new String[]{"Clay", "Grain", "Stone", "Wood", "Wool"};
    private int[] materialAmount = new int[5];

    public Material(int[] materialAmount) {
        this.materialAmount = materialAmount;
    }


    public boolean addMaterial(Material materialToAdd) {
        for (int i = 0 ; i<5;i++){
            materialAmount[i]+= materialToAdd.getMaterialAmount()[i];
        }
        return true;
    }
    public boolean addMaterial(Material materialToAdd,int multiplier) {
        for (int i = 0 ; i<5;i++){
            materialAmount[i]+= (materialToAdd.getMaterialAmount()[i]*2);
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    private int[] getMaterialAmount() {
        return materialAmount;
    }
}
   