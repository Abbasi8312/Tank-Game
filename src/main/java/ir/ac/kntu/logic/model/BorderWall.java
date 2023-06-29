package ir.ac.kntu.logic.model;

import ir.ac.kntu.logic.CollisionHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BorderWall extends GameObject {

    protected BorderWall(double x, double y, CollisionHandler collisionHandler, int id) {
        super(x, y, collisionHandler, id);
        width = 4 * GameConstants.TILE_SIZE;
        height = 4 * GameConstants.TILE_SIZE;
    }

    @Override public void draw(GraphicsContext gc, int frameIndex) {
        gc.drawImage(new Image("images/tile023.png"), x - width / 2, y - height / 2, width, height);
    }
}
