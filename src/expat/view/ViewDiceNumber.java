package expat.view;

import javafx.scene.control.Button;

/**
 * is responsible for
 * <p>
 * created on 29.03.2017
 *
 * @author vanonir
 */
public class ViewDiceNumber extends Button {
    private int xCoord;
    private int yCoord;


    public ViewDiceNumber(String text, int xCoord, int yCoord) {
        super(text);
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    /**
     * @return
     */
    public int[] getCoords() {
        return new int[]{xCoord,yCoord};
    }
}
