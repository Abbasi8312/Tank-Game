package ir.ac.kntu.gamecontroller;

import ir.ac.kntu.gamelogic.gamevariables.GameVariables;
import ir.ac.kntu.gamelogic.models.Direction;
import ir.ac.kntu.gamelogic.models.tanks.PlayerTank;
import javafx.scene.input.KeyCode;

import static ir.ac.kntu.gamecontroller.KeyMap.*;
import static ir.ac.kntu.gamelogic.gamevariables.GameVariables.playerTank1;

public class PlayerController implements InputManager {
    private static final PlayerController INSTANCE = new PlayerController();

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

    @Override public void handlePressedKeys(KeyCode keyCode) {
        if (GameVariables.gameStatus == GameVariables.GameStatus.PAUSED) {
            return;
        }
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
        if (GameVariables.gameStatus == GameVariables.GameStatus.PAUSED) {
            return;
        }
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
