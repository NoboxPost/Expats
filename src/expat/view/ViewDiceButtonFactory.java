package expat.view;

import expat.control.panes.PaneBoardController;
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

    /**
     * @param hexes
     * @return
     */
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

    /**
     * @param hex
     * @return
     */
    public StackPane generateDiceButton(ModelHex hex){
        Double[] coords = coordCalculator.calcHexCoords(hex);
        StackPane pane = new StackPane();
        pane.setPrefHeight(hexSize*0.8);
        pane.setPrefWidth(hexSize);
        pane.getStyleClass().add("diceButtonPane");
        ViewDiceNumber viewDiceNumber = new ViewDiceNumber(""+hex.getDiceNumber(),hex.getCoords()[0],hex.getCoords()[1]);
        Button button = (Button) viewDiceNumber;
        button.setCursor(Cursor.HAND);
        button.setOnAction(paneBoardController::hexClicked);
        button.setMinHeight(hexSize*0.25);
        button.setPrefHeight(hexSize*0.25);
        button.setMaxHeight(hexSize*0.25);
        button.setMinWidth(hexSize*0.25);
        button.setPrefWidth(hexSize*0.25);
        button.setMaxWidth(hexSize*0.25);
        styleDiceNumber(hex, button);
        pane.getChildren().add(button);
        pane.setLayoutX(coords[0]);
        pane.setLayoutY(coords[1]);
        return pane;
    }

    /**
     * adds a css-style-class to each dice number so you can style them separately
     * (numbers with higher possibility are bigger, larger and red)
     *
     * @param hex
     * @param button
     */
    private void styleDiceNumber(ModelHex hex, Button button){
        switch (hex.getDiceNumber()){
            case 2: button.getStyleClass().add("viewdicenumber2");
                    break;
            case 3: button.getStyleClass().add("viewdicenumber3");
                    break;
            case 4: button.getStyleClass().add("viewdicenumber4");
                    break;
            case 5: button.getStyleClass().add("viewdicenumber5");
                    break;
            case 6: button.getStyleClass().add("viewdicenumber6");
                    break;
            case 8: button.getStyleClass().add("viewdicenumber8");
                    break;
            case 9: button.getStyleClass().add("viewdicenumber9");
                    break;
            case 10: button.getStyleClass().add("viewdicenumber10");
                    break;
            case 11: button.getStyleClass().add("viewdicenumber11");
                    break;
            case 12: button.getStyleClass().add("viewdicenumber12");
                    break;
        }
    }

}
