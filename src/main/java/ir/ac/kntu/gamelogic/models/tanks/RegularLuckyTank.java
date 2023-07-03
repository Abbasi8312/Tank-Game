package ir.ac.kntu.gamelogic.models.tanks;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class RegularLuckyTank extends EnemyTank {
    public RegularLuckyTank(int x, int y) {
        super(x, y);
        health = 1;
        damage = 1;
        score = 100;
    }

    @Override public void draw(GraphicsContext gc) {
        String path;
        if (frameIndex % 4 < 2) {
            switch (direction) {
                case DOWN -> path = "images/sprites/Lucky Lucky Regular Enemy/down1.png";
                case RIGHT -> path = "images/sprites/Lucky Regular Enemy/right1.png";
                case LEFT -> path = "images/sprites/Lucky Regular Enemy/left1.png";
                default -> path = "images/sprites/Lucky Regular Enemy/up1.png";
            }
        } else {
            switch (direction) {
                case DOWN -> path = "images/sprites/Lucky Regular Enemy/down2.png";
                case RIGHT -> path = "images/sprites/Lucky Regular Enemy/right2.png";
                case LEFT -> path = "images/sprites/Lucky Regular Enemy/left2.png";
                default -> path = "images/sprites/Lucky Regular Enemy/up2.png";
            }
        }
        gc.drawImage(new Image(path), x - width / 2, y - height / 2, width, height);
    }
}
