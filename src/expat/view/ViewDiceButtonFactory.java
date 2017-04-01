package expat.view;

import expat.control.PaneBoardController;
import expat.model.board.ModelHex;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

/**
 * is responsible for
 * <p>
 * created on 29.03.2017
 *
 * @author vanonir
 */
public class ViewDiceButtonFactory {
    private final PaneBoardController paneBoardController;
    private int hexSize;
    private ViewCoordinateCalculator coordCalculator ;


    public ViewDiceButtonFactory(int hexSize, PaneBoardController paneBoardController) {
        this.hexSize = hexSize;
        this.paneBoardController = paneBoardController;
        coordCalculator = new ViewCoordinateCalculator(hexSize);
    }
    public List<StackPane> generateDiceButtons(ModelHex[][] hexes){
        List<StackPane> buttonPanes= new ArrayList<StackPane>();
        for (ModelHex[] hexline : hexes) {
            for (ModelHex hex : hexline) {
                if (hex.getDiceNumber()!=0){
                    buttonPanes.add(generateDiceButton(hex));
                }
            }
        }
        return buttonPanes;
    }
    public StackPane generateDiceButton(ModelHex hex){
        Double[] coords = coordCalculator.calcHexCoords(hex);
        StackPane pane = new StackPane();
        pane.setPrefHeight(hexSize*0.8);
        pane.setPrefWidth(hexSize);
        ViewDiceNumber viewDiceNumber = new ViewDiceNumber(""+hex.getDiceNumber(),hex.getCoords()[0],hex.getCoords()[1]);
        Button button = (Button) viewDiceNumber;
        button.setCursor(Cursor.HAND);
        button.setOnAction(paneBoardController::hexClicked);
        button.getStyleClass().add("viewdicenumber");
        button.setMinHeight(hexSize*0.25);
        button.setPrefHeight(hexSize*0.25);
        button.setMaxHeight(hexSize*0.25);
        button.setMinWidth(hexSize*0.25);
        button.setPrefWidth(hexSize*0.25);
        button.setMaxWidth(hexSize*0.25);
        pane.getChildren().add(button);
        pane.setLayoutX(coords[0]);
        pane.setLayoutY(coords[1]);
        return pane;
    }

}
