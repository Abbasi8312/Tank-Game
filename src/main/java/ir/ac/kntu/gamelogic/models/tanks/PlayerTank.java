package ir.ac.kntu.gamelogic.models.tanks;

import ir.ac.kntu.gamecontroller.PlayerController;
import ir.ac.kntu.gamelogic.gameconstants.Direction;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class PlayerTank extends Tank {
    private boolean isMoving;

    public PlayerTank(int x, int y) {
        super(x, y);
        PlayerController.getInstance().setPlayer1(this);
        direction = Direction.UP;
        isMoving = false;
    }

    @Override public void move() {
        if (isMoving) {
            super.move();
        } else {
            lastTime = System.nanoTime();
        }
    }

    @Override public void draw(GraphicsContext gc) {
        String path;
        if (frameIndex % 4 < 2) {
            switch (direction) {
                case DOWN -> path = "images/tile004.png";
                case RIGHT -> path = "images/tile006.png";
                case LEFT -> path = "images/tile002.png";
                default -> path = "images/tile000.png";
            }
        } else {
            switch (direction) {
                case DOWN -> path = "images/tile005.png";
                case RIGHT -> path = "images/tile007.png";
                case LEFT -> path = "images/tile003.png";
                default -> path = "images/tile001.png";
            }
        }
        gc.drawImage(new Image(path), x - width / 2, y - height / 2, width, height);
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }
}
