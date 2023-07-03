package ir.ac.kntu.gamelogic.models.tanks;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ArmoredLuckyTank extends EnemyTank {
    public ArmoredLuckyTank(double x, double y) {
        super(x, y);
        health = 2;
        damage = 1;
        score = 200;
    }

    @Override public void draw(GraphicsContext gc) {
        String path;
        if (frameIndex % 4 < 2) {
            switch (direction) {
                case DOWN -> path = "images/sprites/Lucky Armored Enemy/down1.png";
                case RIGHT -> path = "images/sprites/Lucky Armored Enemy/right1.png";
                case LEFT -> path = "images/sprites/Lucky Armored Enemy/left1.png";
                default -> path = "images/sprites/Lucky Armored Enemy/up1.png";
            }
        } else {
            switch (direction) {
                case DOWN -> path = "images/sprites/Lucky Armored Enemy/down2.png";
                case RIGHT -> path = "images/sprites/Lucky Armored Enemy/right2.png";
                case LEFT -> path = "images/sprites/Lucky Armored Enemy/left2.png";
                default -> path = "images/sprites/Lucky Armored Enemy/up2.png";
            }
        }
        gc.drawImage(new Image(path), x - width / 2, y - height / 2, width, height);
    }
}
