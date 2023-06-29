package ir.ac.kntu.gamelogic.model.wall;

import ir.ac.kntu.gamelogic.service.CollisionHandler;
import ir.ac.kntu.gamelogic.gameconstants.Direction;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class IronWall extends Wall {
    protected Direction direction = Direction.NONE;

    public IronWall(double x, double y, CollisionHandler collisionHandler) {
        super(x, y, collisionHandler);
    }

    @Override public void draw(GraphicsContext gc) {
        String path = "";
        gc.drawImage(new Image(path), x - width / 2, y - height / 2, width, height);
    }

    public void damage(Direction direction) {

    }
}
