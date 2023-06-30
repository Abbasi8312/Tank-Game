package ir.ac.kntu.gamelogic.models.tanks;

import ir.ac.kntu.gamelogic.gameconstants.Direction;

import java.util.Random;

public abstract class EnemyTank extends Tank {
    public EnemyTank(int x, int y) {
        super(x, y);
        changeDirection();
    }

    @Override public void move() {
        if (distance >= 2 * velocity) {
            changeDirection();
            distance -= 2 * velocity;
        }
        super.move();
    }

    protected void changeDirection() {
        Direction[] directions = Direction.values();
        direction = directions[new Random().nextInt(directions.length - 1)];
    }
}
