package ir.ac.kntu.gamelogic.models.tanks;

import ir.ac.kntu.gamelogic.gamevariables.GameVariables;
import ir.ac.kntu.gamelogic.models.elements.TimeFreeze;
import ir.ac.kntu.gamelogic.services.GridHandler;
import ir.ac.kntu.gamelogic.services.CollisionHandler;

import java.util.Random;

public abstract class LuckyTank extends EnemyTank {
    public LuckyTank(double x, double y) {
        super(x, y);
    }

    @Override public void die() {
        super.die();
        double x;
        double y;
        Random random = new Random();
        do {
            x = random.nextInt(1, GameVariables.gameWidth / GameVariables.TILE_SIZE - 1);
            y = random.nextInt(1, GameVariables.gameHeight / GameVariables.TILE_SIZE - 1);
            x = (x + 0.5) * GameVariables.TILE_SIZE;
            y = (y + 0.5) * GameVariables.TILE_SIZE;
        } while (CollisionHandler.getINSTANCE().checkPoint(x, y) != null);
        GridHandler.getInstance().addGameObject(new TimeFreeze(x, y));
    }
}
