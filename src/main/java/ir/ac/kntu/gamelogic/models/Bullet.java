package ir.ac.kntu.gamelogic.models;

import ir.ac.kntu.gamelogic.gamevariables.GameVariables;
import ir.ac.kntu.gamelogic.models.elements.Element;
import ir.ac.kntu.gamelogic.models.tanks.EnemyTank;
import ir.ac.kntu.gamelogic.models.tanks.PlayerTank;
import ir.ac.kntu.gamelogic.models.terrains.BrickWall;
import ir.ac.kntu.gamelogic.models.terrains.Flag;
import ir.ac.kntu.gamelogic.models.terrains.Spawner;
import ir.ac.kntu.gamelogic.services.CollisionHandler;
import ir.ac.kntu.gamelogic.services.GridHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet extends Unit {
    private final Origin origin;

    public Bullet(double x, double y, int damage, Direction direction, Origin origin) {
        super(x, y);
        this.damage = damage;
        velocity *= 5;
        width = GameVariables.TILE_SIZE / 4;
        height = GameVariables.TILE_SIZE / 4;
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
        if (GameVariables.gameStatus == GameVariables.GameStatus.PAUSED) {
            return;
        }

        move();
    }

    @Override public void move() {
        distance += velocity;
        GameObject collided = CollisionHandler.getInstance().checkCollision(this);
        if (collided == null || collided instanceof PlayerTank && origin == Origin.PLAYER ||
                collided instanceof EnemyTank && origin == Origin.ENEMY || collided instanceof Element ||
                collided instanceof Spawner) {
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
            GridHandler.getInstance().removeGameObject(this);
        } else if (collided instanceof BrickWall brickWall) {
            brickWall.damage(direction);
            GridHandler.getInstance().removeGameObject(this);
        } else if (collided instanceof Flag flag) {
            flag.damage();
        } else {
            GridHandler.getInstance().removeGameObject(this);
        }
    }

    public enum Origin {
        PLAYER,
        ENEMY
    }
}
