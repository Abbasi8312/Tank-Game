package ir.ac.kntu.logic.model;

import ir.ac.kntu.logic.CollisionHandler;
import ir.ac.kntu.logic.UpdateBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class Board {
    private final List<GameObject> gameObjects;

    private final List<Movable> movables;

    private final CollisionHandler collisionHandler;

    private final ThreadPoolExecutor executor;

    public Board(ThreadPoolExecutor executor) {
        this.executor = executor;
        gameObjects = new ArrayList<>();
        collisionHandler = new CollisionHandler(gameObjects);
        movables = new ArrayList<>();
        gameObjects.add(new RegularTank(50, 100, collisionHandler, 1));
        gameObjects.add(new RegularTank(75, 150, collisionHandler, 2));
        gameObjects.add(new RegularTank(125, 150, collisionHandler, 3));
    }

    public void update() {
        executor.submit(new UpdateBoard(gameObjects, executor));
    }

    public List<GameObject> getGameObjects() {
        return new ArrayList<>(gameObjects);
    }
}
