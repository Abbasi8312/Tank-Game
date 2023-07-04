package ir.ac.kntu.scenes;

import ir.ac.kntu.SceneHandler;
import ir.ac.kntu.gamelogic.gamevariables.GameVariables;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static ir.ac.kntu.scenes.RegionStyleManager.setNormalStyle;
import static ir.ac.kntu.scenes.RegionStyleManager.setupFocusAndMouseEvents;

public class SelectStage {
    private final BorderPane borderPane;

    public SelectStage(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public void start() {
        setupBackground();
        VBox stageList = createStageList();
        setupStageList(stageList);
        ScrollPane scrollPane = createScrollPane(stageList);
        setupScrollPane(scrollPane);
        setNodesInBorderPane(scrollPane);
    }

    private void setupBackground() {
        borderPane.setBackground(Background.EMPTY);
    }

    private VBox createStageList() {
        VBox stageList = new VBox();
        stageList.setAlignment(Pos.TOP_CENTER);
        stageList.setSpacing(10);
        stageList.setBackground(Background.EMPTY);
        return stageList;
    }

    private void setupStageList(VBox stageList) {
        for (int i = 1; i <= 10; i++) {
            Button stageButton = createStageButton(i);
            setupStageButton(stageButton, i);
            stageList.getChildren().add(stageButton);
        }
    }

    private Button createStageButton(int stageNumber) {
        String buttonText = "Stage " + stageNumber;
        Button stageButton = new Button(buttonText);
        stageButton.setStyle("-fx-font-family: 'Courier New';" + "-fx-font-size: 14px;" + "-fx-text-fill: white;");
        stageButton.setAlignment(Pos.CENTER);
        stageButton.setPrefWidth(200);
        setNormalStyle(stageButton);
        return stageButton;
    }

    private void setupStageButton(Button stageButton, int stageNumber) {
        setupFocusAndMouseEvents(stageButton);
        stageButton.setOnAction(event -> {
            GameVariables.stageNumber = stageNumber;
            GameVariables.remainingTanks = 6 + stageNumber * 4;
            SceneHandler.getINSTANCE().game();
        });
    }

    private ScrollPane createScrollPane(VBox stageList) {
        ScrollPane scrollPane = new ScrollPane(stageList);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, new Insets(0))));
        scrollPane.setStyle("-fx-background-color: black; fx-background: black");
        scrollPane.getContent().setStyle("-fx-background-color: black; fx-background: black");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        return scrollPane;
    }

    private void setupScrollPane(ScrollPane scrollPane) {
        BorderPane.setMargin(scrollPane, new Insets(50, 50, 50, 50));
    }

    private void setNodesInBorderPane(ScrollPane scrollPane) {
        borderPane.setCenter(scrollPane);
    }
}
