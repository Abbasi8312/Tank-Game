package ir.ac.kntu.gamelogic.models.terrains;

import ir.ac.kntu.gamelogic.models.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Border extends GameObject {

    public Border(double x, double y) {
        super(x, y);
    }

    @Override public void draw(GraphicsContext gc) {
        gc.setFill(Color.GRAY);
        gc.fillRect(x - width / 2, y - height / 2, width, height);
    }
}
