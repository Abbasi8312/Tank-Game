package ir.ac.kntu.logic.model;

import ir.ac.kntu.logic.CollisionHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class RegularTank extends Tank {
    protected RegularTank(int x, int y, CollisionHandler collisionHandler, int id) {
        super(x, y, collisionHandler, id);
    }

    @Override public boolean isColliding(GameObject gameObject, double velocity) {
        double x = this.x;
        double y = this.y;
        switch (direction) {
            case UP -> y -= velocity;
            case DOWN -> y += velocity;
            case RIGHT -> x += velocity;
            case LEFT -> x -= velocity;
        }
        return Math.abs(x - gameObject.x) < width / 2 + gameObject.width / 2 &&
                Math.abs(y - gameObject.y) < height / 2 + gameObject.height / 2;
    }

    @Override public void run() {
        move();
    }

    @Override public void move() {
        long currentTime = System.nanoTime();
        double deltaTime = (currentTime - lastTime) / 1e9;
        double velocity = this.velocity * deltaTime;
        GameObject collided = collisionHandler.checkCollision(this, velocity);
        if (collided == null) {
            switch (direction) {
                case UP -> y -= velocity;
                case DOWN -> y += velocity;
                case RIGHT -> x += velocity;
                case LEFT -> x -= velocity;
            }
        }
        System.out.println("id: " + id + " x: " + x + " y: " + y);
        lastTime = currentTime;
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
        gc.drawImage(new Image(path), x - width / 2, y - width / 2, width, height);
    }
}
