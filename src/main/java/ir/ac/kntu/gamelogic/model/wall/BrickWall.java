package ir.ac.kntu.gamelogic.model.wall;

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
            case 1 -> path = "";
            case 2 -> path = "";
            case 3 -> path = "";
            case 4 -> path = "";
            case 5 -> path = "";
            case 6 -> path = "";
            case 7 -> path = "";
            case 8 -> path = "";
            case 9 -> path = "";
            default -> path = "";
        }
        gc.drawImage(new Image(path), x - width / 2, y - height / 2, width, height);
    }

    public void damage(Direction direction) {
        switch (direction) {
            case UP -> upDamage();
            case DOWN -> downDamage();
            case LEFT -> leftDamage();
            case RIGHT -> rightDamage();
            default -> {
            }
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
