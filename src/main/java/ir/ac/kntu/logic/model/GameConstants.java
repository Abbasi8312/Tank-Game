package ir.ac.kntu.logic.model;

public class GameConstants {
    public static double TILE_SIZE = 5;

    public static GameStatus gameStatus = GameStatus.RUNNING;

    public static int FRAME_LENGTH = 50; //In millis

    public static int VELOCITY = 100;

    public static int GAME_WIDTH = 800;

    public static int GAME_HEIGHT = 600;

    public enum GameStatus {
        PAUSED,
        RUNNING,
        STOPPED
    }
}
