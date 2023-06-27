package ir.ac.kntu.graphics;

import ir.ac.kntu.logic.model.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DrawObjects {
    private final List<GameObject> gameObjects;

    private final List<Movable> movables;

    private final GraphicsContext graphicsContext;

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

    public void draw() {
        Timer timer = new Timer();
        timer.schedule(new UpdateScreen(), 0, GameConstants.FRAME_LENGTH);
    }

    private void update() {
        graphicsContext.clearRect(0, 0, GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof RegularTank regularTank) {
                graphicsContext.setFill(Color.RED);
                graphicsContext.fillRect(gameObject.getX() - gameObject.getWidth() / 2,
                        gameObject.getY() - gameObject.getHeight() / 2, gameObject.getWidth(), gameObject.getHeight());
            }
        }
    }

    private class UpdateScreen extends TimerTask {
        @Override public void run() {
            update();
        }
    }
}
