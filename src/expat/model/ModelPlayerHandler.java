package expat.model;

import expat.model.board.ModelBoard;

import java.util.LinkedList;

/**
 * Created by Rino on 05.04.2017.
 */
public class ModelPlayerHandler {


    private int playerCounter;
    private LinkedList<ModelPlayer> players = new LinkedList<>();
    private ModelPlayer nowPlaying;
    private ModelPlayer[] playerArray;
    private ModelBoard board;

    private int[][] amountOfBuildingsPerPlayer;
    private int[] indexOfSequenceForFirstBuilding;
    private int currentPlayerIndex = 0;


    /**
     * @param board
     */
    public ModelPlayerHandler(int playersAmount, ModelBoard board) {
        ModelPlayerGenerator playerGenerator = new ModelPlayerGenerator();
        for (int i = 0; i < playersAmount; i++) {
            players.add(playerGenerator.newPlayer());
        }
        playerCounter = players.size();
        playerArray = new ModelPlayer[playerCounter];
        for (int i = 0; i < playerCounter; i++) {
            playerArray[i] = players.get(i);
        }
        this.board = board;
        indexOfSequenceForFirstBuilding = new int[playerCounter * 2];
        int playerindex = 0;
        for (int i = 0; i < playerCounter * 2; i++) {
            if (i < playerCounter) {
                indexOfSequenceForFirstBuilding[i] = playerindex;
                playerindex += 1;
            } else {
                playerindex -= 1;
                indexOfSequenceForFirstBuilding[i] = playerindex;
            }
        }
        nowPlaying = playerArray[indexOfSequenceForFirstBuilding[currentPlayerIndex]];
        amountOfBuildingsPerPlayer = new int[playerCounter * 2][2];
        checkBuidlingsPerPlayer();
    }

    /**
     *
     */
    public void checkBuidlingsPerPlayer() {
        for (int i = 0; i < playerCounter; i++) {
            amountOfBuildingsPerPlayer[i][0] = board.countBuildingsOwned(playerArray[i]);
            amountOfBuildingsPerPlayer[i][1] = board.countConnectionsOwned(playerArray[i]);
        }
    }

    /**
     * @return
     */
    public ModelPlayer nextPlayer() {
        if (currentPlayerIndex < indexOfSequenceForFirstBuilding.length - 1) {
            currentPlayerIndex += 1;
            nowPlaying = playerArray[indexOfSequenceForFirstBuilding[currentPlayerIndex]];
            return nowPlaying;
        } else {
            players.add(players.poll());
            nowPlaying = players.peek();
            return nowPlaying;
        }
    }


    /**
     * @return
     */
    public ModelPlayer getCurrentPlayer() {
        if (currentPlayerIndex < indexOfSequenceForFirstBuilding.length - 1) {
            return nowPlaying;
        }else {
            return nowPlaying;
        }
    }

    public LinkedList<ModelPlayer> getPlayers() {
        return players;
    }
}

