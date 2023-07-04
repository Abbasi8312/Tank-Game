package ir.ac.kntu.gamelogic.models.elements;

import ir.ac.kntu.gamelogic.gamevariables.GameVariables;
import ir.ac.kntu.gamelogic.models.tanks.PlayerTank;
import ir.ac.kntu.gamelogic.services.TimerWrapper;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.TimerTask;

public class TimeFreeze extends Element {
    private static int timeRemaining = 0;

    public final TimerTask freezeTime = new TimerTask() {
        @Override public void run() {
            timeRemaining -= 5000;
            if (timeRemaining == 0) {
                GameVariables.gameStatus = GameVariables.GameStatus.RUNNING;
                cancel();
            }
        }
    };

    public TimeFreeze(double x, double y) {
        super(x, y);
    }

    @Override public void draw(GraphicsContext gc) {
        if (frameIndex == 0) {
            String path = "images/sprites/Lucky Elements/time.png";
            gc.drawImage(new Image(path), x - width / 2, y - height / 2, width, height);
        }
    }

    @Override public void apply(PlayerTank playerTank) {
        GameVariables.gameStatus = GameVariables.GameStatus.FROZE;
        remove();
        timeRemaining += 5000;
        if (timeRemaining == 5000) {
            TimerWrapper.getInstance().schedule(freezeTime, 5000, 5000);
        }
    }
}
