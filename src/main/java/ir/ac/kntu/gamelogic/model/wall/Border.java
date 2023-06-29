package ir.ac.kntu.gamelogic.model.wall;

import ir.ac.kntu.gamelogic.service.CollisionHandler;
import ir.ac.kntu.gamelogic.gameconstants.GameConstants;
import ir.ac.kntu.gamelogic.model.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Border extends GameObject {

    public Border(double x, double y, CollisionHandler collisionHandler) {
        super(x, y, collisionHandler);
        width = 4 * GameConstants.TILE_SIZE;
        height = 4 * GameConstants.TILE_SIZE;
    }

    @Override public void draw(GraphicsContext gc) {
        gc.drawImage(new Image("images/tile023.png"), x - width / 2, y - height / 2, width, height);
    }
}
