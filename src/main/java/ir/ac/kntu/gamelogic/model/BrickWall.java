package ir.ac.kntu.gamelogic.model;

import ir.ac.kntu.gamelogic.CollisionHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BrickWall extends GameObject {
    protected BrickWall(double x, double y, CollisionHandler collisionHandler, int id) {
        super(x, y, collisionHandler, id);
        width = GameConstants.TILE_SIZE;
        height = GameConstants.TILE_SIZE;}

    @Override public void draw(GraphicsContext gc) {
        gc.drawImage(new Image("images/tile023.png"), x - width / 2, y - height / 2, width, height);
    }
}
