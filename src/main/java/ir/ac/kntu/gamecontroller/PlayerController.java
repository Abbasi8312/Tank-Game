package ir.ac.kntu.gamecontroller;

import ir.ac.kntu.gamelogic.models.Direction;
import ir.ac.kntu.gamelogic.models.tanks.PlayerTank;
import javafx.scene.input.KeyCode;

import static ir.ac.kntu.gamecontroller.KeyMap.*;

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
        if (keyCode == player1Left) {
            move(playerTank1, Direction.LEFT);
        } else if (keyCode == player1Right) {
            move(playerTank1, Direction.RIGHT);
        } else if (keyCode == player1Up) {
            move(playerTank1, Direction.UP);
        } else if (keyCode == player1Down) {
            move(playerTank1, Direction.DOWN);
        } else if (keyCode == player1Fire) {
            playerTank1.setFiring(true);
        }
    }

    @Override public void handleReleasedKeys(KeyCode keyCode) {
        if (keyCode == player1Left) {
            stop(playerTank1, Direction.LEFT);
        } else if (keyCode == player1Right) {
            stop(playerTank1, Direction.RIGHT);
        } else if (keyCode == player1Up) {
            stop(playerTank1, Direction.UP);
        } else if (keyCode == player1Down) {
            stop(playerTank1, Direction.DOWN);
        } else if (keyCode == player1Fire) {
            playerTank1.setFiring(false);
        }
    }
}
