package ir.ac.kntu.scenes;

import ir.ac.kntu.SceneHandler;
import ir.ac.kntu.gamelogic.gamevariables.GameVariables;
import ir.ac.kntu.gamelogic.models.GameObject;
import ir.ac.kntu.gamelogic.services.GridHandler;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Game {

    private final BorderPane borderPane;
    private final AnimationTimer animationTimer;
    private final GameRenderer gameRenderer;
    private final VBox vBox;
    private final Label infoLabel;
    private final ImageView playerImageView;
    private final Label healthLabel;

    private final ImageView enemyImageView;

    private final Label enemyCountLabel;

    private final Label stageNumberLabel;

    public Game(BorderPane borderPane) {
        this.borderPane = borderPane;
        borderPane.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        Group root = new Group();
        Canvas staticCanvas = new Canvas(GameVariables.gameWidth, GameVariables.gameHeight);
        Canvas movingCanvas = new Canvas(GameVariables.gameWidth, GameVariables.gameHeight);
        root.getChildren().addAll(staticCanvas, movingCanvas);

        GraphicsContext staticGC = staticCanvas.getGraphicsContext2D();
        GraphicsContext movingGC = movingCanvas.getGraphicsContext2D();
        staticGC.fillRect(0, 0, GameVariables.gameWidth, GameVariables.gameHeight);

        gameRenderer = new GameRenderer(staticGC, movingGC);

        animationTimer = new GameLoop();
        borderPane.setLeft(root);

        infoLabel = new Label("Press 'P' to pause/unpause");
        infoLabel.setFont(Font.font(16));
        infoLabel.setAlignment(Pos.CENTER);

        vBox = new VBox(10);
        vBox.setPadding(new Insets(10));
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(100);

        enemyImageView = new ImageView(new Image("images/sprites/Lucky Regular Enemy/down1.png"));
        enemyImageView.setFitWidth(20);
        enemyImageView.setPreserveRatio(true);

        enemyCountLabel = new Label();
        enemyCountLabel.setFont(Font.font(14));

        playerImageView = new ImageView(new Image("images/sprites/Regular Player 1/down1.png"));
        playerImageView.setFitWidth(20);
        playerImageView.setPreserveRatio(true);

        healthLabel = new Label();
        healthLabel.setFont(Font.font(14));

        stageNumberLabel = new Label();
        stageNumberLabel.setFont(Font.font(14));

        HBox playerBox = new HBox(10);
        playerBox.setAlignment(Pos.CENTER);
        playerBox.getChildren().addAll(playerImageView, healthLabel);

        HBox enemyBox = new HBox(10);
        enemyBox.setAlignment(Pos.CENTER);
        enemyBox.getChildren().addAll(enemyImageView, enemyCountLabel);

        vBox.getChildren().addAll(enemyBox, playerBox, stageNumberLabel);

        borderPane.setRight(vBox);

        borderPane.setBottom(infoLabel);
        BorderPane.setAlignment(infoLabel, Pos.CENTER);
    }

    public void start() {
        animationTimer.start();
    }

    private record GameRenderer(GraphicsContext staticGC, GraphicsContext movingGC) {
        public void clearStatics() {
            for (GameObject gameObject : GridHandler.getInstance().getUpdatedStatics()) {
                staticGC.setFill(Color.BLACK);
                staticGC.fillRect(gameObject.getX() - gameObject.getWidth() / 2,
                        gameObject.getY() - gameObject.getHeight() / 2, gameObject.getWidth(), gameObject.getHeight());
            }
        }

        public void clearMovables() {
            for (GameObject gameObject : GridHandler.getInstance().getMovables()) {
                movingGC.clearRect(gameObject.getX() - gameObject.getWidth() / 2,
                        gameObject.getY() - gameObject.getHeight() / 2, gameObject.getWidth(), gameObject.getHeight());
            }
        }

        public void drawStatics() {
            for (GameObject gameObject : GridHandler.getInstance().getUpdatedStatics()) {
                gameObject.draw(staticGC);
            }
            GridHandler.getInstance().clearUpdatedStatics();
        }

        public void drawMovables() {
            for (GameObject gameObject : GridHandler.getInstance().getMovables()) {
                gameObject.draw(movingGC);
            }
        }
    }

    private class GameLoop extends AnimationTimer {
        long lastTime = System.currentTimeMillis();
        int frameCount = 0;
        int secondCount = 0;

        @Override public void handle(long currentNanoTime) {
            if (GameVariables.gameStatus == GameVariables.GameStatus.LOST) {
                this.stop();
                SceneHandler.getINSTANCE().gameOver();
                return;
            } else if (GameVariables.gameStatus == GameVariables.GameStatus.WON) {
                this.stop();
                SceneHandler.getINSTANCE().playerScore();
                return;
            }

            if (System.currentTimeMillis() - lastTime < 1000) {
                frameCount++;
            } else {
                lastTime += 1000;
                ++secondCount;
                System.out.println(secondCount + "\tFPS: " + frameCount);
                frameCount = 0;
            }

            healthLabel.textProperty().set(String.valueOf(GameVariables.playerTank1.getHealth()));
            enemyCountLabel.textProperty().set(String.valueOf(GameVariables.remainingTanks));
            stageNumberLabel.textProperty().set("Stage: " + GameVariables.stageNumber);

            gameRenderer.clearStatics();
            gameRenderer.drawStatics();
            gameRenderer.clearMovables();
            GridHandler.getInstance().updateFrame();
            gameRenderer.drawMovables();
        }
    }
}
