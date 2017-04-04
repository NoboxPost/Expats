package expat.model;

/**
 * is responsible for
 * <p>
 * created on 31.03.2017
 *
 * @author gallatib
 */

import java.util.Random;

public class ModelDiceRolling {
    private int rand1;
    private int rand2;

    public ModelDiceRolling() {
        Random rand = new Random();
        rand1 = rand.nextInt(6) + 1;
        rand2 = rand.nextInt(6) + 1;
    }


    public int getRolledDices(){
        return (rand1+rand2);
    }

    public int[] getRolledDicesSeperately(){
        return(new int[]{rand1, rand2});
    }
}
