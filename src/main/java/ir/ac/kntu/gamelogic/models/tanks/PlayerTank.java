package ir.ac.kntu.gamelogic.models.tanks;

import ir.ac.kntu.SceneHandler;
import ir.ac.kntu.gamecontroller.PlayerController;
import ir.ac.kntu.gamelogic.gameconstants.Direction;
import ir.ac.kntu.gamelogic.models.Bullet;
import ir.ac.kntu.gamelogic.models.Unit;
import ir.ac.kntu.gamelogic.services.BoardHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class PlayerTank extends Unit {
    private boolean isMoving;

    private boolean isFiring;

    private double firingDistance;

    public PlayerTank(int x, int y) {
        super(x, y);
        PlayerController.getInstance().setPlayer1(this);
        direction = Direction.UP;
        isMoving = false;
        health = 3;
        damage = 1;
    }

    @Override public void update() {
        firingDistance += distance;

        if (firingDistance >= 0.5 * velocity && isFiring) {
            fire();
            firingDistance = 0;
        }

        move();
    }

    @Override public void move() {
        if (isMoving) {
            super.move();
        } else {
            lastTime = System.nanoTime();
        }
    }

    @Override public void draw(GraphicsContext gc) {
        String path;
        if (frameIndex % 4 < 2) {
            switch (direction) {
                case DOWN -> path = "images/tile004.png";
                case RIGHT -> path = "images/tile006.png";
                case LEFT -> path = "images/tile002.png";
                default -> path = "images/tile000.png";
            }
        } else {
            switch (direction) {
                case DOWN -> path = "images/tile005.png";
                case RIGHT -> path = "images/tile007.png";
                case LEFT -> path = "images/tile003.png";
                default -> path = "images/tile001.png";
            }
        }
        gc.drawImage(new Image(path), x - width / 2, y - height / 2, width, height);
    }

    public void fire() {
        double x = this.x;
        double y = this.y;
        switch (direction) {
            case RIGHT -> x += width;
            case LEFT -> x -= width;
            case DOWN -> y += height;
            case UP -> y -= height;
        }
        Bullet bullet = new Bullet(x, y, damage, direction, Bullet.Origin.PLAYER);
        BoardHandler.getInstance().addGameObject(bullet);
    }

    @Override public void damage(int damage) {
        super.damage(damage);
        if (health <= 0) {
            SceneHandler.getINSTANCE().gameOver();
        }
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public void setFiring(boolean firing) {
        isFiring = firing;
    }
}
