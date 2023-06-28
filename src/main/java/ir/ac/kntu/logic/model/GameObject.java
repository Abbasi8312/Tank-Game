package ir.ac.kntu.logic.model;

import ir.ac.kntu.logic.CollisionHandler;
import javafx.scene.canvas.GraphicsContext;

import java.util.Timer;

public abstract class GameObject {
    protected final int id;

    protected final CollisionHandler collisionHandler;

    protected final Timer timer;

    protected double x;

    protected double y;

    protected double width;

    protected double height;

    public abstract void draw(GraphicsContext gc, int frameIndex);

    protected GameObject(double x, double y, CollisionHandler collisionHandler, int id) {
        this.x = x;
        this.y = y;
        this.collisionHandler = collisionHandler;
        timer = new Timer();
        this.id = id;
    }

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
