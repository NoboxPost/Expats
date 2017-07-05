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
    private Random random;

    /**
     * Constructor for diceRoll, so next roll can be generated
     */
    public ModelDiceRoller() {
        random = new Random();
        rand1 = random.nextInt(6) + 1;
        rand2 = random.nextInt(6) + 1;
    }

    /**
     * Get dice roll as a combined event
     *
     * @return int dice numebr
     */
    public int rollDices(){
        rand1 = random.nextInt(6) + 1;
        rand2 = random.nextInt(6) + 1;

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
