package ir.ac.kntu.gamelogic;

import ir.ac.kntu.gamelogic.models.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private List<GameObject> gameObjects;

    public Grid() {
        gameObjects = new ArrayList<>();
    }

    public Grid(List<GameObject> gameObjects) {
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

    public void clear() {
        gameObjects.clear();
    }
}
