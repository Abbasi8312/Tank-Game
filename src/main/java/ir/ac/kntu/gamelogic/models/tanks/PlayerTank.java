package ir.ac.kntu.gamelogic.models.tanks;

import ir.ac.kntu.gamecontroller.PlayerController;
import ir.ac.kntu.gamelogic.gameconstants.Direction;
import ir.ac.kntu.gamelogic.models.GameObject;
import ir.ac.kntu.gamelogic.services.CollisionHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class PlayerTank extends Tank {
    public PlayerTank(int x, int y) {
        super(x, y);
        PlayerController.setPlayer1(this);
        direction = Direction.UP;
    }

    @Override public void move() {
        double velocity = this.velocity / 20;
        GameObject collided = CollisionHandler.getINSTANCE().checkCollision(this, velocity);
        if (collided == null) {
            ++frameIndex;
            switch (direction) {
                case UP -> y -= velocity;
                case DOWN -> y += velocity;
                case RIGHT -> x += velocity;
                case LEFT -> x -= velocity;
                default -> {
                }
            }
        }
    }

    @Override public void draw(GraphicsContext gc) {
        String path;
        if (frameIndex % 4 < 2) {
            switch (direction) {
                case DOWN -> path = "images/tile004.png";
                case RIGHT -> path = "images/tile006.png";
                case LEFT -> path = "images/tile002.png";
                default -> path = "images/tile000.png";
            }
        } else {
            switch (direction) {
                case DOWN -> path = "images/tile005.png";
                case RIGHT -> path = "images/tile007.png";
                case LEFT -> path = "images/tile003.png";
                default -> path = "images/tile001.png";
            }
        }
        gc.drawImage(new Image(path), x - width / 2, y - height / 2, width, height);
    }
}
