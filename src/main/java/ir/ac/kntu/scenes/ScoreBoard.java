package ir.ac.kntu.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ScoreBoard {
    private final BorderPane borderPane;

    private final Label hiScoreLabel;
    private final Label player1Label;
    private final Label player2Label;
    private final Label player1ScoreLabel;
    private final Label player2ScoreLabel;
    private final Label player1DestroyedLabel;
    private final Label player2DestroyedLabel;
    private final Label totalDestroyedLabel;

    public ScoreBoard(BorderPane borderPane) {
        this.borderPane = borderPane;

        hiScoreLabel = createLabel("HI-SCORE");
        player1Label = createLabel("I-Player");
        player2Label = createLabel("II-Player");
        player1ScoreLabel = createLabel("");
        player2ScoreLabel = createLabel("");
        player1DestroyedLabel = createLabel("");
        player2DestroyedLabel = createLabel("");
        totalDestroyedLabel = createLabel("");

        updateScores();

        VBox scoreboard = new VBox(10);
        scoreboard.setAlignment(Pos.CENTER);
        scoreboard.getChildren()
                .addAll(hiScoreLabel, player1Label, player1ScoreLabel, player1DestroyedLabel, player2Label,
                        player2ScoreLabel, player2DestroyedLabel, totalDestroyedLabel);

        borderPane.setTop(scoreboard);
        BorderPane.setMargin(scoreboard, new Insets(20, 0, 0, 0));
    }

    public void updateScores() {

        player1ScoreLabel.setText("Score: ");
        player2ScoreLabel.setText("Score: ");
        player1DestroyedLabel.setText("Destroyed: ");
        player2DestroyedLabel.setText("Destroyed: ");
        totalDestroyedLabel.setText("Total Destroyed: ");
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font(16));
        label.setTextFill(Color.WHITE);
        return label;
    }
}
