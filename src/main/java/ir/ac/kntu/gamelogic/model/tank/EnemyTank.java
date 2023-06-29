package ir.ac.kntu.gamelogic.model.tank;

import ir.ac.kntu.gamelogic.service.CollisionHandler;
import ir.ac.kntu.gamelogic.gameconstants.Direction;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public abstract class EnemyTank extends Tank implements Runnable {
    protected final Timer timer;


    public EnemyTank(int x, int y, CollisionHandler collisionHandler) {
        super(x, y, collisionHandler);
        timer = new Timer();
        timer.schedule(new ChangeDirection(), 0, 2000);
    }

    protected void changeDirection() {
        Direction[] directions = Direction.values();
        direction = directions[new Random().nextInt(directions.length - 1)];
    }

    private class ChangeDirection extends TimerTask {
        @Override public void run() {
            changeDirection();
        }
    }
}
