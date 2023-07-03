package ir.ac.kntu.gamelogic.models.walls;

import ir.ac.kntu.gamelogic.gameconstants.Direction;
import ir.ac.kntu.gamelogic.gameconstants.GameConstants;
import ir.ac.kntu.gamelogic.models.GameObject;

public abstract class Wall extends GameObject {
    public Wall(double x, double y) {
        super(x, y);
    }

    public abstract void damage(Direction direction);
}
