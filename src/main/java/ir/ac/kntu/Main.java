package ir.ac.kntu;

import ir.ac.kntu.logic.model.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * @author Sina Rostami
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new Pane());
        Board board = new Board();
        board.start();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}