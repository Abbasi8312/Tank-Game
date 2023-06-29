package ir.ac.kntu.gamecontroller;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class EventHandler {
    private static final EventHandler instance = new EventHandler();

    private static boolean isMoving = false;

    private EventHandler() {
    }

    public static EventHandler getInstance() {
        return instance;
    }

    public void attachEventHandlers(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            KeyCode code = keyEvent.getCode();
            PlayerController.getInstance().handlePlayerMovements(code);
        });
        scene.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
        });
    }
}
