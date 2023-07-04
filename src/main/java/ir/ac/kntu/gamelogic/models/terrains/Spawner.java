package ir.ac.kntu.gamelogic.models.terrains;

import ir.ac.kntu.gamelogic.models.GameObject;
import ir.ac.kntu.gamelogic.models.Unit;
import ir.ac.kntu.gamelogic.models.tanks.*;
import ir.ac.kntu.gamelogic.services.CollisionHandler;
import ir.ac.kntu.gamelogic.services.GridHandler;
import ir.ac.kntu.gamelogic.services.TimerWrapper;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Random;
import java.util.TimerTask;

public class Spawner extends GameObject {
    public Spawner(double x, double y) {
        super(x, y);
    }

    @Override public void draw(GraphicsContext gc) {
        if (frameIndex == 1) {
            gc.drawImage(new Image("images/sprites/Terrain/water.png"), x - width / 2, y - height / 2, width, height);
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
                boolean done = scheduleEnemyTank();
                if (done) {
                    cancel();
                }
            }
        }, 1000, 100);
        return true;
    }

    public void respawn(Unit unit) {
        frameIndex = 1;
        GridHandler.getInstance().addGameObject(this);
        TimerWrapper.getInstance().schedule(new TimerTask() {
            @Override public void run() {
                boolean done = schedulePlayer(unit);
                if (done) {
                    cancel();
                }
            }
        }, 1000, 100);
    }

    private boolean scheduleEnemyTank() {
        if (CollisionHandler.getINSTANCE().checkCollision(this) != null) {
            return false;
        }
        EnemyTank[] enemyTanks =
                {new RegularTank(x, y), new ArmoredTank(x, y), new RegularLuckyTank(x, y), new ArmoredLuckyTank(x, y)};
        GridHandler.getInstance().addGameObject(enemyTanks[new Random().nextInt(enemyTanks.length)]);
        GridHandler.getInstance().removeGameObject(this);
        frameIndex = 0;
        return true;
    }

    private boolean schedulePlayer(Unit unit) {
        if (CollisionHandler.getINSTANCE().checkCollision(this) != null) {
            return false;
        }
        GridHandler.getInstance().addGameObject(unit);
        GridHandler.getInstance().removeGameObject(this);
        frameIndex = 0;
        return true;
    }
}
