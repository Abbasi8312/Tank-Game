package ir.ac.kntu.gamelogic.models;

import ir.ac.kntu.gamelogic.gameconstants.Direction;
import ir.ac.kntu.gamelogic.gameconstants.GameConstants;
import ir.ac.kntu.gamelogic.models.interfaces.Collidable;
import ir.ac.kntu.gamelogic.models.interfaces.Movable;
import ir.ac.kntu.gamelogic.models.terrains.Wall;
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
            switch (direction) {
                case UP -> {
                    if ((x <= (collided.x - (0.85 * GameConstants.TILE_SIZE))) && !(CollisionHandler.getINSTANCE()
                            .checkPoint(collided.x - width, y - velocity) instanceof Wall)) {
                        x = collided.x - width;
                    } else if (x >= collided.x + 0.85 * GameConstants.TILE_SIZE && !(CollisionHandler.getINSTANCE()
                            .checkPoint(collided.x + width, y - velocity) instanceof Wall)) {
                        x = collided.x + width;
                    }
                }
                case DOWN -> {
                    if (x <= collided.x - 0.85 * GameConstants.TILE_SIZE && !(CollisionHandler.getINSTANCE()
                            .checkPoint(collided.x - width, y + velocity) instanceof Wall)) {
                        x = collided.x - width;
                    } else if (x >= collided.x + 0.85 * GameConstants.TILE_SIZE && !(CollisionHandler.getINSTANCE()
                            .checkPoint(collided.x + width, y + velocity) instanceof Wall)) {
                        x = collided.x + width;
                    }
                }
                case RIGHT -> {
                    if (y <= collided.y - 0.85 * GameConstants.TILE_SIZE && !(CollisionHandler.getINSTANCE()
                            .checkPoint(collided.y - height, x + velocity) instanceof Wall)) {
                        y = collided.y - height;
                    } else if (y >= collided.y + 0.85 * GameConstants.TILE_SIZE && !(CollisionHandler.getINSTANCE()
                            .checkPoint(collided.y + height, x + velocity) instanceof Wall)) {
                        y = collided.y + height;
                    }
                }
                case LEFT -> {
                    if (y <= collided.y - 0.85 * GameConstants.TILE_SIZE && !(CollisionHandler.getINSTANCE()
                            .checkPoint(collided.y - height, x - velocity) instanceof Wall)) {
                        y = collided.y - height;
                    } else if (y >= collided.y + 0.85 * GameConstants.TILE_SIZE && !(CollisionHandler.getINSTANCE()
                            .checkPoint(collided.y + height, x - velocity) instanceof Wall)) {
                        y = collided.y + height;
                    }
                }
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
