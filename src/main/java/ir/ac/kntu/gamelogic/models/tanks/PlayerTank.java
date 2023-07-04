package ir.ac.kntu.gamelogic.models.tanks;

import ir.ac.kntu.gamecontroller.PlayerController;
import ir.ac.kntu.gamelogic.gamevariables.GameVariables;
import ir.ac.kntu.gamelogic.models.Bullet;
import ir.ac.kntu.gamelogic.models.Direction;
import ir.ac.kntu.gamelogic.models.GameObject;
import ir.ac.kntu.gamelogic.models.Unit;
import ir.ac.kntu.gamelogic.models.elements.Element;
import ir.ac.kntu.gamelogic.models.terrains.Flag;
import ir.ac.kntu.gamelogic.models.terrains.Spawner;
import ir.ac.kntu.gamelogic.models.terrains.Wall;
import ir.ac.kntu.gamelogic.services.CollisionHandler;
import ir.ac.kntu.gamelogic.services.GridHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Timer;
import java.util.TimerTask;

public class PlayerTank extends Unit {
    private final Spawner spawner;
    private boolean isMoving;
    private boolean isFiring;
    private double firingDistance;

    public PlayerTank(double x, double y) {
        super(x, y);
        direction = Direction.UP;
        isMoving = false;
        health = 3;
        damage = 1;
        spawner = new Spawner(this.x, this.y);
    }

    @Override public void update() {
        firingDistance += distance;

        if (firingDistance >= 15 * velocity && isFiring) {
            fire();
            firingDistance = 0;
        }

        move();
    }

    public void fire() {
        double x = this.x;
        double y = this.y;
        switch (direction) {
            case RIGHT -> x += width * 0.5;
            case LEFT -> x -= width * 0.5;
            case DOWN -> y += height * 0.5;
            case UP -> y -= height * 0.5;
            default -> {
            }
        }
        Bullet bullet = new Bullet(x, y, damage, direction, Bullet.Origin.PLAYER);
        GridHandler.getInstance().addGameObject(bullet);
    }

    @Override public void move() {
        if (!isMoving) {
            lastTime = System.nanoTime();
            return;
        }
        distance = velocity;
        GameObject collided = CollisionHandler.getINSTANCE().checkCollision(this);
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
            moveHelper(collided);
        } else if (collided instanceof Element element) {
            element.apply(this);
        }
    }

    @Override public void draw(GraphicsContext gc) {
        String path;
        if (frameIndex % 4 < 2) {
            switch (direction) {
                case DOWN -> path = "images/sprites/Regular Player 1/down1.png";
                case RIGHT -> path = "images/sprites/Regular Player 1/right1.png";
                case LEFT -> path = "images/sprites/Regular Player 1/left1.png";
                default -> path = "images/sprites/Regular Player 1/up1.png";
            }
        } else {
            switch (direction) {
                case DOWN -> path = "images/sprites/Regular Player 1/down2.png";
                case RIGHT -> path = "images/sprites/Regular Player 1/right2.png";
                case LEFT -> path = "images/sprites/Regular Player 1/left2.png";
                default -> path = "images/sprites/Regular Player 1/up2.png";
            }
        }
        gc.drawImage(new Image(path), x - width / 2, y - height / 2, width, height);
    }

    @Override public void damage(int damage) {
        --health;
        this.damage = 1;
        if (health <= 0) {
            die();
        } else {
            reset();
            GridHandler.getInstance().removeGameObject(this);
            spawner.respawn(this);
        }
    }

    public void reset() {
        x = spawner.getX();
        y = spawner.getY();
        isMoving = false;
        isFiring = false;
    }

    @Override public void die() {
        super.die();
        GameVariables.gameStatus = GameVariables.GameStatus.PAUSED;
        new Timer().schedule(new TimerTask() {
            @Override public void run() {
                GameVariables.gameStatus = GameVariables.GameStatus.LOST;
            }
        }, 250);
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public void setFiring(boolean firing) {
        isFiring = firing;
    }
}
