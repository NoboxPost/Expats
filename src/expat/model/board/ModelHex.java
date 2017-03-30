package expat.model.board;

import expat.model.ModelMaterial;



/**
 * Created by vanonir on 22.03.2017.
 */
public class ModelHex {

    protected String type = "Water";
    protected int xCoord;
    protected int yCoord;
    protected boolean raidable= false;
    protected boolean raided = false;



    protected boolean allowsBuildings = false;
    protected ModelMaterial material = new ModelMaterial(new int[]{0,0,0,0,0});
    protected int diceNumber;



    public ModelHex(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public boolean asignDiceNumber(int newDiceNumber){
        if (diceNumber == 0){
            if (newDiceNumber>=1&&newDiceNumber<=12){
                diceNumber = newDiceNumber;
                return true;
            }else {return false;}
        }else {
            return false;
        }
    }

    public boolean raid(){
        if (!raidable) {
            if (raided){
                raided = false;
            }else {
                raided = true;
            }
            return true;
        } else {
            return false;
        }
    }
    public ModelMaterial getMaterial(){
        return material;
    }
    public String getType(){return type;}

    public boolean checkIfRaided(){
        return raided;
    }

    public int getDiceNumber() {
        return diceNumber;
    }

    public int[] getCoords(){
        return new int[]{xCoord,yCoord};
    }
    public void setTypeAndDiceNumber(String type, int diceNumber){
        this.type = type;
        if (!type.equals("Water")){
            allowsBuildings = true;
        }else {
            allowsBuildings =false;
        }
        int[] mat;
        switch (type){
            case "Clay":
                 mat = new int[]{1,0,0,0,0};
                 break;
            case "Grain":
                mat = new int[]{0,1,0,0,0};
                break;
            case "Stone":
                mat = new int[]{0,0,1,0,0};
                break;
            case "Wood":
                mat = new int[]{0,0,0,1,0};
                break;
            case "Wool":
                mat = new int[]{0,0,0,0,1};
                break;
            default:
                mat = new int[]{0,0,0,0,0};
        }
        this.material = new ModelMaterial(mat);
        this.diceNumber = diceNumber;
    }
    public boolean getAllowsBuildings() {
        return allowsBuildings;
    }

}
