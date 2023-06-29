package ir.ac.kntu.gamecontroller;

import ir.ac.kntu.logic.gameconstants.Direction;
import ir.ac.kntu.logic.model.Player;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

public class PlayerController implements InputManager {
    private static final PlayerController instance = new PlayerController();

    private static Player player1;

    private PlayerController() {
    }

    public static PlayerController getInstance() {
        return instance;
    }

    private static void move(Player player, Direction direction) {
        player.setDirection(direction);
        player.move();
    }

    public static Player getPlayer1() {
        return player1;
    }

    public static void setPlayer1(Player player1) {
        PlayerController.player1 = player1;
    }

    @Override public void handlePlayerMovements(KeyCode keyCode) {
        switch (keyCode) {
            case LEFT -> move(player1, Direction.LEFT);
            case RIGHT -> move(player1, Direction.RIGHT);
            case UP -> move(player1, Direction.UP);
            case DOWN -> move(player1, Direction.DOWN);
        }
    }
}
