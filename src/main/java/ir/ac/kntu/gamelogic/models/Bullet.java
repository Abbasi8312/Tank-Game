package ir.ac.kntu.gamelogic.models;

import ir.ac.kntu.gamelogic.gameconstants.Direction;
import ir.ac.kntu.gamelogic.gameconstants.GameConstants;
import ir.ac.kntu.gamelogic.models.tanks.EnemyTank;
import ir.ac.kntu.gamelogic.models.tanks.PlayerTank;
import ir.ac.kntu.gamelogic.models.terrains.BrickWall;
import ir.ac.kntu.gamelogic.services.BoardHandler;
import ir.ac.kntu.gamelogic.services.CollisionHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet extends Unit {
    private final Origin origin;

    public Bullet(double x, double y, int damage, Direction direction, Origin origin) {
        super(x, y);
        this.damage = damage;
        velocity *= 5;
        width = GameConstants.TILE_SIZE / 4;
        height = GameConstants.TILE_SIZE / 4;
        collisionRect.width = width;
        collisionRect.height = height;
        switch (direction) {
            case UP, DOWN -> width *= 3.0 / 4;
            default -> height *= 3.0 / 4;
        }
        this.direction = direction;
        this.origin = origin;
        health = 1;
    }

    @Override public void draw(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(x - width / 2, y - height / 2, width, height);
    }

    @Override public void update() {
        move();
    }

    @Override public void move() {
        long currentTime = System.nanoTime();
        double deltaTime = (currentTime - lastTime) / 1e9;
        double velocity = this.velocity * deltaTime;
        distance += velocity;
        GameObject collided = CollisionHandler.getINSTANCE().checkCollision(this, velocity);
        if (collided == null || collided instanceof PlayerTank && origin == Origin.PLAYER ||
                collided instanceof EnemyTank && origin == Origin.ENEMY) {
            ++frameIndex;
            switch (direction) {
                case UP -> this.y -= velocity;
                case DOWN -> this.y += velocity;
                case RIGHT -> this.x += velocity;
                case LEFT -> this.x -= velocity;
                default -> {
                }
            }
        } else if (collided instanceof PlayerTank || collided instanceof EnemyTank || collided instanceof Bullet) {
            ((Unit) collided).damage(damage);
            BoardHandler.getInstance().removeGameObject(this);
        } else if (collided instanceof BrickWall brickWall) {
            brickWall.damage(direction);
            BoardHandler.getInstance().removeGameObject(this);
        } else if (collided instanceof Flag flag) {
            flag.damage();
        } else {
            BoardHandler.getInstance().removeGameObject(this);
        }
        lastTime = currentTime;
    }

    public enum Origin {
        PLAYER,
        ENEMY
    }
}
