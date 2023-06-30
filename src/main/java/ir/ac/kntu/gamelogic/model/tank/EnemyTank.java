package ir.ac.kntu.gamelogic.model.tank;

import ir.ac.kntu.gamelogic.gameconstants.Direction;
import ir.ac.kntu.gamelogic.service.TimerWrapper;

import java.util.Random;
import java.util.TimerTask;

public abstract class EnemyTank extends Tank implements Runnable {
    public EnemyTank(int x, int y) {
        super(x, y);
        TimerWrapper.getInstance().schedule(new ChangeDirection(), 0, 2000);
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
