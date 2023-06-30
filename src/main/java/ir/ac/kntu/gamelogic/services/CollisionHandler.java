package ir.ac.kntu.gamelogic.services;

import ir.ac.kntu.gamelogic.models.GameObject;
import ir.ac.kntu.gamelogic.models.interfaces.Collidable;

public class CollisionHandler {
    private final static CollisionHandler INSTANCE = new CollisionHandler();

    private CollisionHandler() {
    }

    public static CollisionHandler getINSTANCE() {
        return INSTANCE;
    }

    public GameObject checkCollision(Collidable collidable, double velocity) {
        for (GameObject gameObject : BoardHandler.getInstance().getGameObjects()) {
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