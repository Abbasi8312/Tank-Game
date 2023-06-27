package ir.ac.kntu.logic.model;

import ir.ac.kntu.logic.CollisionHandler;

public abstract class Tank extends GameObject implements Collidable, Movable {
    protected int velocity = GameConstants.velocity;

    protected Direction direction;

    protected Tank(int x, int y, CollisionHandler collisionHandler, int id) {
        super(x, y, collisionHandler, id);
    }

    protected enum Direction {
        UP,
        DOWN,
        RIGHT,
        LEFT
    }
}
