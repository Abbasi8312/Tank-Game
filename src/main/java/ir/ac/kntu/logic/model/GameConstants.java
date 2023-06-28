package ir.ac.kntu.logic.model;

public class GameConstants {
    public static double TILE_SIZE = 10;

    public static GameStatus gameStatus = GameStatus.RUNNING;

    public static int FRAME_LENGTH = 32; //In millis

    public static int VELOCITY = 50;

    public static int GAME_WIDTH = 800;

    public static int GAME_HEIGHT = 600;

    public enum GameStatus {
        PAUSED,
        RUNNING,
        STOPPED
    }
}
