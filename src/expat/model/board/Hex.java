package expat.model.board;

import expat.model.Material;

/**
 * Created by vanonir on 22.03.2017.
 */
public abstract class Hex {

    protected String type;
    protected int xCoord;
    protected int yCoord;
    protected boolean raided = false;
    protected Material material;
    protected int diceNumber;


    public Hex(int xCoord,int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
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
