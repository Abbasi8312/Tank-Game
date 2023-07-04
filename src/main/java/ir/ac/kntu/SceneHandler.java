package ir.ac.kntu;

import ir.ac.kntu.gamecontroller.EventHandler;
import ir.ac.kntu.gamelogic.gamevariables.GameVariables;
import ir.ac.kntu.gamelogic.services.GridHandler;
import ir.ac.kntu.scenes.*;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
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
        GridHandler.getInstance().init();

        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(GameVariables.gameWidth + 100);
        borderPane.setPrefHeight(GameVariables.gameHeight + 20);
        borderPane.setBackground(Background.EMPTY);
        new Game(borderPane).start();
        Scene scene = new Scene(borderPane, GameVariables.gameWidth + 100, GameVariables.gameHeight + 20, Color.BLACK);

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

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        playerScore();
    }

    public void playerScore() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(GameVariables.gameWidth);
        borderPane.setPrefHeight(GameVariables.gameHeight);
        borderPane.setBackground(Background.EMPTY);
        new ScoreBoard(borderPane).updateScores();
        Scene scene = new Scene(borderPane, GameVariables.gameWidth, GameVariables.gameHeight, Color.BLACK);

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
