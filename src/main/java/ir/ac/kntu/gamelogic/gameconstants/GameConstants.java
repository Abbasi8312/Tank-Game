package ir.ac.kntu.gamelogic.gameconstants;

public class GameConstants {
    public static final double TILE_SIZE = 8 * 4;

    public static final int FRAME_LENGTH = 16; //In millis

    public static final int VELOCITY = 40;

    public static final int GAME_WIDTH = 600;

    public static final int GAME_HEIGHT = 600;

    public static GameStatus gameStatus = GameStatus.RUNNING;

    public enum GameStatus {
        PAUSED,
        RUNNING,
        STOPPED
    }
}
