package ir.ac.kntu.gamelogic.model;

import ir.ac.kntu.gamelogic.CollisionHandler;
import ir.ac.kntu.gamelogic.gameconstants.Direction;

public abstract class Tank extends GameObject implements Collidable, Movable {
    protected double velocity = GameConstants.VELOCITY;

    protected long lastTime;

    protected Direction direction;

    protected Tank(int x, int y, CollisionHandler collisionHandler, int id) {
        super(x, y, collisionHandler, id);
        width = 4 * GameConstants.TILE_SIZE;
        height = 4 * GameConstants.TILE_SIZE;
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
        }
        return Math.abs(x - gameObject.x) < width / 2 + gameObject.width / 2 &&
                Math.abs(y - gameObject.y) < height / 2 + gameObject.height / 2;
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
        //System.out.println("id: " + id + " x: " + x + " y: " + y);
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
