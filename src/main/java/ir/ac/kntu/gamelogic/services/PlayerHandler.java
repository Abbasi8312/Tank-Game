package ir.ac.kntu.gamelogic.services;

import ir.ac.kntu.gamelogic.models.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerHandler {
    private final static PlayerHandler INSTANCE = new PlayerHandler();

    private Player currentPlayer;

    private PlayerHandler() {
        currentPlayer = new Player("Ali");
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

    public List<Player> getCurrentPlayers() {
        List<Player> players = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream("src/main/data/player.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            players = (List<Player>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return players;
    }

    public void savePlayer() {
        List<Player> players = getCurrentPlayers();
        try (FileOutputStream fileOut = new FileOutputStream("src/main/data/player.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            players.add(currentPlayer);
            out.writeObject(players);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
