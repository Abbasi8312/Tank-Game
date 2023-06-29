package ir.ac.kntu.logic.model;

import ir.ac.kntu.logic.CollisionHandler;
import ir.ac.kntu.logic.gameconstants.Direction;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public abstract class EnemyTank extends Tank implements Runnable {
    protected final Timer timer;


    protected EnemyTank(int x, int y, CollisionHandler collisionHandler, int id) {
        super(x, y, collisionHandler, id);
        timer = new Timer();
        timer.schedule(new ChangeDirection(), 0, 2000);
    }

    protected void changeDirection() {
        Direction[] directions = Direction.values();
        direction = directions[new Random().nextInt(directions.length)];
    }

    private class ChangeDirection extends TimerTask {
        @Override public void run() {
            changeDirection();
        }
    }
}
