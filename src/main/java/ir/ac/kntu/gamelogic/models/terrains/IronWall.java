package ir.ac.kntu.gamelogic.models.terrains;

import ir.ac.kntu.gamelogic.models.Direction;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class IronWall extends Wall {
    public IronWall(double x, double y) {
        super(x, y);
    }

    @Override public void draw(GraphicsContext gc) {
        String path = "images/sprites/Terrain/iron.png";
        gc.drawImage(new Image(path), x - width / 2, y - height / 2, width, height);
    }

    public void damage(Direction direction) {
    }
}
