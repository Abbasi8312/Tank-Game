package ir.ac.kntu;

import ir.ac.kntu.gamecontroller.EventHandler;
import ir.ac.kntu.gamelogic.gameconstants.GameConstants;
import ir.ac.kntu.gamelogic.services.BoardHandler;
import ir.ac.kntu.scenes.GameModeMenu;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SceneHandler {
    private final static SceneHandler INSTANCE = new SceneHandler();

    private Stage stage;

    private SceneHandler() {
    }

    public void gameModeMenu() {
        Pane pane = new Pane();
        pane.setPrefWidth(GameConstants.GAME_WIDTH);
        pane.setPrefHeight(GameConstants.GAME_HEIGHT);
        pane.setBackground(Background.EMPTY);
        new GameModeMenu(pane).start();
        Scene scene = new Scene(pane, GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT, Color.BLACK);
        stage.setScene(scene);
    }

    public void gameScene() {
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas staticCanvas = new Canvas(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);
        Canvas movingCanvas = new Canvas(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);
        root.getChildren().addAll(staticCanvas, movingCanvas);
        GraphicsContext staticGC = staticCanvas.getGraphicsContext2D();
        GraphicsContext movingGC = movingCanvas.getGraphicsContext2D();

        BoardHandler.getInstance().init();

        GameScene gameScene = new GameScene(staticGC, movingGC);
        gameScene.start();

        EventHandler.getInstance().attachEventHandlers(scene);

        stage.setScene(scene);
    }

    public static SceneHandler getINSTANCE() {
        return INSTANCE;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.centerOnScreen();
    }
}