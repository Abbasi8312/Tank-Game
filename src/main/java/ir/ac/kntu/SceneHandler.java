package ir.ac.kntu;

import ir.ac.kntu.gamecontroller.EventHandler;
import ir.ac.kntu.gamelogic.gamevariables.GameVariables;
import ir.ac.kntu.gamelogic.services.GridHandler;
import ir.ac.kntu.scenes.*;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

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

        PauseTransition delay = new PauseTransition(Duration.seconds(2));

        delay.setOnFinished(e -> {
            playerScore();
        });
        delay.play();
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
