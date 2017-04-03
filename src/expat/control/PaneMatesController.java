package expat.control;

import javafx.scene.control.TextArea;

/**
 * the right hand pane that works as a display for other player progress (victory points)
 * <p>
 * created on 29.03.2017
 *
 * @author gallatib
 */
public class PaneMatesController {

    public TextArea matesVictoryPointsTextArea;


    public void setMatesInformation(String winPointsString){
        matesVictoryPointsTextArea.setText(winPointsString);
    }
}
