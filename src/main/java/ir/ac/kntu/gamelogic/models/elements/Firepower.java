package ir.ac.kntu.gamelogic.models.elements;

import ir.ac.kntu.gamelogic.models.tanks.PlayerTank;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Firepower extends Element {
    public Firepower(double x, double y) {
        super(x, y);
    }

    @Override public void draw(GraphicsContext gc) {
        if (frameIndex == 0) {
            String path = "images/sprites/Lucky Elements/firepower.png";
            gc.drawImage(new Image(path), x - width / 2, y - height / 2, width, height);
        }
    }

    @Override public void apply(PlayerTank playerTank) {
        remove();
        playerTank.setDamage(2);
    }
}
