package ir.ac.kntu.gamelogic.models.terrains;

import ir.ac.kntu.gamelogic.gamevariables.GameVariables;
import ir.ac.kntu.gamelogic.models.GameObject;
import ir.ac.kntu.gamelogic.services.GridHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Timer;
import java.util.TimerTask;

public class Flag extends GameObject {
    public Flag(double x, double y) {
        super(x, y);
    }

    public void damage() {
        frameIndex++;
        GridHandler.getInstance().updateStatic(this);
        GameVariables.gameStatus = GameVariables.GameStatus.PAUSED;
        new Timer().schedule(new TimerTask() {
            @Override public void run() {
                GameVariables.gameStatus = GameVariables.GameStatus.LOST;
            }
        }, 250);
    }

    @Override public void draw(GraphicsContext gc) {
        String path;
        if (frameIndex == 0) {
            path = "images/sprites/Flag/1.png";
        } else {
            path = "images/sprites/Flag/2.png";
        }
        gc.drawImage(new Image(path), x - width / 2, y - height / 2, width, height);
    }
}
