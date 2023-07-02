package ir.ac.kntu;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Ali Abbasi
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override public void start(Stage primaryStage) {
        SceneHandler.getINSTANCE().setStage(primaryStage);
        SceneHandler.getINSTANCE().selectPlayer();

        primaryStage.show();
    }
}