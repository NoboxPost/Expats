package expat.model.board;

import expat.model.ModelMaterial;

/**
 * Created by vanonir on 22.03.2017.
 */
public abstract class ModelHex {

    protected String type;
    protected int xCoord;
    protected int yCoord;
    protected boolean raided = false;
    protected ModelMaterial material;
    protected int diceNumber;


    public ModelHex(int xCoord, int yCoord) {
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
    public ModelMaterial getMaterial(){
        return material;
    }
    public String getType(){return type;}


}
