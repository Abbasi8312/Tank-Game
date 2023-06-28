package ir.ac.kntu.logic;

import ir.ac.kntu.logic.model.GameConstants;
import ir.ac.kntu.logic.model.GameObject;
import ir.ac.kntu.logic.model.Movable;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class UpdateBoard implements Runnable {
    private final List<GameObject> gameObjects;

    private final ThreadPoolExecutor executor;

    public UpdateBoard(List<GameObject> gameObjects, ThreadPoolExecutor executor) {
        this.gameObjects = gameObjects;
        this.executor = executor;
    }

    @Override public void run() {
        while (GameConstants.gameStatus != GameConstants.GameStatus.STOPPED) {
            for (GameObject gameObject : gameObjects) {
                if (gameObject instanceof Movable movable) {
                    executor.submit(movable);
                }
            }
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
