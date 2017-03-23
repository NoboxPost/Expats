package ExpatsOfEngehalde.Controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by vanonir on 22.03.2017.
 */
public class MainStageController implements Initializable {

    private BoardPaneController boardPaneController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boardPaneController = new BoardPaneController();
    }
}
