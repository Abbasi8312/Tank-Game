package ir.ac.kntu.logic.model;

import ir.ac.kntu.gamecontroller.PlayerController;
import ir.ac.kntu.logic.CollisionHandler;
import ir.ac.kntu.logic.gameconstants.Direction;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends Tank {
    protected Player(int x, int y, CollisionHandler collisionHandler, int id) {
        super(x, y, collisionHandler, id);
        PlayerController.setPlayer1(this);
        direction = Direction.UP;
    }

    @Override public void move() {
        double velocity = this.velocity / 5;
        GameObject collided = collisionHandler.checkCollision(this, velocity);
        if (collided == null) {
            switch (direction) {
                case UP -> y -= velocity;
                case DOWN -> y += velocity;
                case RIGHT -> x += velocity;
                case LEFT -> x -= velocity;
            }
        }
    }

    @Override public void draw(GraphicsContext gc, int frameIndex) {
        String path = "images/tile000.png";
        if (frameIndex % 4 < 2) {
            switch (direction) {
                case UP -> path = "images/tile000.png";
                case DOWN -> path = "images/tile004.png";
                case RIGHT -> path = "images/tile006.png";
                case LEFT -> path = "images/tile002.png";
            }
        } else {
            switch (direction) {
                case UP -> path = "images/tile001.png";
                case DOWN -> path = "images/tile005.png";
                case RIGHT -> path = "images/tile007.png";
                case LEFT -> path = "images/tile003.png";
            }
        }
        gc.drawImage(new Image(path), x - width / 2, y - height / 2, width, height);
    }
}
