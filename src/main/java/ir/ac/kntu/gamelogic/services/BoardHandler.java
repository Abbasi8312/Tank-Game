package ir.ac.kntu.gamelogic.services;

import ir.ac.kntu.gamelogic.Board;
import ir.ac.kntu.gamelogic.gameconstants.GameConstants;
import ir.ac.kntu.gamelogic.models.Flag;
import ir.ac.kntu.gamelogic.models.GameObject;
import ir.ac.kntu.gamelogic.models.Unit;
import ir.ac.kntu.gamelogic.models.interfaces.Movable;
import ir.ac.kntu.gamelogic.models.tanks.PlayerTank;
import ir.ac.kntu.gamelogic.models.tanks.RegularTank;
import ir.ac.kntu.gamelogic.models.walls.Border;
import ir.ac.kntu.gamelogic.models.walls.BrickWall;

import java.util.ArrayList;
import java.util.List;

public class BoardHandler {
    private final static BoardHandler INSTANCE = new BoardHandler();

    private final Board board;

    private final List<GameObject> updatedStatics;

    private final List<GameObject> movables;

    private final List<GameObject> addQueue;

    private final List<GameObject> removeQueue;

    private BoardHandler() {
        board = new Board();
        updatedStatics = new ArrayList<>();
        movables = new ArrayList<>();
        addQueue = new ArrayList<>();
        removeQueue = new ArrayList<>();
    }

    public static BoardHandler getInstance() {
        return INSTANCE;
    }

    public void init() {
//        addGameObject(new RegularTank(100, 100));
//        addGameObject(new RegularTank(400, 150));
//        addGameObject(new RegularTank(200, 400));
        for (int i = 0; i < GameConstants.GAME_WIDTH / GameConstants.TILE_SIZE; i++) {
            addGameObject(new Border(GameConstants.TILE_SIZE * (i + 0.5), GameConstants.TILE_SIZE * 0.5));
            addGameObject(new Border(GameConstants.TILE_SIZE * (i + 0.5),
                    GameConstants.GAME_HEIGHT - GameConstants.TILE_SIZE * 0.5));
        }
        for (int i = 1; i < GameConstants.GAME_HEIGHT / GameConstants.TILE_SIZE - 1; i++) {
            addGameObject(new Border(GameConstants.TILE_SIZE * 0.5, GameConstants.TILE_SIZE * (i + 0.5)));
            addGameObject(new Border(GameConstants.GAME_WIDTH - GameConstants.TILE_SIZE * 0.5,
                    GameConstants.TILE_SIZE * (i + 0.5)));
        }
        addGameObject(new PlayerTank(500, 400));
        addGameObject(new Flag(300, 400));
        addGameObject(new BrickWall(250, 250));
        addGameObject(new BrickWall(250 + GameConstants.TILE_SIZE, 250));
        addGameObject(new BrickWall(250, 250 + GameConstants.TILE_SIZE));
        addGameObject(new BrickWall(250 + GameConstants.TILE_SIZE, 250 + GameConstants.TILE_SIZE));
    }

    public void updateFrame() {
        processQueue();
        for (GameObject gameObject : movables) {
            ((Unit) gameObject).update();
        }
    }

    public void addGameObject(GameObject gameObject) {
        addQueue.add(gameObject);
    }

    private void processQueue() {
        List<GameObject> added = new ArrayList<>();
        List<GameObject> removed = new ArrayList<>();
        for (GameObject gameObject : addQueue) {
            board.addGameObject(gameObject);
            if (gameObject instanceof Movable) {
                movables.add(gameObject);
            } else {
                updatedStatics.add(gameObject);
            }
            added.add(gameObject);
        }
        for (GameObject gameObject : removeQueue) {
            board.removeGameObject(gameObject);
            if (gameObject instanceof Movable) {
                movables.remove(gameObject);
            } else if (gameObject instanceof BrickWall) {
                updatedStatics.add(gameObject);
            }
            removed.add(gameObject);
        }
        addQueue.removeAll(added);
        removeQueue.removeAll(removed);
    }

    public void removeGameObject(GameObject gameObject) {
        removeQueue.add(gameObject);
    }

    public List<GameObject> getUpdatedStatics() {
        return new ArrayList<>(updatedStatics);
    }

    public void updateStatic(GameObject gameObject) {
        if (!(gameObject instanceof Movable)) {
            updatedStatics.add(gameObject);
        }
    }

    public void clearUpdatedStatics() {
        updatedStatics.clear();
    }

    public List<GameObject> getMovables() {
        return new ArrayList<>(movables);
    }

    public List<GameObject> getGameObjects() {
        return board.getGameObjects();
    }
}
