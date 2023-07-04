package ir.ac.kntu.gamelogic.gamevariables;

public class GameVariables {
    public static final int TILE_SIZE = 16 * 2;

    public static final double VELOCITY = 1.5;

    public static int gameWidth = 600;

    public static int gameHeight = 600;

    public static GameStatus gameStatus = GameStatus.RUNNING;

    public static GameStatus lastStatus;

    public static int stageNumber;

    public static int remainingTanks;

    public enum GameStatus {
        PAUSED,
        RUNNING,
        LOST,
        WON,
        FROZE
    }
}
