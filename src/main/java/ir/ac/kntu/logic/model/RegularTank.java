package ir.ac.kntu.logic.model;

import ir.ac.kntu.logic.CollisionHandler;

import java.util.TimerTask;

public class RegularTank extends Tank {
    protected RegularTank(int x, int y, CollisionHandler collisionHandler, int id) {
        super(x, y, collisionHandler, id);
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
        return Math.abs(x - gameObject.x) < GameConstants.TILE_SIZE / 2 &&
                Math.abs(y - gameObject.y) < GameConstants.TILE_SIZE / 2;
    }

    @Override public void run() {
        System.out.println(id);
        timer.schedule(new ChangeDirection(), 0, 2000);
        System.out.println(id);
        move();
    }

    @Override public void move() {
        long lastTime = System.nanoTime();
        while (true) {
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - lastTime) / 10e9;
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
            System.out.println("id: " + id + " x: " + x + " y: " + y);
            lastTime = currentTime;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class ChangeDirection extends TimerTask {
        @Override public void run() {
            changeDirection();
        }
    }
}
