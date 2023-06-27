package ir.ac.kntu.logic.model;

public class GameConstants {
    public static int COLLISION_BOX_LENGTH = 90;

    public static GameStatus gameStatus = GameStatus.RUNNING;

    public static int velocity = 5;

    public enum GameStatus {
        PAUSED,
        RUNNING,
        STOPPED
    }
}
