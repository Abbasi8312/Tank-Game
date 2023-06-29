package ir.ac.kntu.gamelogic.model;

import ir.ac.kntu.gamelogic.CollisionHandler;
import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject {
    protected final int id;

    protected final CollisionHandler collisionHandler;

    protected double x;

    protected double y;

    protected double width;

    protected double height;

    protected int frameIndex = 0;

    protected GameObject(double x, double y, CollisionHandler collisionHandler, int id) {
        this.x = x;
        this.y = y;
        this.collisionHandler = collisionHandler;
        this.id = id;
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

    public int getId() {
        return id;
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
}
