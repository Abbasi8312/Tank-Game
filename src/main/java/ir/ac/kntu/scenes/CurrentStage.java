package ir.ac.kntu.scenes;

import ir.ac.kntu.gamelogic.services.PlayerHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class CurrentStage {
    private final BorderPane rootPane;

    private final int currentStage;

    public CurrentStage(BorderPane rootPane, int currentStage) {
        this.rootPane = rootPane;
        this.currentStage = currentStage;
    }

    public void start() {
        Label label = new Label("Stage - " + currentStage);
        label.setStyle("-fx-font-family: 'Courier New';" + "-fx-font-size: 100px;" + "-fx-text-fill: white;");
        label.setAlignment(Pos.CENTER);
        rootPane.setCenter(label);
    }
}
