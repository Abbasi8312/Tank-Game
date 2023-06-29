package ir.ac.kntu.gamelogic.model.interfaces;

import ir.ac.kntu.gamelogic.model.GameObject;

public interface Collidable {
    boolean isColliding(GameObject gameObject, double velocity);
}
