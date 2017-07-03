package expat.model.procedure;

import expat.model.ModelPlayer;

import java.util.LinkedList;

/**
 * Created by gallatib on 03.07.2017.
 */
public class ModelDroppingPlayerHandler {

    private LinkedList<ModelPlayer> playersThatMustDrop;
    private ModelPlayer currentDroppingPlayer;

    public ModelDroppingPlayerHandler(LinkedList<ModelPlayer> allPlayers) {
        playersThatMustDrop = new LinkedList<>();
        generatePlayersThatMustDropList(allPlayers);
    }

    private void generatePlayersThatMustDropList(LinkedList<ModelPlayer> allPlayers){
        for (ModelPlayer player : allPlayers) {
            if (player.getMaterial().getSumOfAllMaterials() > 7) {
                playersThatMustDrop.add(player);
            }
        }
    }

    public ModelPlayer nextPlayer() {
        return playersThatMustDrop.pollLast();
    }

    public ModelPlayer getNextPlayerThatMustDrop(){
        return playersThatMustDrop.peek();
    }

    /**
     * gets playersThatMustDrop
     *
     * @return value of playersThatMustDrop
     */
    public LinkedList<ModelPlayer> getPlayersThatMustDrop() {
        return playersThatMustDrop;
    }

    /**
     * gets currentDroppingPlayer
     *
     * @return value of currentDroppingPlayer
     */
    public ModelPlayer getCurrentDroppingPlayer() {
        return currentDroppingPlayer;
    }
}
