package ir.ac.kntu.gamecontroller;

import javafx.scene.input.KeyCode;

public interface InputManager {
    void handlePressedKeys(KeyCode keyCode);

    void handleReleasedKeys(KeyCode keyCode);
}
