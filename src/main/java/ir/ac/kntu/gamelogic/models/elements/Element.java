package ir.ac.kntu.gamelogic.models.elements;

import ir.ac.kntu.gamelogic.models.GameObject;
import ir.ac.kntu.gamelogic.models.tanks.PlayerTank;
import ir.ac.kntu.gamelogic.services.GridHandler;
import ir.ac.kntu.gamelogic.services.TimerWrapper;

import java.util.TimerTask;

public abstract class Element extends GameObject {
    public Element(double x, double y) {
        super(x, y);

        TimerWrapper.getInstance().schedule(new TimerTask() {
            @Override public void run() {
                remove();
            }
        }, 5000);
    }

    public void remove() {
        GridHandler.getInstance().removeGameObject(this);
        frameIndex = 1;
    }

    public abstract void apply(PlayerTank playerTank);
}
