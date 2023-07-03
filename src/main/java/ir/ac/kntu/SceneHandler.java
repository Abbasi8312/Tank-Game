package ir.ac.kntu;

import ir.ac.kntu.gamecontroller.EventHandler;
import ir.ac.kntu.gamelogic.gamevariables.GameVariables;
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
        borderPane.setPrefWidth(GameVariables.GAME_WIDTH);
        borderPane.setPrefHeight(GameVariables.GAME_HEIGHT);
        borderPane.setBackground(Background.EMPTY);
        new SelectPlayer(borderPane).start();
        Scene scene = new Scene(borderPane, GameVariables.GAME_WIDTH, GameVariables.GAME_HEIGHT, Color.BLACK);

        stage.setScene(scene);
    }

    public void selectGameMode() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(GameVariables.GAME_WIDTH);
        borderPane.setPrefHeight(GameVariables.GAME_HEIGHT);
        borderPane.setBackground(Background.EMPTY);
        new SelectGameMode(borderPane).start();
        Scene scene = new Scene(borderPane, GameVariables.GAME_WIDTH, GameVariables.GAME_HEIGHT, Color.BLACK);

        stage.setScene(scene);
    }

    public void selectStage() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(GameVariables.GAME_WIDTH);
        borderPane.setPrefHeight(GameVariables.GAME_HEIGHT);
        borderPane.setBackground(Background.EMPTY);
        new SelectStage(borderPane).start();
        Scene scene = new Scene(borderPane, GameVariables.GAME_WIDTH, GameVariables.GAME_HEIGHT, Color.BLACK);

        stage.setScene(scene);
    }

    public void game() {
        BoardHandler.getInstance().init();

        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas staticCanvas = new Canvas(GameVariables.GAME_WIDTH, GameVariables.GAME_HEIGHT);
        Canvas movingCanvas = new Canvas(GameVariables.GAME_WIDTH, GameVariables.GAME_HEIGHT);
        root.getChildren().addAll(staticCanvas, movingCanvas);
        GraphicsContext staticGC = staticCanvas.getGraphicsContext2D();
        GraphicsContext movingGC = movingCanvas.getGraphicsContext2D();

        Game game = new Game(staticGC, movingGC);
        game.start();

        EventHandler.getInstance().attachEventHandlers(scene);

        stage.setScene(scene);
    }

    public void gameOver() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(GameVariables.GAME_WIDTH);
        borderPane.setPrefHeight(GameVariables.GAME_HEIGHT);
        borderPane.setBackground(Background.EMPTY);
        new GameOver(borderPane).start();
        Scene scene = new Scene(borderPane, GameVariables.GAME_WIDTH, GameVariables.GAME_HEIGHT, Color.BLACK);

        stage.setScene(scene);
    }

    public void playerScore() {
        Pane pane = new Pane();
        pane.setPrefWidth(GameVariables.GAME_WIDTH);
        pane.setPrefHeight(GameVariables.GAME_HEIGHT);
        pane.setBackground(Background.EMPTY);

        Scene scene = new Scene(pane, GameVariables.GAME_WIDTH, GameVariables.GAME_HEIGHT, Color.BLACK);

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
