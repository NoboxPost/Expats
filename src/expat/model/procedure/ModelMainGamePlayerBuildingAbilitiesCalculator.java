package expat.model.procedure;

import expat.model.ModelMaterial;

import java.util.HashMap;

/**
 * Created by gallatib on 04.07.2017.
 */
public class ModelMainGamePlayerBuildingAbilitiesCalculator {

    public HashMap<String, Boolean> playerBuildingAbilities(int[] playerMaterialAmount) {
        HashMap<String, int[]> costToBuildMap = new HashMap<>();
        costToBuildMap.put("Road", new int[]{1, 0, 0, 1, 0});
        costToBuildMap.put("Boat", new int[]{0, 0, 0, 1, 1});
        costToBuildMap.put("Settlement", new int[]{1, 1, 0, 1, 1});
        costToBuildMap.put("Town", new int[]{0, 2, 3, 0, 0});
        costToBuildMap.put("Development", new int[]{0, 1, 1, 0, 1});

        HashMap<String, Boolean> abilitiesToBuildMap = new HashMap<>();
        costToBuildMap.forEach((key,value) -> abilitiesToBuildMap.put(key, playerHasEnoughMaterialsToBuild(value, playerMaterialAmount)));


        return abilitiesToBuildMap;
    }

    private boolean playerHasEnoughMaterialsToBuild(int[] buildingCostAmount, int[] playerMaterialAmount){
        boolean playerHasEnoughMaterialsToBuild = true;
        for(int i = 0; i < 5; i++){
            if(playerMaterialAmount[i] - buildingCostAmount[i] < 0){
                playerHasEnoughMaterialsToBuild = false;
                break;
            }
        }
        return playerHasEnoughMaterialsToBuild;
    }
}
