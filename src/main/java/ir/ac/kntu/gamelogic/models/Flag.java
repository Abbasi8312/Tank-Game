package ir.ac.kntu.gamelogic.models;

import ir.ac.kntu.SceneHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Flag extends GameObject {
    public Flag(double x, double y) {
        super(x, y);
    }

    public void damage() {
        SceneHandler.getINSTANCE().gameOver();
    }

    @Override public void draw(GraphicsContext gc) {
        gc.setFill(Color.YELLOW);
        gc.fillRect(x - width / 2, y - height / 2, width, height);
    }

}
