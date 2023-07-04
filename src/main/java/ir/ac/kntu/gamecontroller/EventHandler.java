package ir.ac.kntu.gamecontroller;

import ir.ac.kntu.gamelogic.gamevariables.GameVariables;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class EventHandler {
    private static final EventHandler INSTANCE = new EventHandler();

    private EventHandler() {
    }

    public static EventHandler getInstance() {
        return INSTANCE;
    }

    public void attachEventHandlers(Scene scene) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            KeyCode code = keyEvent.getCode();
            PlayerController.getInstance().handlePressedKeys(code);
        });
        scene.addEventFilter(KeyEvent.KEY_RELEASED, keyEvent -> {
            KeyCode code = keyEvent.getCode();
            PlayerController.getInstance().handleReleasedKeys(code);
        });
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyMap.pause) {
                if (GameVariables.lastStatus == null) {
                    GameVariables.lastStatus = GameVariables.gameStatus;
                    GameVariables.gameStatus = GameVariables.GameStatus.PAUSED;
                } else {
                    GameVariables.gameStatus = GameVariables.lastStatus;
                    GameVariables.lastStatus = null;
                }
            }
        });
    }
}
