package ir.ac.kntu.gamelogic.models;

import ir.ac.kntu.gamelogic.gamevariables.GameVariables;
import ir.ac.kntu.gamelogic.models.interfaces.Collidable;
import ir.ac.kntu.gamelogic.models.interfaces.Movable;
import ir.ac.kntu.gamelogic.models.terrains.Wall;
import ir.ac.kntu.gamelogic.services.BoardHandler;
import ir.ac.kntu.gamelogic.services.CollisionHandler;

public abstract class Unit extends GameObject implements Collidable, Movable {
    protected double velocity = GameVariables.VELOCITY;

    protected long lastTime;

    protected Direction direction;

    protected double distance;

    protected int damage;

    protected int health;

    public Unit(double x, double y) {
        super(x, y);
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
        return Math.abs(x + collisionRect.relativeX - (gameObject.getX() + collisionRect.relativeX)) <
                collisionRect.width / 2 + gameObject.collisionRect.width / 2 &&
                Math.abs(y + collisionRect.relativeY - (gameObject.getY() + collisionRect.relativeY)) <
                        collisionRect.height / 2 + gameObject.collisionRect.height / 2;
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
                case UP -> y -= velocity;
                case DOWN -> y += velocity;
                case RIGHT -> x += velocity;
                case LEFT -> x -= velocity;
                default -> {
                }
            }
        } else if (collided instanceof Wall || collided instanceof Flag) {
            moveHelper(velocity, collided);
        }
        lastTime = currentTime;
    }

    private void moveHelper(double velocity, GameObject collided) {
        switch (direction) {
            case UP -> upHelper(velocity, collided);
            case DOWN -> downHelper(velocity, collided);
            case RIGHT -> rightHelper(velocity, collided);
            case LEFT -> leftHelper(velocity, collided);
            default -> {
            }
        }
    }

    private void upHelper(double velocity, GameObject collided) {
        double leftLimit = collided.x - (0.8 * GameVariables.TILE_SIZE);
        double rightLimit = collided.x + (0.8 * GameVariables.TILE_SIZE);
        if (x <= leftLimit &&
                !(CollisionHandler.getINSTANCE().checkPoint(collided.x - width, y - velocity) instanceof Wall)) {
            x = collided.x - width;
        } else if (x >= rightLimit &&
                !(CollisionHandler.getINSTANCE().checkPoint(collided.x + width, y - velocity) instanceof Wall)) {
            x = collided.x + width;
        }
    }

    private void downHelper(double velocity, GameObject collided) {
        double leftLimit = collided.x - (0.8 * GameVariables.TILE_SIZE);
        double rightLimit = collided.x + (0.8 * GameVariables.TILE_SIZE);
        if (x <= leftLimit &&
                !(CollisionHandler.getINSTANCE().checkPoint(collided.x - width, y + velocity) instanceof Wall)) {
            x = collided.x - width;
        } else if (x >= rightLimit &&
                !(CollisionHandler.getINSTANCE().checkPoint(collided.x + width, y + velocity) instanceof Wall)) {
            x = collided.x + width;
        }
    }

    private void rightHelper(double velocity, GameObject collided) {
        double topLimit = collided.y - (0.8 * GameVariables.TILE_SIZE);
        double bottomLimit = collided.y + (0.8 * GameVariables.TILE_SIZE);
        if (y <= topLimit &&
                !(CollisionHandler.getINSTANCE().checkPoint(collided.y - height, x + velocity) instanceof Wall)) {
            y = collided.y - height;
        } else if (y >= bottomLimit &&
                !(CollisionHandler.getINSTANCE().checkPoint(collided.y + height, x + velocity) instanceof Wall)) {
            y = collided.y + height;
        }
    }

    private void leftHelper(double velocity, GameObject collided) {
        double topLimit = collided.y - (0.8 * GameVariables.TILE_SIZE);
        double bottomLimit = collided.y + (0.8 * GameVariables.TILE_SIZE);
        if (y <= topLimit &&
                !(CollisionHandler.getINSTANCE().checkPoint(collided.y - height, x - velocity) instanceof Wall)) {
            y = collided.y - height;
        } else if (y >= bottomLimit &&
                !(CollisionHandler.getINSTANCE().checkPoint(collided.y + height, x - velocity) instanceof Wall)) {
            y = collided.y + height;
        }
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
