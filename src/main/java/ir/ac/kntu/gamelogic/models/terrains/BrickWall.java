package ir.ac.kntu.gamelogic.models.terrains;

import ir.ac.kntu.gamelogic.models.Direction;
import ir.ac.kntu.gamelogic.services.GridHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BrickWall extends Wall {
    public BrickWall(double x, double y) {
        super(x, y);
        frameIndex = 5;
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
        if (frameIndex != 0) {
            gc.drawImage(new Image(path), x - width / 2, y - height / 2, width, height);
        }
    }

    public void damage(Direction direction) {
        switch (direction) {
            case UP -> upDamage();
            case DOWN -> downDamage();
            case LEFT -> leftDamage();
            default -> rightDamage();
        }
        if (frameIndex == 0) {
            GridHandler.getInstance().removeGameObject(this);
        } else {
            GridHandler.getInstance().updateStatic(this);
        }
    }

    private void rightDamage() {
        collisionRect.width /= 2;
        collisionRect.relativeX += width / 2;
        if (frameIndex == 2 || frameIndex == 5 || frameIndex == 8) {
            frameIndex += 1;
        } else {
            frameIndex = 0;
        }
    }

    private void leftDamage() {
        collisionRect.width /= 2;
        collisionRect.relativeX -= width / 2;
        if (frameIndex == 2 || frameIndex == 5 || frameIndex == 8) {
            frameIndex -= 1;
        } else {
            frameIndex = 0;
        }
    }

    private void downDamage() {
        collisionRect.height /= 2;
        collisionRect.relativeX += height / 2;
        if (frameIndex == 4 || frameIndex == 5 || frameIndex == 6) {
            frameIndex -= 3;
        } else {
            frameIndex = 0;
        }
    }

    private void upDamage() {
        collisionRect.height /= 2;
        collisionRect.relativeX -= height / 2;
        if (frameIndex == 4 || frameIndex == 5 || frameIndex == 6) {
            frameIndex += 3;
        } else {
            frameIndex = 0;
        }
    }
}
