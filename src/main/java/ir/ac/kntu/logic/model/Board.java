package ir.ac.kntu.logic.model;

import ir.ac.kntu.logic.CollisionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Board {
    private final List<GameObject> gameObjects;

    private final CollisionHandler collisionHandler;

    public Board() {
        gameObjects = new ArrayList<>();
        collisionHandler = new CollisionHandler(gameObjects);
        gameObjects.add(new RegularTank(50, 100, collisionHandler, 1));
        gameObjects.add(new RegularTank(100, 200, collisionHandler, 2));
    }

    public void start() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute((Runnable) gameObjects.get(0));
        executor.execute((Runnable) gameObjects.get(1));
    }
}
