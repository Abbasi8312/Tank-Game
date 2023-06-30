package ir.ac.kntu.gamecontroller;

import ir.ac.kntu.gamelogic.gameconstants.Direction;
import ir.ac.kntu.gamelogic.models.tanks.PlayerTank;
import javafx.scene.input.KeyCode;

public class PlayerController implements InputManager {
    private static final PlayerController INSTANCE = new PlayerController();

    private PlayerTank playerTank1;

    private PlayerController() {
    }

    public static PlayerController getInstance() {
        return INSTANCE;
    }

    private void move(PlayerTank playerTank, Direction direction) {
        playerTank.setDirection(direction);
        playerTank.setMoving(true);
    }

    private void stop(PlayerTank playerTank, Direction direction) {
        if (playerTank.getDirection() == direction) {
            playerTank.setMoving(false);
        }
    }

    public PlayerTank getPlayer1() {
        return playerTank1;
    }

    public void setPlayer1(PlayerTank playerTank1) {
        this.playerTank1 = playerTank1;
    }

    @Override public void handlePressedKeys(KeyCode keyCode) {
        switch (keyCode) {
            case LEFT -> move(playerTank1, Direction.LEFT);
            case RIGHT -> move(playerTank1, Direction.RIGHT);
            case UP -> move(playerTank1, Direction.UP);
            case DOWN -> move(playerTank1, Direction.DOWN);
            default -> move(playerTank1, Direction.NONE);
        }
    }

    @Override public void handleReleasedKeys(KeyCode keyCode) {
        switch (keyCode) {
            case LEFT -> stop(playerTank1, Direction.LEFT);
            case RIGHT -> stop(playerTank1, Direction.RIGHT);
            case UP -> stop(playerTank1, Direction.UP);
            case DOWN -> stop(playerTank1, Direction.DOWN);
            default -> stop(playerTank1, Direction.NONE);
        }
    }
}
