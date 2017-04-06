package expat.model;

import expat.model.board.ModelBoard;
import expat.model.buildings.ModelBuildingAction;

import java.util.List;

/**
 * Created by Rino on 05.04.2017.
 */
public class ModelFirstBuildingActionSequence {


    private int playerCounter;
    private ModelPlayer[] players;
    private ModelBoard board;

    private int[][] amountOfBuildingsPerPlayer;
    private int[] indexOfSequenceForFirstBuilding;
    private int currentPlayerIndex = 0;



    public ModelFirstBuildingActionSequence(List<ModelPlayer> playerList, ModelBoard board) {
        playerCounter = playerList.size();
        players = new ModelPlayer[playerCounter];
        for (int i = 0; i < playerCounter; i++) {
            players[i] = playerList.get(i);
        }
        this.board = board;
        indexOfSequenceForFirstBuilding = new int[playerCounter*2];
        int playerindex =0;
        for (int i = 0; i < playerCounter*2; i++) {
            if (i<playerCounter){
                indexOfSequenceForFirstBuilding[i]=playerindex;
                playerindex+=1;
            }else {
                playerindex-=1;
                indexOfSequenceForFirstBuilding[i]=playerindex;
            }
        }

        amountOfBuildingsPerPlayer = new int[playerCounter*2][2];
        checkBuidlingsPerPlayer();
    }

    /**
     *
     */
    public void checkBuidlingsPerPlayer() {
        for (int i = 0; i < playerCounter; i++) {
            amountOfBuildingsPerPlayer[i][0] = board.countBuildingsOwned(players[i]);
            amountOfBuildingsPerPlayer[i][1] = board.countConnectionsOwned(players[i]);
        }
    }

    /**
     * @return
     */
    public boolean nextPlayer(){
        if (currentPlayerIndex<indexOfSequenceForFirstBuilding.length-1){
            currentPlayerIndex+=1;
            return true;
        }else{
            return false;
        }
    }


    /**
     * @return
     */
    public ModelPlayer getCurrentPlayer(){
        return players[indexOfSequenceForFirstBuilding[currentPlayerIndex]];
    }

}

