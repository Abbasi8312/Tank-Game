package ir.ac.kntu.gamelogic.models.tanks;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class RegularTank extends EnemyTank {
    public RegularTank(int x, int y) {
        super(x, y);
    }

    @Override public void draw(GraphicsContext gc) {
        ++frameIndex;
        String path;
        if (frameIndex % 4 < 2) {
            switch (direction) {
                case UP -> path = "images/tile000.png";
                case DOWN -> path = "images/tile004.png";
                case RIGHT -> path = "images/tile006.png";
                default -> path = "images/tile002.png";
            }
        } else {
            switch (direction) {
                case UP -> path = "images/tile001.png";
                case DOWN -> path = "images/tile005.png";
                case RIGHT -> path = "images/tile007.png";
                default -> path = "images/tile003.png";
            }
        }
        gc.drawImage(new Image(path), x - width / 2, y - height / 2, width, height);
    }
}
