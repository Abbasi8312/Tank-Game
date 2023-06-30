package ir.ac.kntu.gamelogic.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<GameObject> gameObjects;

    public Board() {
        gameObjects = new ArrayList<>();
    }

    public Board(List<GameObject> gameObjects) {
        this.gameObjects = new ArrayList<>(gameObjects);
    }

    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    public List<GameObject> getGameObjects() {
        return new ArrayList<>(gameObjects);
    }

    public void setGameObjects(List<GameObject> gameObjects) {
        this.gameObjects = new ArrayList<>(gameObjects);
    }
}
