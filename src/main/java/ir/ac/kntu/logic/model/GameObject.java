package ir.ac.kntu.logic.model;

import ir.ac.kntu.logic.CollisionHandler;

import java.util.Timer;

public abstract class GameObject {
    protected final int id;

    protected final CollisionHandler collisionHandler;

    protected final Timer timer;

    protected int x;

    protected int y;

    protected GameObject(int x, int y, CollisionHandler collisionHandler, int id) {
        this.x = x;
        this.y = y;
        this.collisionHandler = collisionHandler;
        timer = new Timer();
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }
}
