package ir.ac.kntu.gamelogic.services;

import ir.ac.kntu.gamelogic.Grid;
import ir.ac.kntu.gamelogic.gamevariables.GameVariables;
import ir.ac.kntu.gamelogic.models.GameObject;
import ir.ac.kntu.gamelogic.models.Unit;
import ir.ac.kntu.gamelogic.models.interfaces.Movable;
import ir.ac.kntu.gamelogic.models.tanks.EnemyTank;
import ir.ac.kntu.gamelogic.models.terrains.Border;
import ir.ac.kntu.gamelogic.models.terrains.Spawner;

import java.util.*;

public class GridHandler {
    private static final GridHandler INSTANCE = new GridHandler();

    private final Grid grid;

    private final List<GameObject> updatedStatics;

    private final List<GameObject> movables;

    private final List<GameObject> addQueue;

    private final List<GameObject> removeQueue;

    private final List<Spawner> spawners;

    private GridHandler() {
        grid = new Grid();
        updatedStatics = new ArrayList<>();
        movables = new ArrayList<>();
        addQueue = new ArrayList<>();
        removeQueue = new ArrayList<>();
        spawners = new ArrayList<>();
    }

    public static GridHandler getInstance() {
        return INSTANCE;
    }

    public void init() {
        clear();
        addQueue.addAll(DataHandler.getInstance().loadGameObjectsFromFile());
        if (GameVariables.playerTank1 != null) {
            addQueue.add(GameVariables.playerTank1);
        }
        initSpawners();
        for (int i = 0; i < GameVariables.gameWidth / GameVariables.TILE_SIZE; i++) {
            addGameObject(new Border(GameVariables.TILE_SIZE * (i + 0.5), GameVariables.TILE_SIZE * 0.5));
            addGameObject(new Border(GameVariables.TILE_SIZE * (i + 0.5),
                    GameVariables.gameHeight - GameVariables.TILE_SIZE * 0.5));
        }
        for (int i = 1; i < GameVariables.gameHeight / GameVariables.TILE_SIZE - 1; i++) {
            addGameObject(new Border(GameVariables.TILE_SIZE * 0.5, GameVariables.TILE_SIZE * (i + 0.5)));
            addGameObject(new Border(GameVariables.gameWidth - GameVariables.TILE_SIZE * 0.5,
                    GameVariables.TILE_SIZE * (i + 0.5)));
        }
    }

    public void clear() {
        grid.clear();
        updatedStatics.clear();
        movables.clear();
        addQueue.clear();
        removeQueue.clear();
        spawners.clear();
    }

    private void initSpawners() {
        for (GameObject gameObject : addQueue) {
            if (gameObject instanceof Spawner spawner) {
                spawners.add(spawner);
            }
        }
        if (spawners.size() == 0) {
            spawners.add(new Spawner(GameVariables.TILE_SIZE * 1.5, GameVariables.TILE_SIZE * 1.5));
        }
        addQueue.removeAll(spawners);
    }

    public void updateFrame() {
        processQueue();
        if (GameVariables.gameStatus == GameVariables.GameStatus.PAUSED) {
            return;
        }
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
            grid.addGameObject(gameObject);
            if (gameObject instanceof Movable) {
                movables.add(gameObject);
            } else {
                updatedStatics.add(gameObject);
            }
            added.add(gameObject);
        }
        for (GameObject gameObject : removeQueue) {
            grid.removeGameObject(gameObject);
            if (gameObject instanceof Movable) {
                movables.remove(gameObject);
            } else {
                updatedStatics.add(gameObject);
            }
            removed.add(gameObject);
            if (gameObject instanceof EnemyTank) {
                handleEnemyTank();
            }
        }
        addQueue.removeAll(added);
        removeQueue.removeAll(removed);
    }

    private void handleEnemyTank() {
        if (GameVariables.remainingTanks > 0) {
            Spawner spawner;
            do {
                spawner = spawners.get(new Random().nextInt(spawners.size()));
            } while (!spawner.spawn());
            return;
        }
        for (GameObject gameObject : movables) {
            if (gameObject instanceof EnemyTank) {
                return;
            }
        }
        GameVariables.gameStatus = GameVariables.GameStatus.PAUSED;
        new Timer().schedule(new TimerTask() {
            @Override public void run() {
                GameVariables.gameStatus = GameVariables.GameStatus.WON;
            }
        }, 250);
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
        return grid.getGameObjects();
    }
}
