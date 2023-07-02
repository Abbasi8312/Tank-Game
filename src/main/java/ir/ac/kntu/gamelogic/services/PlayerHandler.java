package ir.ac.kntu.gamelogic.services;

import ir.ac.kntu.gamelogic.models.Player;

public class PlayerHandler {
    private final static PlayerHandler INSTANCE = new PlayerHandler();

    private Player currentPlayer;

    private PlayerHandler() {
    }

    public static PlayerHandler getINSTANCE() {
        return INSTANCE;
    }

    public void addScore(int score) {
        currentPlayer.setScore(currentPlayer.getScore() + score);
        currentPlayer.setHighScore(Math.max(currentPlayer.getScore(), currentPlayer.getHighScore()));
    }

    public int getScore() {
        return currentPlayer.getScore();
    }

    public int getHighScore() {
        return currentPlayer.getHighScore();
    }

    public void addGameCount() {
        currentPlayer.setGameCount(currentPlayer.getGameCount() + 1);
    }

    public int getGameCount() {
        return currentPlayer.getGameCount();
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
