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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Game {

    private final BorderPane borderPane;
    private final AnimationTimer animationTimer;
    private final GameRenderer gameRenderer;
    private final VBox uiBox;
    private final Label infoLabel;
    private final ImageView playerImageView;
    private final Label healthLabel;

    public Game(BorderPane borderPane) {
        this.borderPane = borderPane;

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

        uiBox = new VBox(10);
        uiBox.setPadding(new Insets(10));
        uiBox.setAlignment(Pos.CENTER);

        playerImageView = new ImageView(new Image("images/sprites/Regular Player 1/down1.png"));
        playerImageView.setFitWidth(50);
        playerImageView.setPreserveRatio(true);

        healthLabel = new Label();
        healthLabel.setFont(Font.font(14));

        uiBox.getChildren().addAll(playerImageView, healthLabel);

        borderPane.setRight(uiBox);

        infoLabel.setAlignment(Pos.CENTER);
        borderPane.setBottom(infoLabel);
    }

    public void start() {
        animationTimer.start();
    }

    private static class GameRenderer {
        private final GraphicsContext staticGC;
        private final GraphicsContext movingGC;

        public GameRenderer(GraphicsContext staticGC, GraphicsContext movingGC) {
            this.staticGC = staticGC;
            this.movingGC = movingGC;
        }

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
            if (GameVariables.gameStatus == GameVariables.GameStatus.ENDED) {
                stop();
                SceneHandler.getINSTANCE().gameOver();
            }

            if (System.currentTimeMillis() - lastTime < 1000) {
                frameCount++;
            } else {
                lastTime += 1000;
                ++secondCount;
                System.out.println(secondCount + "\tFPS: " + frameCount);
                frameCount = 0;
            }

            gameRenderer.clearStatics();
            gameRenderer.drawStatics();
            gameRenderer.clearMovables();
            GridHandler.getInstance().updateFrame();
            gameRenderer.drawMovables();
        }
    }
}
