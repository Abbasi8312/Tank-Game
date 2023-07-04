package ir.ac.kntu.gamelogic.models.terrains;

import ir.ac.kntu.gamelogic.models.GameObject;
import ir.ac.kntu.gamelogic.models.tanks.*;
import ir.ac.kntu.gamelogic.services.GridHandler;
import ir.ac.kntu.gamelogic.services.TimerWrapper;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;
import java.util.TimerTask;

public class Spawner extends GameObject {
    public Spawner(double x, double y) {
        super(x, y);
    }

    @Override public void draw(GraphicsContext gc) {
        if (frameIndex == 1) {
            gc.setFill(Color.BLUE);
            gc.fillRect(x - width / 2, y - height / 2, width, height);
        }
    }

    public boolean spawn() {
        if (frameIndex == 1) {
            return false;
        }
        frameIndex = 1;
        GridHandler.getInstance().addGameObject(this);
        TimerWrapper.getInstance().schedule(new TimerTask() {
            @Override public void run() {
                schedule();
            }
        }, 5000);
        return true;
    }

    private void schedule() {
        EnemyTank[] enemyTanks = {new RegularTank(x, y), new ArmoredTank(x, y), new RegularLuckyTank(x, y),
                new ArmoredLuckyTank(x, y)};
        GridHandler.getInstance().addGameObject(enemyTanks[new Random().nextInt(enemyTanks.length)]);
        GridHandler.getInstance().removeGameObject(this);
        frameIndex = 0;
    }
}
