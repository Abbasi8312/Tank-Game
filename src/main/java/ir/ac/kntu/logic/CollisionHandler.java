package ir.ac.kntu.logic;

import ir.ac.kntu.logic.model.Collidable;
import ir.ac.kntu.logic.model.GameObject;

import java.util.List;

public class CollisionHandler {
    private final List<GameObject> gameObjects;

    public CollisionHandler(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public GameObject checkCollision(Collidable collidable) {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.equals(collidable)) {
                continue;
            }
            if (collidable.isCollide(gameObject)) {
                System.out.println("Collided with: " + gameObject.getId());
                return gameObject;
            }
        }
        return null;
    }
}
