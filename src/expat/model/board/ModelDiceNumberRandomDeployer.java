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
public class ModelDiceNumberRandomDeployer {

    private ArrayList<Integer> dicenumbers =new ArrayList<>();


    public ModelDiceNumberRandomDeployer() {
        int[] numbers = new int[]{2,3,3,4,4,5,5,6,6,8,8,9,9,10,10,11,11,12};
        for (int i = 0; i<numbers.length;i++) {
            dicenumbers.add(numbers[i]);
        }
    }
    public int getADiceNumber(){
        if (dicenumbers.isEmpty()){
            return 0;
        }else {
            Random random =new Random();
            int randint = random.nextInt(dicenumbers.size());
            int returnint = dicenumbers.get(randint);
            dicenumbers.remove(randint);
            return returnint;
        }
    }
}
