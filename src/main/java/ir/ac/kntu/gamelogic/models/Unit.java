package ir.ac.kntu.gamelogic.models;

import ir.ac.kntu.gamelogic.gameconstants.Direction;
import ir.ac.kntu.gamelogic.gameconstants.GameConstants;
import ir.ac.kntu.gamelogic.models.interfaces.Collidable;
import ir.ac.kntu.gamelogic.models.interfaces.Movable;
import ir.ac.kntu.gamelogic.services.BoardHandler;
import ir.ac.kntu.gamelogic.services.CollisionHandler;

public abstract class Unit extends GameObject implements Collidable, Movable {
    protected double velocity = GameConstants.VELOCITY;

    protected long lastTime;

    protected Direction direction;

    protected double distance;

    protected int damage;

    protected int health;

    public Unit(double x, double y) {
        super(x, y);
        width = 2 * GameConstants.TILE_SIZE;
        height = 2 * GameConstants.TILE_SIZE;
        lastTime = System.nanoTime();
    }

    public void damage(int damage) {
        health -= damage;
        if (health <= 0) {
            die();
        }
    }

    public void die() {
        BoardHandler.getInstance().removeGameObject(this);
    }

    @Override public boolean isColliding(GameObject gameObject, double velocity) {
        double x = this.x;
        double y = this.y;
        switch (direction) {
            case UP -> y -= velocity;
            case DOWN -> y += velocity;
            case RIGHT -> x += velocity;
            case LEFT -> x -= velocity;
            default -> {
            }
        }
        return Math.abs(x - gameObject.getX()) < width / 2 + gameObject.getWidth() / 2 &&
                Math.abs(y - gameObject.getY()) < height / 2 + gameObject.getHeight() / 2;
    }

    public abstract void update();

    @Override public void move() {
        long currentTime = System.nanoTime();
        double deltaTime = (currentTime - lastTime) / 1e9;
        double velocity = this.velocity * deltaTime;
        distance = velocity;
        GameObject collided = CollisionHandler.getINSTANCE().checkCollision(this, velocity);
        if (collided == null || collided instanceof Bullet) {
            ++frameIndex;

            switch (direction) {
                case UP -> this.y -= velocity;
                case DOWN -> this.y += velocity;
                case RIGHT -> this.x += velocity;
                case LEFT -> this.x -= velocity;
                default -> {
                }
            }
        }
        lastTime = currentTime;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
