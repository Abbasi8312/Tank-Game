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
        borderPane.setPrefWidth(GameVariables.gameWidth);
        borderPane.setPrefHeight(GameVariables.gameHeight);
        borderPane.setBackground(Background.EMPTY);
        new SelectPlayer(borderPane).start();
        Scene scene = new Scene(borderPane, GameVariables.gameWidth, GameVariables.gameHeight, Color.BLACK);

        stage.setScene(scene);
    }

    public void selectGameMode() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(GameVariables.gameWidth);
        borderPane.setPrefHeight(GameVariables.gameHeight);
        borderPane.setBackground(Background.EMPTY);
        new SelectGameMode(borderPane).start();
        Scene scene = new Scene(borderPane, GameVariables.gameWidth, GameVariables.gameHeight, Color.BLACK);

        stage.setScene(scene);
    }

    public void selectStage() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(GameVariables.gameWidth);
        borderPane.setPrefHeight(GameVariables.gameHeight);
        borderPane.setBackground(Background.EMPTY);
        new SelectStage(borderPane).start();
        Scene scene = new Scene(borderPane, GameVariables.gameWidth, GameVariables.gameHeight, Color.BLACK);

        stage.setScene(scene);
    }

    public void game() {
        BoardHandler.getInstance().init();

        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas staticCanvas = new Canvas(GameVariables.gameWidth, GameVariables.gameHeight);
        Canvas movingCanvas = new Canvas(GameVariables.gameWidth, GameVariables.gameHeight);
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
        borderPane.setPrefWidth(GameVariables.gameWidth);
        borderPane.setPrefHeight(GameVariables.gameHeight);
        borderPane.setBackground(Background.EMPTY);
        new GameOver(borderPane).start();
        Scene scene = new Scene(borderPane, GameVariables.gameWidth, GameVariables.gameHeight, Color.BLACK);

        stage.setScene(scene);
    }

    public void playerScore() {
        Pane pane = new Pane();
        pane.setPrefWidth(GameVariables.gameWidth);
        pane.setPrefHeight(GameVariables.gameHeight);
        pane.setBackground(Background.EMPTY);

        Scene scene = new Scene(pane, GameVariables.gameWidth, GameVariables.gameHeight, Color.BLACK);

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
