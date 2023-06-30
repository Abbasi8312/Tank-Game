package ir.ac.kntu.gamelogic.models;

import ir.ac.kntu.gamelogic.gameconstants.Direction;
import ir.ac.kntu.gamelogic.gameconstants.GameConstants;
import ir.ac.kntu.gamelogic.models.tanks.EnemyTank;
import ir.ac.kntu.gamelogic.models.tanks.PlayerTank;
import ir.ac.kntu.gamelogic.models.walls.BrickWall;
import ir.ac.kntu.gamelogic.services.BoardHandler;
import ir.ac.kntu.gamelogic.services.CollisionHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet extends MovingUnit {
    private final Origin origin;

    public Bullet(double x, double y, int damage, Direction direction, Origin origin) {
        super(x, y);
        this.damage = damage;
        velocity *= 5;
        width = GameConstants.TILE_SIZE / 2;
        height = GameConstants.TILE_SIZE / 2;
        this.direction = direction;
        this.origin = origin;
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
        if (collided == null) {
            ++frameIndex;

            switch (direction) {
                case UP -> this.y -= velocity;
                case DOWN -> this.y += velocity;
                case RIGHT -> this.x += velocity;
                case LEFT -> this.x -= velocity;
                default -> {
                }
            }
        } else if (collided instanceof PlayerTank && origin == Origin.ENEMY ||
                collided instanceof EnemyTank && origin == Origin.PLAYER || collided instanceof Bullet) {
            BoardHandler.getInstance().removeGameObject(collided);
            BoardHandler.getInstance().removeGameObject(this);
        } else if (collided instanceof BrickWall brickWall) {
            brickWall.damage(direction);
            BoardHandler.getInstance().removeGameObject(this);
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
