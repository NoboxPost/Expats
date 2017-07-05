package expat.model.procedure;

import expat.model.ModelPlayer;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Created by gallatib on 26.06.2017.
 */
public class ModelMainGamePlayerHandler {
    private LinkedList<ModelPlayer> players;
    private ModelPlayer currentMainGamePlayer;

    public ModelMainGamePlayerHandler(LinkedList<ModelPlayer> players) {
        this.players = players;
    }

    public void nextPlayer(){
        currentMainGamePlayer = players.getFirst();
        players.addLast(players.getFirst());
        players.removeFirst();
    }

    /**
     * gets currentPreGameUser
     *
     * @return value of currentPreGameUser
     */
    public ModelPlayer getCurrentMainGamePlayer() {
        return currentMainGamePlayer;
    }

    /**
     * gets players
     *
     * @return value of players
     */
    public LinkedList<ModelPlayer> getPlayers() {
        return players;
    }
}
