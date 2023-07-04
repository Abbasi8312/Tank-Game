package ir.ac.kntu.gamelogic.models.terrains;

import ir.ac.kntu.gamelogic.gamevariables.GameVariables;
import ir.ac.kntu.gamelogic.models.GameObject;
import ir.ac.kntu.gamelogic.models.Unit;
import ir.ac.kntu.gamelogic.models.tanks.*;
import ir.ac.kntu.gamelogic.services.CollisionHandler;
import ir.ac.kntu.gamelogic.services.GridHandler;
import ir.ac.kntu.gamelogic.services.TimerWrapper;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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
        --GameVariables.remainingTanks;
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
        if (CollisionHandler.getInstance().checkCollision(this) != null) {
            return false;
        }
        EnemyTank[] enemyTanks = new EnemyTank[4];
        enemyTanks[0] = new RegularTank(x, y);
        enemyTanks[1] = new ArmoredTank(x, y);
        enemyTanks[2] = new RegularLuckyTank(x, y);
        enemyTanks[3] = new ArmoredLuckyTank(x, y);
        GridHandler.getInstance().addGameObject(enemyTanks[new Random().nextInt(enemyTanks.length)]);
        GridHandler.getInstance().removeGameObject(this);
        frameIndex = 0;
        return true;
    }

    private boolean schedulePlayer(Unit unit) {
        if (CollisionHandler.getInstance().checkCollision(this) != null) {
            return false;
        }
        GridHandler.getInstance().addGameObject(unit);
        GridHandler.getInstance().removeGameObject(this);
        frameIndex = 0;
        return true;
    }
}
