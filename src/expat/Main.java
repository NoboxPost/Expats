package expat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    @Override
    public void start( Stage primaryStage) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("view/MainStage.fxml"));
            primaryStage.setTitle("Expats of Engehalde");
            Scene primaryScene = new Scene(root, 800, 600);
            primaryStage.setScene(primaryScene);
            primaryScene.getStylesheets().addAll(this.getClass().getResource("view/style.css").toExternalForm());
            primaryStage.setMaximized(true); //TODO: eventually change to setFullscreen(true)
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        ModelApp app = new ModelApp(this, controllerBoardPane);
    }


    public static void main(String[] args) {
        launch(args);

    }
}
