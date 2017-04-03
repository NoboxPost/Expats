package expat.model;

/**
 * Created by Rino on 02.04.2017.
 */
public class ModelPlayerGenerator {

    public static int playerID = 0;
    public String[] colors = new String[]{"Blue","Red","Yellow","Green"};

    public ModelPlayer newPlayer() {
        ModelPlayer player;
        player = new ModelPlayer(colors[playerID % 4], playerID);
        playerID += 1;
        return player;
    }
}
