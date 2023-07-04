package ir.ac.kntu.gamelogic.models.tanks;

import ir.ac.kntu.gamelogic.gamevariables.GameVariables;
import ir.ac.kntu.gamelogic.models.Bullet;
import ir.ac.kntu.gamelogic.models.Direction;
import ir.ac.kntu.gamelogic.models.Unit;
import ir.ac.kntu.gamelogic.services.GridHandler;
import ir.ac.kntu.gamelogic.services.PlayerHandler;

import java.util.Random;

public abstract class EnemyTank extends Unit {
    protected double turningDistance;

    protected double firingDistance;

    protected int score;

    public EnemyTank(double x, double y) {
        super(x, y);
        changeDirection();
        turningDistance = 0;
        firingDistance = 0;
    }

    @Override public void update() {
        if (GameVariables.gameStatus != GameVariables.GameStatus.RUNNING) {
            return;
        }

        turningDistance += distance;
        firingDistance += distance;

        if (turningDistance >= 60 * velocity) {
            changeDirection();
            turningDistance -= 60 * velocity;
        }
        if (firingDistance >= 30 * velocity) {
            fire();
            firingDistance -= 30 * velocity;
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
        Bullet bullet = new Bullet(x, y, damage, direction, Bullet.Origin.ENEMY);
        GridHandler.getInstance().addGameObject(bullet);
    }

    @Override public void die() {
        super.die();
        PlayerHandler.getInstance().addScore(score);
    }

    protected void changeDirection() {
        Direction[] directions = Direction.values();
        direction = directions[new Random().nextInt(directions.length - 1)];
    }
}
