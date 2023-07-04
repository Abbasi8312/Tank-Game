package ir.ac.kntu;

import ir.ac.kntu.gamecontroller.EventHandler;
import ir.ac.kntu.gamelogic.gamevariables.GameVariables;
import ir.ac.kntu.scenes.*;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static ir.ac.kntu.scenes.ScoreBoard.transition;

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
        borderPane.setPrefWidth(800);
        borderPane.setPrefHeight(600);
        borderPane.setBackground(Background.EMPTY);
        new SelectPlayer(borderPane).start();
        Scene scene = new Scene(borderPane, 800, 600, Color.BLACK);

        stage.setScene(scene);
    }

    public void selectGameMode() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(800);
        borderPane.setPrefHeight(600);
        borderPane.setBackground(Background.EMPTY);
        new SelectGameMode(borderPane).start();
        Scene scene = new Scene(borderPane, 800, 600, Color.BLACK);

        stage.setScene(scene);
    }

    public void selectStage() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(800);
        borderPane.setPrefHeight(600);
        borderPane.setBackground(Background.EMPTY);
        new SelectStage(borderPane).start();
        Scene scene = new Scene(borderPane, 800, 600, Color.BLACK);

        stage.setScene(scene);
    }

    public void currentStage(int stageNumber) {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(GameVariables.gameWidth + 75);
        borderPane.setPrefHeight(GameVariables.gameHeight + 25);
        borderPane.setBackground(Background.EMPTY);
        new CurrentStage(borderPane, stageNumber).start();
        Scene scene = new Scene(borderPane, GameVariables.gameWidth + 75, GameVariables.gameHeight + 25, Color.BLACK);

        stage.setScene(scene);
    }

    public void game() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(GameVariables.gameWidth + 75);
        borderPane.setPrefHeight(GameVariables.gameHeight + 25);
        borderPane.setBackground(Background.EMPTY);
        new Game(borderPane).start();
        Scene scene = new Scene(borderPane, GameVariables.gameWidth + 75, GameVariables.gameHeight + 25, Color.BLACK);

        EventHandler.getInstance().attachEventHandlers(scene);

        stage.setScene(scene);
    }

    public void gameOver() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(GameVariables.gameWidth + 75);
        borderPane.setPrefHeight(GameVariables.gameHeight + 25);
        borderPane.setBackground(Background.EMPTY);
        Scene scene = new Scene(borderPane, GameVariables.gameWidth + 75, GameVariables.gameHeight + 25, Color.BLACK);
        new GameOver(borderPane).start();

        stage.setScene(scene);
    }

    public void playerScore() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(GameVariables.gameWidth);
        borderPane.setPrefHeight(GameVariables.gameHeight);
        borderPane.setBackground(Background.EMPTY);
        new ScoreBoard(borderPane).updateScores();
        Scene scene = new Scene(borderPane, GameVariables.gameWidth + 75, GameVariables.gameHeight + 25, Color.BLACK);
        scene.setOnMousePressed(e -> transition());
        scene.setOnKeyPressed(e -> transition());

        stage.setScene(scene);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.centerOnScreen();
    }
}
