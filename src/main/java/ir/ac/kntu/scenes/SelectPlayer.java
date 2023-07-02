package ir.ac.kntu.scenes;

import ir.ac.kntu.SceneHandler;
import ir.ac.kntu.gamelogic.models.Player;
import ir.ac.kntu.gamelogic.services.PlayerHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class SelectPlayer {
    private final BorderPane borderPane;

    public SelectPlayer(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public void start() {
        setupBackground();
        TextField playerNameField = createPlayerNameField();
        setupPlayerNameField(playerNameField);
        VBox playerList = createPlayerList();
        setupPlayerList(playerList);
        ScrollPane scrollPane = createScrollPane(playerList);
        setupScrollPane(scrollPane);
        setNodesInBorderPane(playerNameField, scrollPane);
    }

    private void setupBackground() {
        borderPane.setBackground(Background.EMPTY);
    }

    private TextField createPlayerNameField() {
        TextField playerNameField = new TextField();
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.length() <= 40) {
                return change;
            }
            return null;
        });
        playerNameField.setTextFormatter(textFormatter);
        playerNameField.setAlignment(Pos.CENTER);
        playerNameField.setPromptText("Enter a new name");
        playerNameField.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        playerNameField.setStyle("-fx-text-fill: white");
        return playerNameField;
    }

    private void setupPlayerNameField(TextField playerNameField) {
        setupFocusAndMouseEvents(playerNameField);

        playerNameField.setOnAction(event -> {
            String playerName = playerNameField.getText();
            Player player = PlayerHandler.getINSTANCE().newPlayer(playerName);
            PlayerHandler.getINSTANCE().setCurrentPlayer(player);
            PlayerHandler.getINSTANCE().savePlayer();
            playerNameField.clear();
            SceneHandler.getINSTANCE().gameMode();
        });
    }

    private void setupFocusAndMouseEvents(Region region) {
        region.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                setFocusedStyle(region);
            } else {
                setNormalStyle(region);
            }
        });

        region.setOnMouseEntered(e -> setOnMouseStyle(region));
        region.setOnMouseExited(e -> {
            if (region.isFocused()) {
                setFocusedStyle(region);
            } else {
                setNormalStyle(region);
            }
        });
    }

    private void setFocusedStyle(Region region) {
        region.setBackground(new Background(new BackgroundFill(Color.DARKRED, new CornerRadii(50), new Insets(0))));
        region.setBorder(new Border(
                new BorderStroke(Color.DARKRED, BorderStrokeStyle.SOLID, new CornerRadii(50), BorderWidths.DEFAULT)));
    }

    private void setOnMouseStyle(Region region) {
        region.setBackground(new Background(
                new BackgroundFill(Color.DARKRED.darker().darker(), new CornerRadii(50), new Insets(0))));
        region.setBorder(new Border(
                new BorderStroke(Color.DARKRED.darker().darker(), BorderStrokeStyle.SOLID, new CornerRadii(50),
                        BorderWidths.DEFAULT)));
    }

    private void setNormalStyle(Region region) {
        if (region.isFocused()) {
            setOnMouseStyle(region);
        } else {
            region.setBackground(Background.EMPTY);
            region.setBorder(new Border(new BorderStroke(Color.DARKRED, BorderStrokeStyle.DASHED, new CornerRadii(50),
                    BorderWidths.DEFAULT)));
        }
    }

    private VBox createPlayerList() {
        VBox playerList = new VBox();
        playerList.setAlignment(Pos.TOP_CENTER);
        playerList.setSpacing(10);
        playerList.setBackground(Background.EMPTY);
        return playerList;
    }

    private void setupPlayerList(VBox playerList) {
        List<Player> existingPlayers = PlayerHandler.getINSTANCE().getCurrentPlayers();
        for (Player player : existingPlayers) {
            Button playerButton = createPlayerButton(player);
            setupPlayerButton(playerButton, player);
            playerList.getChildren().add(playerButton);
        }
    }

    private Button createPlayerButton(Player player) {
        Button playerButton = new Button(player.getName());
        playerButton.setAlignment(Pos.CENTER);
        playerButton.setPrefWidth(200);
        playerButton.setBackground(Background.EMPTY);
        playerButton.setBorder(new Border(
                new BorderStroke(Color.DARKRED, BorderStrokeStyle.DASHED, new CornerRadii(50), BorderWidths.DEFAULT)));
        playerButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        playerButton.setStyle("-fx-text-fill: white");
        return playerButton;
    }

    private void setupPlayerButton(Button playerButton, Player player) {
        setupFocusAndMouseEvents(playerButton);

        playerButton.setOnAction(event -> {
            PlayerHandler.getINSTANCE().setCurrentPlayer(player);
            SceneHandler.getINSTANCE().gameMode();
        });
    }

    private ScrollPane createScrollPane(VBox playerList) {
        ScrollPane scrollPane = new ScrollPane(playerList);
        scrollPane.setFitToWidth(true);
        scrollPane.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, new Insets(0))));
        scrollPane.setStyle("-fx-background-color: black; fx-background: black");
        scrollPane.getContent().setStyle("-fx-background-color: black; fx-background: black");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        return scrollPane;
    }

    private void setupScrollPane(ScrollPane scrollPane) {
        BorderPane.setMargin(scrollPane, new Insets(0, 50, 50, 50));
    }

    private void setNodesInBorderPane(TextField playerNameField, ScrollPane scrollPane) {
        borderPane.setTop(playerNameField);
        borderPane.setCenter(scrollPane);
        BorderPane.setMargin(playerNameField, new Insets(20, 50, 50, 50));
    }
}
