package ir.ac.kntu.graphics;

import ir.ac.kntu.gamelogic.model.Board;
import ir.ac.kntu.gamelogic.gameconstants.GameConstants;
import ir.ac.kntu.gamelogic.model.GameObject;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawObjects {
    private final Board board;

    private final GraphicsContext staticGC;

    private final GraphicsContext movingGC;

    public DrawObjects(Board board, GraphicsContext staticGC, GraphicsContext movingGC) {
        this.board = board;
        this.staticGC = staticGC;
        this.movingGC = movingGC;
    }

    private void drawStatics() {
        staticGC.clearRect(0, 0, GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);
        staticGC.setFill(Color.BLACK);
        staticGC.fillRect(0, 0, GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);
        for (GameObject gameObject : board.getGameObjects()) {
            if (!board.getMovables().contains(gameObject)) {
                gameObject.draw(staticGC);
            }
        }
    }

    private void drawMovables() {
        movingGC.clearRect(0, 0, GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);
        for (GameObject gameObject : board.getMovables()) {
            gameObject.draw(movingGC);
        }
    }

    public void start() {
        drawStatics();
        new AnimationTimer() {
            long lastTime = System.currentTimeMillis();
            int frameCount = 0;

            @Override public void handle(long currentNanoTime) {
                if (System.currentTimeMillis() - lastTime < 1000) {
                    frameCount++;
                } else {
                    lastTime += 1000;
                    System.out.println(frameCount);
                    frameCount = 0;
                }
                drawMovables();
            }
        }.start();
    }
}
