package expat.model.procedure;

import expat.model.ModelPlayer;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by gallatib on 26.06.2017.
 */
public class ModelPreGamePlayerHandler {
    private LinkedList<ModelPlayer> playersQueue;
    private Stack<ModelPlayer> playersStack;
    private ModelPlayer currentPreGamePlayer;

    public ModelPreGamePlayerHandler(LinkedList<ModelPlayer> players) {
        playersQueue = new LinkedList();
        playersStack = new Stack<>();

        playersQueue.addAll(players);
    }

    public void nextPlayer(){
        if(!playersQueue.isEmpty()){
            currentPreGamePlayer = playersQueue.peek();
            playersStack.add(playersQueue.poll());
        }
        else{
            currentPreGamePlayer = playersStack.pop();
        }
    }

    /**
     * gets currentPreGameUser
     *
     * @return value of currentPreGameUser
     */
    public ModelPlayer getCurrentPreGamePlayer() {
        return currentPreGamePlayer;
    }
}
