package ir.ac.kntu.gamelogic.models.walls;

import ir.ac.kntu.gamelogic.gameconstants.Direction;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BrickWall extends Wall {
    protected Direction direction = Direction.NONE;

    public BrickWall(double x, double y) {
        super(x, y);
    }

    public BrickWall(double x, double y, int frameIndex) {
        super(x, y);
        this.frameIndex = frameIndex;
    }

    @Override public void draw(GraphicsContext gc) {
        String path;
        switch (frameIndex) {
            case 1 -> path = "images/sprites/Terrain/brick1.png";
            case 2 -> path = "images/sprites/Terrain/brick2.png";
            case 3 -> path = "images/sprites/Terrain/brick3.png";
            case 4 -> path = "images/sprites/Terrain/brick4.png";
            case 5 -> path = "images/sprites/Terrain/brick5.png";
            case 6 -> path = "images/sprites/Terrain/brick6.png";
            case 7 -> path = "images/sprites/Terrain/brick7.png";
            case 8 -> path = "images/sprites/Terrain/brick8.png";
            default -> path = "images/sprites/Terrain/brick9.png";
        }
        gc.drawImage(new Image(path), x - width / 2, y - height / 2, width, height);
    }

    public void damage(Direction direction) {
        switch (direction) {
            case UP -> upDamage();
            case DOWN -> downDamage();
            case LEFT -> leftDamage();
            default -> rightDamage();
        }
    }

    private void rightDamage() {
        width /= 2;
        x += width / 2;
        if (frameIndex == 2 || frameIndex == 5 || frameIndex == 8) {
            frameIndex += 1;
        }
    }

    private void leftDamage() {
        width /= 2;
        x -= width / 2;
        if (frameIndex == 2 || frameIndex == 5 || frameIndex == 8) {
            frameIndex -= 1;
        }
    }

    private void downDamage() {
        height /= 2;
        y += height / 2;
        if (frameIndex == 4 || frameIndex == 5 || frameIndex == 6) {
            frameIndex -= 3;
        }
    }

    private void upDamage() {
        height /= 2;
        y -= height / 2;
        if (frameIndex == 4 || frameIndex == 5 || frameIndex == 6) {
            frameIndex += 3;
        }
    }
}
