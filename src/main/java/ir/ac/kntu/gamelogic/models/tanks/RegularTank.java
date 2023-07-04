package ir.ac.kntu.gamelogic.models.tanks;

import ir.ac.kntu.gamelogic.gamevariables.GameVariables;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class RegularTank extends EnemyTank {
    public RegularTank(double x, double y) {
        super(x, y);
        health = 1;
        damage = 1;
        score = 100;
    }

    @Override public void die() {
        super.die();
        GameVariables.playerTank1.getDestroyed().addRegular();
    }

    @Override public void draw(GraphicsContext gc) {
        String path;
        if (frameIndex % 4 < 2) {
            switch (direction) {
                case DOWN -> path = "images/sprites/Regular Enemy/down1.png";
                case RIGHT -> path = "images/sprites/Regular Enemy/right1.png";
                case LEFT -> path = "images/sprites/Regular Enemy/left1.png";
                default -> path = "images/sprites/Regular Enemy/up1.png";
            }
        } else {
            switch (direction) {
                case DOWN -> path = "images/sprites/Regular Enemy/down2.png";
                case RIGHT -> path = "images/sprites/Regular Enemy/right2.png";
                case LEFT -> path = "images/sprites/Regular Enemy/left2.png";
                default -> path = "images/sprites/Regular Enemy/up2.png";
            }
        }
        gc.drawImage(new Image(path), x - width / 2, y - height / 2, width, height);
    }
}
