package ir.ac.kntu.gamelogic.model.wall;

import ir.ac.kntu.gamelogic.service.CollisionHandler;
import ir.ac.kntu.gamelogic.gameconstants.Direction;
import ir.ac.kntu.gamelogic.gameconstants.GameConstants;
import ir.ac.kntu.gamelogic.model.GameObject;

public abstract class Wall extends GameObject {
    public Wall(double x, double y, CollisionHandler collisionHandler) {
        super(x, y, collisionHandler);
        width = GameConstants.TILE_SIZE;
        height = GameConstants.TILE_SIZE;
        frameIndex = 5;
    }

    public abstract void damage(Direction direction);
}