package ir.ac.kntu.scenes;

import ir.ac.kntu.SceneHandler;
import ir.ac.kntu.gamelogic.services.PlayerHandler;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class GameOver {
    private final BorderPane rootPane;

    public GameOver(BorderPane rootPane) {
        this.rootPane = rootPane;
    }

    public void start() {
        Label label = new Label("GAME OVER");
        label.setStyle("-fx-font-family: 'Courier New';" + "-fx-font-size: 100px;" + "-fx-text-fill: white;");
        label.setAlignment(Pos.CENTER);
        rootPane.setCenter(label);

        PlayerHandler.getInstance().addGameCount();
        PlayerHandler.getInstance().updatePlayer();

        PauseTransition delay = new PauseTransition(Duration.seconds(2));

        delay.setOnFinished(e -> SceneHandler.getINSTANCE().playerScore());
        delay.play();
    }
}
