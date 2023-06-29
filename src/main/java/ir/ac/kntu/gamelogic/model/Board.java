package ir.ac.kntu.gamelogic.model;

import ir.ac.kntu.gamelogic.model.interfaces.Movable;
import ir.ac.kntu.gamelogic.service.CollisionHandler;
import ir.ac.kntu.gamelogic.service.UpdateBoard;
import ir.ac.kntu.gamelogic.gameconstants.GameConstants;
import ir.ac.kntu.gamelogic.model.tank.PlayerTank;
import ir.ac.kntu.gamelogic.model.tank.RegularTank;
import ir.ac.kntu.gamelogic.model.wall.Border;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class Board {
    private final List<GameObject> gameObjects;

    private final List<GameObject> movables;

    private final CollisionHandler collisionHandler;

    private final ThreadPoolExecutor executor;

    public Board(ThreadPoolExecutor executor) {
        this.executor = executor;
        gameObjects = new ArrayList<>();
        collisionHandler = new CollisionHandler(gameObjects);
        movables = new ArrayList<>();
        gameObjects.add(new RegularTank(100, 100, collisionHandler));
        gameObjects.add(new RegularTank(400, 150, collisionHandler));
        gameObjects.add(new RegularTank(200, 400, collisionHandler));
        for (int i = 0; i < GameConstants.GAME_WIDTH / GameConstants.TILE_SIZE; i++) {
            gameObjects.add(
                    new Border(GameConstants.TILE_SIZE * (i + 0.5), GameConstants.TILE_SIZE * 0.5, collisionHandler));
            gameObjects.add(new Border(GameConstants.TILE_SIZE * (i + 0.5),
                    GameConstants.GAME_HEIGHT - GameConstants.TILE_SIZE * 0.5, collisionHandler));
        }
        for (int i = 1; i < GameConstants.GAME_HEIGHT / GameConstants.TILE_SIZE - 1; i++) {
            gameObjects.add(
                    new Border(GameConstants.TILE_SIZE * 0.5, GameConstants.TILE_SIZE * (i + 0.5), collisionHandler));
            gameObjects.add(new Border(GameConstants.GAME_WIDTH - GameConstants.TILE_SIZE * 0.5,
                    GameConstants.TILE_SIZE * (i + 0.5), collisionHandler));
        }
        gameObjects.add(new PlayerTank(500, 400, collisionHandler));
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof Movable) {
                movables.add(gameObject);
            }
        }
    }

    public void update() {
        executor.submit(new UpdateBoard(gameObjects, executor));
    }

    public List<GameObject> getGameObjects() {
        return new ArrayList<>(gameObjects);
    }

    public List<GameObject> getMovables() {
        return new ArrayList<>(movables);
    }
}
