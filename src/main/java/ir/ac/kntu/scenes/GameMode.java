package ir.ac.kntu.scenes;

import ir.ac.kntu.SceneHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GameMode {
    private final Pane rootPane;

    public GameMode(Pane rootPane) {
        this.rootPane = rootPane;
    }

    public void start() {
        VBox menu = new VBox();

        Image gameImage = new Image("images/sprites/Battle City.jpeg");
        ImageView imageView = new ImageView(gameImage);
        imageView.setFitWidth(gameImage.getWidth() * 3);
        imageView.setFitHeight(gameImage.getHeight() * 3);

        Button onePlayerButton = new Button("1 Player");
        onePlayerButton.setOnAction(event -> SceneHandler.getINSTANCE().game());
        onePlayerButton.setAlignment(Pos.CENTER);

        Button twoPlayerButton = new Button("2 Player");
        twoPlayerButton.setOnAction(event -> {
            System.out.println("2 Player button clicked");
        });
        twoPlayerButton.setAlignment(Pos.CENTER);

        menu.setSpacing(10);
        menu.setAlignment(Pos.CENTER);
        menu.setLayoutX(100);
        menu.setLayoutY(100);

        menu.getChildren().addAll(imageView, onePlayerButton, twoPlayerButton);

        rootPane.getChildren().add(menu);
    }
}
