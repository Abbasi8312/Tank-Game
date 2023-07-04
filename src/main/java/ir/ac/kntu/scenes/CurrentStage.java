package ir.ac.kntu.scenes;

import ir.ac.kntu.SceneHandler;
import ir.ac.kntu.gamelogic.gamevariables.GameVariables;
import ir.ac.kntu.gamelogic.services.GridHandler;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class CurrentStage {
    private final BorderPane rootPane;

    private final int currentStage;

    public CurrentStage(BorderPane rootPane, int currentStage) {
        this.rootPane = rootPane;
        this.currentStage = currentStage;
    }

    public void start() {
        Label label = new Label("Stage - " + currentStage);
        label.setStyle("-fx-font-family: 'Courier New';" + "-fx-font-size: 75px;" + "-fx-text-fill: white;");
        label.setAlignment(Pos.CENTER);
        rootPane.setCenter(label);

        GameVariables.stageNumber = currentStage;
        GameVariables.remainingTanks = 6 + currentStage * 4;
        GameVariables.gameStatus = GameVariables.GameStatus.PAUSED;
        GridHandler.getInstance().init();
        GameVariables.playerTank1.reset();

        PauseTransition delay = new PauseTransition(Duration.seconds(5));

        delay.setOnFinished(e -> {
            GameVariables.gameStatus = GameVariables.GameStatus.RUNNING;
            SceneHandler.getINSTANCE().game();
        });
        delay.play();
    }
}
