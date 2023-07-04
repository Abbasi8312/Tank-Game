package ir.ac.kntu.scenes;

import ir.ac.kntu.SceneHandler;
import ir.ac.kntu.gamelogic.gamevariables.GameVariables;
import ir.ac.kntu.gamelogic.services.PlayerHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ScoreBoard {
    private final BorderPane borderPane;

    private final Label hiScoreLabel;

    private final Label stageLabel;

    private final Label playerLabel;

    private final Label scoreLabel;

    private final HBox tank1Box;

    private final HBox tank2Box;

    private final HBox tank3Box;

    private final HBox tank4Box;

    private final HBox totalBox;

    public ScoreBoard(BorderPane borderPane) {
        this.borderPane = borderPane;

        hiScoreLabel = createLabel("HI-SCORE - " + PlayerHandler.getInstance().getHighScore());
        stageLabel = createLabel("STAGE - " + GameVariables.stageNumber);
        playerLabel = createLabel("I-PLAYER");
        playerLabel.setAlignment(Pos.CENTER_LEFT);
        scoreLabel = createLabel(String.valueOf(PlayerHandler.getInstance().getScore()));
        scoreLabel.setAlignment(Pos.CENTER_LEFT);
        tank1Box = createPlayerBox("Regular Enemy/down1", GameVariables.playerTank1.getDestroyed().getRegular());
        tank2Box = createPlayerBox("Armored Enemy/down1", GameVariables.playerTank1.getDestroyed().getArmored());
        tank3Box = createPlayerBox("Lucky Regular Enemy/down1",
                GameVariables.playerTank1.getDestroyed().getLuckyRegular());
        tank4Box = createPlayerBox("Lucky Armored Enemy/down1",
                GameVariables.playerTank1.getDestroyed().getLuckyArmored());
        totalBox = createTotalBox("TOTAL", GameVariables.playerTank1.getDestroyed().getTotal());
    }

    public static void transition() {
        if (GameVariables.gameStatus == GameVariables.GameStatus.WON) {
            SceneHandler.getINSTANCE().currentStage(GameVariables.stageNumber + 1);
        } else {
            SceneHandler.getINSTANCE().selectGameMode();
        }
        GameVariables.gameStatus = GameVariables.GameStatus.RUNNING;
    }

    public void updateScores() {
        VBox scoreLayout = new VBox(10);
        scoreLayout.setAlignment(Pos.CENTER);
        scoreLayout.setPadding(new Insets(20));
        scoreLayout.getChildren()
                .addAll(hiScoreLabel, stageLabel, playerLabel, scoreLabel, tank1Box, tank2Box, tank3Box, tank4Box,
                        totalBox);

        borderPane.setCenter(scoreLayout);
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-family: 'Courier New';" + "-fx-font-size: 16px;" + "-fx-text-fill: white;");
        label.setAlignment(Pos.CENTER);
        return label;
    }

    private HBox createPlayerBox(String tankImage, int score) {
        ImageView tankImageView = new ImageView(new Image("images/sprites/" + tankImage + ".png"));
        tankImageView.setFitWidth(25);
        tankImageView.setPreserveRatio(true);

        Label scoreLabel = new Label(String.valueOf(score));
        scoreLabel.setStyle("-fx-font-family: 'Courier New';" + "-fx-font-size: 16px;" + "-fx-text-fill: white;");
        scoreLabel.setAlignment(Pos.CENTER);

        HBox playerBox = new HBox(10);
        playerBox.setAlignment(Pos.CENTER_LEFT);
        playerBox.getChildren().addAll(tankImageView, scoreLabel);

        return playerBox;
    }

    private HBox createTotalBox(String label, int totalDestroyed) {
        Label totalLabel = new Label(label);
        totalLabel.setStyle("-fx-font-family: 'Courier New';" + "-fx-font-size: 16px;" + "-fx-text-fill: white;");
        totalLabel.setAlignment(Pos.CENTER);

        Label totalDestroyedLabel = new Label(String.valueOf(totalDestroyed));
        totalDestroyedLabel.setStyle(
                "-fx-font-family: 'Courier New';" + "-fx-font-size: 16px;" + "-fx-text-fill: white;");
        totalDestroyedLabel.setAlignment(Pos.CENTER);

        HBox totalBox = new HBox(10);
        totalBox.setAlignment(Pos.CENTER_LEFT);
        totalBox.getChildren().addAll(totalLabel, totalDestroyedLabel);

        return totalBox;
    }
}
