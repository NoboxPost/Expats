package expat.model.board;

import expat.model.Material;

/**
 * Created by vanonir on 22.03.2017.
 */
public abstract class Hex {

    protected String type;
    protected int id;
    protected boolean raided = false;
    protected Material material;
    protected int diceNumber;


    public Hex(int id) {
        this.id = id;
    }


    public boolean isRaided(){
        return raided;
    }

    public void block(){
        if (raided){
            raided = false;
        }else {
            raided = true;
        }
    }
    public Material getMaterial(){
        return material;
    }
    public String getType(){return type;}


}
