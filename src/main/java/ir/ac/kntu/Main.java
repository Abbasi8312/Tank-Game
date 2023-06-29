package ir.ac.kntu;

import ir.ac.kntu.gamecontroller.EventHandler;
import ir.ac.kntu.graphics.DrawObjects;
import ir.ac.kntu.gamelogic.model.Board;
import ir.ac.kntu.gamelogic.gameconstants.GameConstants;
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
        Canvas staticCanvas = new Canvas(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);
        Canvas movingCanvas = new Canvas(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);
        root.getChildren().addAll(staticCanvas, movingCanvas);
        GraphicsContext staticGC = staticCanvas.getGraphicsContext2D();
        GraphicsContext movingGC = movingCanvas.getGraphicsContext2D();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        Board board = new Board(executor);

        DrawObjects drawObjects = new DrawObjects(board, staticGC, movingGC);
        drawObjects.start();

        board.update();
        EventHandler.getInstance().attachEventHandlers(scene);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}