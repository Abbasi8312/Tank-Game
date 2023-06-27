package ir.ac.kntu.logic.model;

import ir.ac.kntu.logic.CollisionHandler;

import java.util.Random;

public abstract class Tank extends GameObject implements Collidable, Movable {
    protected double velocity = GameConstants.VELOCITY;

    protected Direction direction;

    protected Tank(int x, int y, CollisionHandler collisionHandler, int id) {
        super(x, y, collisionHandler, id);
        changeDirection();
        width = 4 * GameConstants.TILE_SIZE;
        height = 4 * GameConstants.TILE_SIZE;
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
}
