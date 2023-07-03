package ir.ac.kntu.gamelogic.models;

import ir.ac.kntu.gamelogic.gameconstants.GameConstants;
import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject {
    protected final CollisionRectangle collisionRect;

    protected double x;

    protected double y;

    protected double width;

    protected double height;

    protected int frameIndex = 0;

    public GameObject(double x, double y) {
        this.x = x;
        this.y = y;
        width = GameConstants.TILE_SIZE;
        height = GameConstants.TILE_SIZE;
        collisionRect = new CollisionRectangle(width, height);
    }

    public abstract void draw(GraphicsContext gc);

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    protected static class CollisionRectangle {
        public double relativeX;

        public double relativeY;

        public double width;

        public double height;

        public CollisionRectangle(double width, double height) {
            this.relativeX = 0;
            this.relativeY = 0;
            this.width = width;
            this.height = height;
        }
    }
}
