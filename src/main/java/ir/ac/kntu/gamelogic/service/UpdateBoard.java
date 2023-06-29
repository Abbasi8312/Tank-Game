package ir.ac.kntu.gamelogic.service;

import ir.ac.kntu.gamelogic.model.tank.EnemyTank;
import ir.ac.kntu.gamelogic.gameconstants.GameConstants;
import ir.ac.kntu.gamelogic.model.GameObject;

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
                if (gameObject instanceof EnemyTank enemyTank) {
                    executor.submit(enemyTank);
                }
            }
            try {
                Thread.sleep(GameConstants.FRAME_LENGTH);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
