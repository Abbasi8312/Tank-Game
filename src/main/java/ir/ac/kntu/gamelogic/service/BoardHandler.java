package ir.ac.kntu.gamelogic.service;

import ir.ac.kntu.gamelogic.gameconstants.GameConstants;
import ir.ac.kntu.gamelogic.model.Board;
import ir.ac.kntu.gamelogic.model.GameObject;
import ir.ac.kntu.gamelogic.model.interfaces.Movable;
import ir.ac.kntu.gamelogic.model.tank.PlayerTank;
import ir.ac.kntu.gamelogic.model.tank.RegularTank;
import ir.ac.kntu.gamelogic.model.wall.Border;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class BoardHandler {
    private final static BoardHandler INSTANCE = new BoardHandler();

    private final Board board;

    private final List<GameObject> statics;

    private final List<GameObject> updatedStatics;

    private final List<GameObject> movables;

    private BoardHandler() {
        board = new Board();
        statics = new ArrayList<>();
        updatedStatics = new ArrayList<>();
        movables = new ArrayList<>();
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
        TimerWrapper.getInstance().schedule(new AIMove(), 0, GameConstants.FRAME_LENGTH);
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
        List<GameObject> tmp = new ArrayList<>(updatedStatics);
        statics.addAll(updatedStatics);
        updatedStatics.clear();
        return tmp;
    }

    public List<GameObject> getMovables() {
        return new ArrayList<>(movables);
    }

    public List<GameObject> getGameObjects() {
        return board.getGameObjects();
    }

    private class AIMove extends TimerTask {
        private final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        @Override public void run() {
            for (GameObject gameObject : movables) {
                if (!(gameObject instanceof PlayerTank)) {
                    executor.submit((Runnable) gameObject);
                }
            }
        }
    }
}
