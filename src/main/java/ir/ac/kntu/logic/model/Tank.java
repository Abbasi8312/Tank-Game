package ir.ac.kntu.logic.model;

import ir.ac.kntu.logic.CollisionHandler;

import java.util.Random;
import java.util.TimerTask;

public abstract class Tank extends GameObject implements Collidable, Movable {
    protected double velocity = GameConstants.VELOCITY;

    protected long lastTime;

    protected Direction direction;

    protected Tank(int x, int y, CollisionHandler collisionHandler, int id) {
        super(x, y, collisionHandler, id);
        timer.schedule(new ChangeDirection(), 0, 2000);
        width = 4 * GameConstants.TILE_SIZE;
        height = 4 * GameConstants.TILE_SIZE;
        lastTime = System.nanoTime();
    }

    protected void changeDirection() {
        Direction[] directions = Direction.values();
        direction = directions[new Random().nextInt(directions.length)];
    }

    protected enum Direction {
        UP,
        DOWN,
        RIGHT,
        LEFT
    }

    private class ChangeDirection extends TimerTask {
        @Override public void run() {
            changeDirection();
        }
    }
}
