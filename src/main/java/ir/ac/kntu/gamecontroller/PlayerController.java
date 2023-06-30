package ir.ac.kntu.gamecontroller;

import ir.ac.kntu.gamelogic.gameconstants.Direction;
import ir.ac.kntu.gamelogic.models.tanks.PlayerTank;
import javafx.scene.input.KeyCode;

public class PlayerController implements InputManager {
    private static final PlayerController INSTANCE = new PlayerController();

    private static PlayerTank playerTank1;

    private PlayerController() {
    }

    public static PlayerController getInstance() {
        return INSTANCE;
    }

    private static void move(PlayerTank playerTank, Direction direction) {
        playerTank.setDirection(direction);
        playerTank.move();
    }

    public static PlayerTank getPlayer1() {
        return playerTank1;
    }

    public static void setPlayer1(PlayerTank playerTank1) {
        PlayerController.playerTank1 = playerTank1;
    }

    @Override public void handlePlayerMovements(KeyCode keyCode) {
        switch (keyCode) {
            case LEFT -> move(playerTank1, Direction.LEFT);
            case RIGHT -> move(playerTank1, Direction.RIGHT);
            case UP -> move(playerTank1, Direction.UP);
            case DOWN -> move(playerTank1, Direction.DOWN);
            default -> move(playerTank1, Direction.NONE);
        }
    }
}
