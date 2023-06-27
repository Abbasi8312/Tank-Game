package ir.ac.kntu.logic.model;

import ir.ac.kntu.logic.CollisionHandler;

import java.util.Random;
import java.util.TimerTask;

public class RegularTank extends Tank {
    protected RegularTank(int x, int y, CollisionHandler collisionHandler, int id) {
        super(x, y, collisionHandler, id);
    }

    @Override public boolean isCollide(GameObject gameObject) {
        int x = this.x;
        int y = this.y;
        switch (direction) {
            case UP -> y -= velocity;
            case DOWN -> y += velocity;
            case RIGHT -> x += velocity;
            case LEFT -> x -= velocity;
        }
        return Math.abs(x - gameObject.x) < GameConstants.COLLISION_BOX_LENGTH / 2 &&
                Math.abs(y - gameObject.y) < GameConstants.COLLISION_BOX_LENGTH / 2;
    }

    @Override public void run() {
        timer.schedule(new ChangeDirection(), 0, 2000);
        timer.schedule(new MoveTask(), 5, 500);
    }

    @Override public void move() {
        GameObject collided = collisionHandler.checkCollision(this);
        if (collided == null) {
            switch (direction) {
                case UP -> y -= velocity;
                case DOWN -> y += velocity;
                case RIGHT -> x += velocity;
                case LEFT -> x -= velocity;
            }
        }
        System.out.println("id: " + id + " x: " + x + " y: " + y);
    }

    private void changeDirection() {
        Direction[] directions = Direction.values();
        direction = directions[new Random().nextInt(directions.length)];
    }

    private class MoveTask extends TimerTask {
        @Override public void run() {
            move();
        }
    }

    private class ChangeDirection extends TimerTask {
        @Override public void run() {
            changeDirection();
        }
    }
}
