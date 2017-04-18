package expat.model.board;

import java.util.ArrayList;
import java.util.Random;

/**
 * is responsible for deploying all dicenumbers for all hexes with ressources. Contains a List with all the dicenumers which have to be deployed. A random int decides which number goes to the next field.
 * <p>
 * created on 29.03.2017
 *
 * @author vanonir
 */
public class ModelDiceNumberDeployer {

    private int[] numbers = new int[]{2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12};
    private ArrayList<Integer> dicenumbers = new ArrayList<>();


    /**
     * Constructor for default hexLayout and default dice numbers so they can be distributed randomly
     */
    public ModelDiceNumberDeployer() {
        for (int i = 0; i < numbers.length; i++) {
            dicenumbers.add(numbers[i]);
        }
    }


    /**
     * Returns a random dice number out of the collection.
     *
     * @return
     */
    public int getADiceNumber() {
        if (dicenumbers.isEmpty()) {
            for (int i = 0; i < numbers.length; i++) {
                dicenumbers.add(numbers[i]);
            }
        }
        Random random = new Random();
        int randint = random.nextInt(dicenumbers.size());
        int returnint = dicenumbers.get(randint);
        dicenumbers.remove(randint);
        return returnint;
    }
}
