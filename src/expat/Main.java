package expat;

import expat.model.ModelApp;
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
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.setMaximized(true); //TODO: eventually change to setFullscreen(true)
            primaryStage.show();
            loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        ModelApp app = new ModelApp(this, controllerBoardPane);
    }


    public static void main(String[] args) {
        launch(args);

    }
}
