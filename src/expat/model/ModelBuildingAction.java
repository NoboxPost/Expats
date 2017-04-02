package expat.model;

/**
 * Created by Rino on 01.04.2017.
 *
 * Delivers all methods for the app, so a player can build new Buildings and connections depending on his Materials and aviable building spots.
 */
public class ModelBuildingAction {
    private ModelPlayer player;
    private ModelMaterial materials;
    private String buildingType;

    public ModelBuildingAction(ModelPlayer player, ModelMaterial materials, String buildingType) {
        this.player = player;
        this.materials = materials;
        this.buildingType = buildingType;
        // TODO: depending on buildingType, show possible locations for types
        //TODO: switch case for building and COST!, COST is not handled by building, so need to do i here.
    }
}
