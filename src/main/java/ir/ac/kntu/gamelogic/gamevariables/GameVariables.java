package ir.ac.kntu.gamelogic.gamevariables;

public class GameVariables {
    public static final int TILE_SIZE = 16 * 2;

    public static final double VELOCITY = 1.5;

    public static int gameWidth = 600;

    public static int gameHeight = 600;

    public static GameStatus gameStatus = GameStatus.RUNNING;

    public enum GameStatus {
        PAUSED,
        RUNNING,
        STOPPED,
        FROZE
    }
}
