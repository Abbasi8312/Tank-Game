package ir.ac.kntu.gamelogic.model.tank;

import ir.ac.kntu.gamelogic.gameconstants.Direction;
import ir.ac.kntu.gamelogic.gameconstants.GameConstants;
import ir.ac.kntu.gamelogic.model.GameObject;
import ir.ac.kntu.gamelogic.model.interfaces.Collidable;
import ir.ac.kntu.gamelogic.model.interfaces.Movable;
import ir.ac.kntu.gamelogic.service.CollisionHandler;

public abstract class Tank extends GameObject implements Collidable, Movable {
    protected double velocity = GameConstants.VELOCITY;

    protected long lastTime;

    protected Direction direction;

    public Tank(int x, int y) {
        super(x, y);
        width = 2 * GameConstants.TILE_SIZE;
        height = 2 * GameConstants.TILE_SIZE;
        lastTime = System.nanoTime();
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

    @Override public void move() {
        long currentTime = System.nanoTime();
        double deltaTime = (currentTime - lastTime) / 1e9;
        double velocity = this.velocity * deltaTime;
        GameObject collided = CollisionHandler.getINSTANCE().checkCollision(this, velocity);
        if (collided == null) {
            switch (direction) {
                case UP -> y -= velocity;
                case DOWN -> y += velocity;
                case RIGHT -> x += velocity;
                case LEFT -> x -= velocity;
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
