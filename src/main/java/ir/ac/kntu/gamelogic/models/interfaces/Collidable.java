package ir.ac.kntu.gamelogic.models.interfaces;

import ir.ac.kntu.gamelogic.models.GameObject;

public interface Collidable {
    boolean isColliding(GameObject gameObject, double velocity);
}
