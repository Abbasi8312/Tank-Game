package ir.ac.kntu;

import ir.ac.kntu.gamecontroller.EventHandler;
import ir.ac.kntu.graphics.DrawObjects;
import ir.ac.kntu.logic.model.Board;
import ir.ac.kntu.logic.model.GameConstants;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @author Sina Rostami
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);
        root.getChildren().add(canvas);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        Board board = new Board(executor);

        DrawObjects drawObjects = new DrawObjects(board, graphicsContext);
        drawObjects.start();

        board.update();
        EventHandler.getInstance().attachEventHandlers(scene);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}