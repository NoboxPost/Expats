package expat.model;

/**
 * is responsible for
 * <p>
 * created on 31.03.2017
 *
 * @author gallatib
 */

import java.util.Random;

public class ModelThrowDice {

    public ModelThrowDice() {
    }

    public int getRandomDiceNumber(){
        Random rand = new Random();
        int rand1 = rand.nextInt(6) + 1;
        int rand2 = rand.nextInt(6) + 1;
        return(rand1+rand2);
    }
}
