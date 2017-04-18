package expat.model.board;

import java.util.ArrayList;
import java.util.Random;

/**
 * is responsible for
 * <p>
 * created on 18.04.2017
 *
 * @author vanonir
 */
public class ModelHexResourceDeplyoyer {
    private String[] resources= new String[]{"Clay","Clay","Clay","Grain","Grain","Grain","Stone","Stone","Stone","Wood","Wood","Wood","Wood","Wool","Wool","Wool","Wool"};
    private ArrayList<String> resourceCollection = new ArrayList<>();

    public ModelHexResourceDeplyoyer() {
        for (int i = 0; i < resources.length; i++) {
            resourceCollection.add(resources[i]);
        }
    }
    public String getAResource() {
        if (resourceCollection.isEmpty()) {
            for (int i = 0; i < resources.length; i++) {
                resourceCollection.add(resources[i]);
            }
        }
        Random random = new Random();
        int randint = random.nextInt(resourceCollection.size());
        String returnString = resourceCollection.get(randint);
        resourceCollection.remove(randint);
        return returnString;
    }


}
