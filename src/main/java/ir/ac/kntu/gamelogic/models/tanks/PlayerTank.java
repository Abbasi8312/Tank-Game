package ir.ac.kntu.gamelogic.models.tanks;

import ir.ac.kntu.SceneHandler;
import ir.ac.kntu.gamecontroller.PlayerController;
import ir.ac.kntu.gamelogic.models.Bullet;
import ir.ac.kntu.gamelogic.models.Direction;
import ir.ac.kntu.gamelogic.models.Unit;
import ir.ac.kntu.gamelogic.services.BoardHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class PlayerTank extends Unit {
    private boolean isMoving;

    private boolean isFiring;

    private double firingDistance;

    public PlayerTank(double x, double y) {
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
        BoardHandler.getInstance().addGameObject(bullet);
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

    @Override public void die() {
        super.die();
        SceneHandler.getINSTANCE().gameOver();
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public void setFiring(boolean firing) {
        isFiring = firing;
    }
}
