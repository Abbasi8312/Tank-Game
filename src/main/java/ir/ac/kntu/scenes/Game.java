package ir.ac.kntu.scenes;

import ir.ac.kntu.gamelogic.gameconstants.GameConstants;
import ir.ac.kntu.gamelogic.models.GameObject;
import ir.ac.kntu.gamelogic.services.BoardHandler;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Game {
    private final GraphicsContext staticGC;

    private final GraphicsContext movingGC;

    public Game(GraphicsContext staticGC, GraphicsContext movingGC) {
        this.staticGC = staticGC;
        this.movingGC = movingGC;
        staticGC.fillRect(0, 0, GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);
    }

    private void clearStatics() {
        for (GameObject gameObject : BoardHandler.getInstance().getUpdatedStatics()) {
            staticGC.setFill(Color.BLACK);
            staticGC.fillRect(gameObject.getX() - gameObject.getWidth() / 2,
                    gameObject.getY() - gameObject.getHeight() / 2, gameObject.getWidth(), gameObject.getHeight());
        }
    }

    private void clearMovables() {
        for (GameObject gameObject : BoardHandler.getInstance().getMovables()) {
            movingGC.clearRect(gameObject.getX() - gameObject.getWidth() / 2,
                    gameObject.getY() - gameObject.getHeight() / 2, gameObject.getWidth(), gameObject.getHeight());
        }
    }

    private void drawStatics() {
        for (GameObject gameObject : BoardHandler.getInstance().getUpdatedStatics()) {
            gameObject.draw(staticGC);
        }
        BoardHandler.getInstance().clearUpdatedStatics();
    }

    private void drawMovables() {
        for (GameObject gameObject : BoardHandler.getInstance().getMovables()) {
            gameObject.draw(movingGC);
        }
    }

    public void start() {
        new AnimationTimer() {
            long lastTime = System.currentTimeMillis();

            int frameCount = 0;

            int secondCount = 0;

            @Override public void handle(long currentNanoTime) {
                if (System.currentTimeMillis() - lastTime < 1000) {
                    frameCount++;
                } else {
                    lastTime += 1000;
                    ++secondCount;
                    System.out.println(secondCount + "\tFPS: " + frameCount);
                    frameCount = 0;
                }

                clearStatics();
                drawStatics();
                clearMovables();
                BoardHandler.getInstance().updateFrame();
                drawMovables();
            }
        }.start();
    }
}
