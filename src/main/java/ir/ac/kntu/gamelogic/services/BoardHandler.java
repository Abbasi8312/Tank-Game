package ir.ac.kntu.gamelogic.services;

import ir.ac.kntu.gamelogic.gameconstants.GameConstants;
import ir.ac.kntu.gamelogic.models.Board;
import ir.ac.kntu.gamelogic.models.GameObject;
import ir.ac.kntu.gamelogic.models.interfaces.Movable;
import ir.ac.kntu.gamelogic.models.tanks.PlayerTank;
import ir.ac.kntu.gamelogic.models.tanks.RegularTank;
import ir.ac.kntu.gamelogic.models.walls.Border;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class BoardHandler {
    private final static BoardHandler INSTANCE = new BoardHandler();

    private final Board board;

    private final List<GameObject> statics;

    private final List<GameObject> updatedStatics;

    private final List<GameObject> movables;

    private final ThreadPoolExecutor executor;

    private BoardHandler() {
        board = new Board();
        statics = new ArrayList<>();
        updatedStatics = new ArrayList<>();
        movables = new ArrayList<>();
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
    }

    public static BoardHandler getInstance() {
        return INSTANCE;
    }

    public void init() {
        addGameObject(new RegularTank(100, 100));
        addGameObject(new RegularTank(400, 150));
        addGameObject(new RegularTank(200, 400));
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
    }

    public void updateFrame() {
        for (GameObject gameObject : movables) {
            ((Movable) gameObject).move();
        }
    }

    public void addGameObject(GameObject gameObject) {
        board.addGameObject(gameObject);
        if (gameObject instanceof Movable) {
            movables.add(gameObject);
        } else {
            updatedStatics.add(gameObject);
        }
    }

    public List<GameObject> getStatics() {
        return new ArrayList<>(statics);
    }

    public List<GameObject> getUpdatedStatics() {
        return new ArrayList<>(updatedStatics);
    }

    public void clearUpdatedStatics() {
        statics.addAll(updatedStatics);
        updatedStatics.clear();
    }

    public List<GameObject> getMovables() {
        return new ArrayList<>(movables);
    }

    public List<GameObject> getGameObjects() {
        return board.getGameObjects();
    }
}
