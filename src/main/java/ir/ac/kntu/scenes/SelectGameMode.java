package ir.ac.kntu.scenes;

import ir.ac.kntu.SceneHandler;
import ir.ac.kntu.gamelogic.services.PlayerHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SelectGameMode {
    private final BorderPane borderPane;

    public SelectGameMode(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public void start() {
        VBox menu = createMenu();
        HBox topBox = createTopBox();

        Image gameImage = new Image("images/sprites/Battle City.jpeg");
        ImageView imageView = createGameImageView(gameImage);

        Button onePlayerButton = createMenuButton("1 Player", this::handleOnePlayerButton);
        Button twoPlayerButton = createMenuButton("2 Player", this::handleTwoPlayerButton);

        menu.getChildren().addAll(topBox, imageView, onePlayerButton, twoPlayerButton);

        setNodesInBorderPane(menu);
    }

    private VBox createMenu() {
        VBox menu = new VBox();
        menu.setSpacing(20);
        menu.setAlignment(Pos.CENTER);
        menu.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        return menu;
    }

    private HBox createTopBox() {
        HBox topBox = new HBox();
        topBox.setSpacing(20);
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(10));

        Text lastScoreText = createTopBoxText("Last Score: %05d", PlayerHandler.getINSTANCE().getScore());
        Text playerNameText = createTopBoxText("Player Name: %s", PlayerHandler.getINSTANCE().getName());
        Text highScoreText = createTopBoxText("High Score: %05d", PlayerHandler.getINSTANCE().getHighScore());

        topBox.getChildren().addAll(lastScoreText, playerNameText, highScoreText);
        return topBox;
    }

    private Text createTopBoxText(String format, Object... args) {
        Text text = new Text(String.format(format, args));
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Courier New", FontWeight.NORMAL, 14));
        return text;
    }

    private ImageView createGameImageView(Image gameImage) {
        ImageView imageView = new ImageView(gameImage);
        imageView.setFitWidth(gameImage.getWidth() * 3);
        imageView.setFitHeight(gameImage.getHeight() * 3);
        return imageView;
    }

    private Button createMenuButton(String text, Runnable action) {
        Button button = new Button(text);
        button.setStyle("-fx-font-family: 'Courier New';" + "-fx-font-size: 14px;" + "-fx-text-fill: white;");
        button.setPrefWidth(200);
        RegionStyleManager.setNormalStyle(button);
        RegionStyleManager.setupFocusAndMouseEvents(button);
        button.setOnAction(event -> action.run());
        return button;
    }

    private void handleOnePlayerButton() {
        PlayerHandler.getINSTANCE().resetScore();
        SceneHandler.getINSTANCE().selectStage();
    }

    private void handleTwoPlayerButton() {
        System.out.println("2 Player button clicked");
    }

    private void setNodesInBorderPane(VBox menu) {
        borderPane.setCenter(menu);
    }
}
