package ir.ac.kntu.graphics;

import ir.ac.kntu.logic.model.Board;
import ir.ac.kntu.logic.model.GameConstants;
import ir.ac.kntu.logic.model.GameObject;
import ir.ac.kntu.logic.model.Movable;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class DrawObjects {
    private final List<GameObject> gameObjects;

    private final List<Movable> movables;

    private final GraphicsContext graphicsContext;

    private int frameIndex = 0;

    public DrawObjects(Board board, GraphicsContext graphicsContext) {
        gameObjects = board.getGameObjects();
        movables = new ArrayList<>();
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof Movable movable) {
                movables.add(movable);
            }
        }
        this.graphicsContext = graphicsContext;
    }

    private void draw() {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);
        for (GameObject gameObject : gameObjects) {
            gameObject.draw(graphicsContext, ++frameIndex);
        }
        graphicsContext.fillRect(0, 0, 10, 100);
    }

    public void start() {
        new AnimationTimer() {
            long lastNanoTime = System.nanoTime();
            @Override public void handle(long currentNanoTime) {
                double elapsedTime = (currentNanoTime - lastNanoTime) / 1_000_000.0;
                if (elapsedTime >= GameConstants.FRAME_LENGTH) {
                    lastNanoTime = currentNanoTime;
                    draw();
                }
            }
        }.start();
    }
}
