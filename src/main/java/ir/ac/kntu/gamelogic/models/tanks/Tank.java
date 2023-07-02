package ir.ac.kntu.gamelogic.models.tanks;

import ir.ac.kntu.gamelogic.models.Bullet;
import ir.ac.kntu.gamelogic.models.Unit;
import ir.ac.kntu.gamelogic.services.BoardHandler;

public abstract class Tank extends Unit {
    public Tank(double x, double y) {
        super(x, y);
    }

    public void fire() {
        double x = this.x;
        double y = this.y;
        switch (direction) {
            case RIGHT -> x += width;
            case LEFT -> x -= width;
            case DOWN -> y += height;
            case UP -> y -= height;
            default -> {
            }
        }
        Bullet bullet = new Bullet(x, y, damage, direction, Bullet.Origin.ENEMY);
        BoardHandler.getInstance().addGameObject(bullet);
    }
}
