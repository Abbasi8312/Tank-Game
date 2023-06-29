package ir.ac.kntu.gamelogic.model;

public class GameConstants {
    public static double TILE_SIZE = 10;

    public static GameStatus gameStatus = GameStatus.RUNNING;

    public static int FRAME_LENGTH = 10; //In millis

    public static int VELOCITY = 25;

    public static int GAME_WIDTH = 800;

    public static int GAME_HEIGHT = 600;

    public enum GameStatus {
        PAUSED,
        RUNNING,
        STOPPED
    }
}
