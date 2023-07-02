package ir.ac.kntu.gamelogic.models;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;

    private int score;

    private int highScore;

    private int gameCount;

    public Player(String name) {
        this.name = name;
        score = 0;
        gameCount = 0;
        highScore = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGameCount() {
        return gameCount;
    }

    public void setGameCount(int gameCount) {
        this.gameCount = gameCount;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
