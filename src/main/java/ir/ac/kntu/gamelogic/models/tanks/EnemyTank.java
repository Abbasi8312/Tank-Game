package ir.ac.kntu.gamelogic.models.tanks;

import ir.ac.kntu.gamelogic.gameconstants.Direction;
import ir.ac.kntu.gamelogic.services.PlayerHandler;

import java.util.Random;

public abstract class EnemyTank extends Tank {
    protected double turningDistance;

    protected double firingDistance;

    protected int score;

    public EnemyTank(int x, int y) {
        super(x, y);
        changeDirection();
        turningDistance = 0;
        firingDistance = 0;
    }

    @Override public void update() {
        turningDistance += distance;
        firingDistance += distance;

        if (turningDistance >= 2 * velocity) {
            changeDirection();
            turningDistance -= 2 * velocity;
        }
        if (firingDistance >= 0.5 * velocity) {
            fire();
            firingDistance -= 0.5 * velocity;
        }

        move();
    }

    @Override public void die() {
        super.die();
        PlayerHandler.getINSTANCE().addScore(score);
    }

    protected void changeDirection() {
        Direction[] directions = Direction.values();
        direction = directions[new Random().nextInt(directions.length - 1)];
    }
}
