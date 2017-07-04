package expat.view;

import expat.control.panes.PaneActionController;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by gallatib on 04.07.2017.
 */
public class ViewPaneBuild {
    private HashMap<String, Boolean> abilitiesToBuild;
    private PaneActionController paneActionController;
    public Image roadImage = new Image("expat/img/Connection.png");
    public Image settlementImage = new Image("expat/img/Settlement.png");
    public Image townImage = new Image("expat/img/TownColored.png");

    public ViewPaneBuild(HashMap<String, Boolean> abilitiesToBuild, PaneActionController paneActionController) {
        this.abilitiesToBuild = abilitiesToBuild;
        this.paneActionController = paneActionController;
    }


    public ImageView generateRoadImageView(){
        ImageView roadImageView = new ImageView(roadImage);
        roadImageView.setFitHeight(80);
        roadImageView.setPreserveRatio(true);

        if(abilitiesToBuild.get("Road")){
            roadImageView.setOnMouseReleased(paneActionController::generateMainRoadPlaces);
            roadImageView.setCursor(Cursor.HAND);
        }else{
            roadImageView.setEffect(new ColorAdjust(0, -1, -0.4, 0.3));
        }
        return roadImageView;
    }

    public ImageView generateSettlementImageView(){
        ImageView settlementImageView = new ImageView(settlementImage);
        settlementImageView.setFitHeight(80);
        settlementImageView.setPreserveRatio(true);

        if(abilitiesToBuild.get("Settlement")){
            settlementImageView.setOnMouseReleased(paneActionController::generateMainSettlementPlaces);
            settlementImageView.setCursor(Cursor.HAND);
        }else{
            settlementImageView.setEffect(new ColorAdjust(0, -1, 0, 0.3));
        }
        return settlementImageView;
    }

    public ImageView generateTownImageView(){
        ImageView townImageView = new ImageView(townImage);
        townImageView.setFitHeight(80);
        townImageView.setPreserveRatio(true);

        if(abilitiesToBuild.get("Town")){
            townImageView.setOnMouseReleased(paneActionController::generateMainTownPlaces);
            townImageView.setCursor(Cursor.HAND);

        }else{
            townImageView.setEffect(new ColorAdjust(0, -1, 0, 0.3));
        }
        return townImageView;
    }
}
