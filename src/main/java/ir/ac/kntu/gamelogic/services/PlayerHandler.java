package ir.ac.kntu.gamelogic.services;

import ir.ac.kntu.gamelogic.models.Player;

import java.util.List;

public class PlayerHandler {
    private final static PlayerHandler INSTANCE = new PlayerHandler();

    private Player currentPlayer;

    private PlayerHandler() {
    }

    public static PlayerHandler getINSTANCE() {
        return INSTANCE;
    }

    public Player newPlayer(String name) {
        return new Player(name);
    }

    public String getName() {
        return currentPlayer.getName();
    }

    public void addScore(int score) {
        currentPlayer.setScore(currentPlayer.getScore() + score);
    }

    public void resetScore() {
        currentPlayer.setScore(0);
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

    public void updatePlayer() {
        List<Player> players = DataHandler.getINSTANCE().getPlayers();
        players.remove(currentPlayer);
        players.add(currentPlayer);
        currentPlayer.setHighScore(Math.max(currentPlayer.getScore(), currentPlayer.getHighScore()));
        players.sort((a,b) -> b.getHighScore() - a.getHighScore());
        DataHandler.getINSTANCE().savePlayers(players);
    }
}
