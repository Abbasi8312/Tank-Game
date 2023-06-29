package ir.ac.kntu.gamelogic.service;

import ir.ac.kntu.gamelogic.model.interfaces.Collidable;
import ir.ac.kntu.gamelogic.model.GameObject;

import java.util.List;

public class CollisionHandler {
    private final List<GameObject> gameObjects;

    public CollisionHandler(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public GameObject checkCollision(Collidable collidable, double velocity) {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.equals(collidable)) {
                continue;
            }
            if (collidable.isColliding(gameObject, velocity)) {
                //System.out.println("Collided with: " + gameObject.getId());
                return gameObject;
            }
        }
        return null;
    }
}
