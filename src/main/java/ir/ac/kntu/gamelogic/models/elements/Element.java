package ir.ac.kntu.gamelogic.models.elements;

import ir.ac.kntu.gamelogic.models.GameObject;
import ir.ac.kntu.gamelogic.models.tanks.PlayerTank;

public abstract class Element extends GameObject {
    public Element(double x, double y) {
        super(x, y);
    }

    public abstract void apply(PlayerTank playerTank);
}
