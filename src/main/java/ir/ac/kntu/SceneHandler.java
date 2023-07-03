package ir.ac.kntu;

import ir.ac.kntu.gamecontroller.EventHandler;
import ir.ac.kntu.gamelogic.gameconstants.GameConstants;
import ir.ac.kntu.gamelogic.services.BoardHandler;
import ir.ac.kntu.scenes.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SceneHandler {
    private final static SceneHandler INSTANCE = new SceneHandler();

    private Stage stage;

    private SceneHandler() {
    }

    public static SceneHandler getINSTANCE() {
        return INSTANCE;
    }

    public void selectPlayer() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(GameConstants.GAME_WIDTH);
        borderPane.setPrefHeight(GameConstants.GAME_HEIGHT);
        borderPane.setBackground(Background.EMPTY);
        new SelectPlayer(borderPane).start();
        Scene scene = new Scene(borderPane, GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT, Color.BLACK);

        stage.setScene(scene);
    }

    public void selectGameMode() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(GameConstants.GAME_WIDTH);
        borderPane.setPrefHeight(GameConstants.GAME_HEIGHT);
        borderPane.setBackground(Background.EMPTY);
        new SelectGameMode(borderPane).start();
        Scene scene = new Scene(borderPane, GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT, Color.BLACK);

        stage.setScene(scene);
    }

    public void selectStage() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(GameConstants.GAME_WIDTH);
        borderPane.setPrefHeight(GameConstants.GAME_HEIGHT);
        borderPane.setBackground(Background.EMPTY);
        new SelectStage(borderPane).start();
        Scene scene = new Scene(borderPane, GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT, Color.BLACK);

        stage.setScene(scene);
    }

    public void game() {
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas staticCanvas = new Canvas(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);
        Canvas movingCanvas = new Canvas(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);
        root.getChildren().addAll(staticCanvas, movingCanvas);
        GraphicsContext staticGC = staticCanvas.getGraphicsContext2D();
        GraphicsContext movingGC = movingCanvas.getGraphicsContext2D();

        BoardHandler.getInstance().init();

        Game game = new Game(staticGC, movingGC);
        game.start();

        EventHandler.getInstance().attachEventHandlers(scene);

        stage.setScene(scene);
    }

    public void gameOver() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(GameConstants.GAME_WIDTH);
        borderPane.setPrefHeight(GameConstants.GAME_HEIGHT);
        borderPane.setBackground(Background.EMPTY);
        new GameOver(borderPane).start();
        Scene scene = new Scene(borderPane, GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT, Color.BLACK);

        stage.setScene(scene);
    }

    public void playerScore() {
        Pane pane = new Pane();
        pane.setPrefWidth(GameConstants.GAME_WIDTH);
        pane.setPrefHeight(GameConstants.GAME_HEIGHT);
        pane.setBackground(Background.EMPTY);

        Scene scene = new Scene(pane, GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT, Color.BLACK);

        stage.setScene(scene);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.centerOnScreen();
    }
}
