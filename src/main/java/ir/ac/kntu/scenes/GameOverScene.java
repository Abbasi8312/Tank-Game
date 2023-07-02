package ir.ac.kntu.scenes;

import ir.ac.kntu.gamelogic.services.PlayerHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class GameOverScene {
    private final BorderPane rootPane;

    public GameOverScene(BorderPane rootPane) {
        this.rootPane = rootPane;
    }

    public void start() {
        Label label = new Label("GAME OVER");
        label.setFont(new Font(100));
        label.setAlignment(Pos.CENTER);
        rootPane.setCenter(label);

        PlayerHandler.getINSTANCE().addGameCount();
        PlayerHandler.getINSTANCE().savePlayer();
    }
}
