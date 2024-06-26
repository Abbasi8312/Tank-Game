package ir.ac.kntu.gamelogic.services;

import ir.ac.kntu.gamelogic.models.GameObject;
import ir.ac.kntu.gamelogic.models.interfaces.Collidable;

public class CollisionHandler {
    private final static CollisionHandler INSTANCE = new CollisionHandler();

    private CollisionHandler() {
    }

    public static CollisionHandler getInstance() {
        return INSTANCE;
    }

    public GameObject checkCollision(Collidable collidable) {
        for (GameObject gameObject : GridHandler.getInstance().getGameObjects()) {
            if (gameObject.equals(collidable)) {
                continue;
            }
            if (collidable.isColliding(gameObject)) {
                //System.out.println("Collided with: " + gameObject.getId());
                return gameObject;
            }
        }
        return null;
    }

    public GameObject checkPoint(double x, double y) {
        for (GameObject gameObject : GridHandler.getInstance().getGameObjects()) {
            if (gameObject.isAtPoint(x, y)) {
                return gameObject;
            }
        }
        return null;
    }
}
