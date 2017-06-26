package expat.model.procedure;

/**
 * is responsible for generation of dice number, which means 2 random int 1-6 are added.
 * <p>
 * created on 31.03.2017
 *
 * @author gallatib
 */

import java.util.Random;

/**
 *
 */
public class ModelDiceRoller {
    private int rand1;
    private int rand2;

    /**
     * Constructor for diceRoll, so next roll can be generated
     */
    public ModelDiceRoller() {
        Random rand = new Random();
        rand1 = rand.nextInt(6) + 1;
        rand2 = rand.nextInt(6) + 1;
    }

    /**
     * Get dice roll as a combined event
     *
     * @return int dice numebr
     */
    public int getRolledDices(){
        return (rand1+rand2);
    }

    /**
     * Get dice roll as 2 integers in a int array
     *
     * @return
     */
    public int[] getRolledDicesSeparately(){
        return(new int[]{rand1, rand2});
    }
}
