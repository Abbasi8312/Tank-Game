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
        gameObjects.add(new RegularTank(100, 100, collisionHandler, 1));
        gameObjects.add(new RegularTank(400, 150, collisionHandler, 2));
        gameObjects.add(new RegularTank(200, 400, collisionHandler, 3));
        for (int i = 0; i < GameConstants.GAME_WIDTH / GameConstants.TILE_SIZE; i++) {
            gameObjects.add(
                    new BorderWall(GameConstants.TILE_SIZE * (i + 0.5), GameConstants.TILE_SIZE * 0.5, collisionHandler,
                            gameObjects.size()));
            gameObjects.add(new BorderWall(GameConstants.TILE_SIZE * (i + 0.5),
                    GameConstants.GAME_HEIGHT - GameConstants.TILE_SIZE * 0.5, collisionHandler, gameObjects.size()));
        }
        for (int i = 1; i < GameConstants.GAME_HEIGHT / GameConstants.TILE_SIZE - 1; i++) {
            gameObjects.add(
                    new BorderWall(GameConstants.TILE_SIZE * 0.5, GameConstants.TILE_SIZE * (i + 0.5), collisionHandler,
                            gameObjects.size()));
            gameObjects.add(new BorderWall(GameConstants.GAME_WIDTH - GameConstants.TILE_SIZE * 0.5,
                    GameConstants.TILE_SIZE * (i + 0.5), collisionHandler, gameObjects.size()));
        }
        gameObjects.add(new Player(500, 400, collisionHandler, gameObjects.size()));
    }

    public void update() {
        executor.submit(new UpdateBoard(gameObjects, executor));
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }
}
