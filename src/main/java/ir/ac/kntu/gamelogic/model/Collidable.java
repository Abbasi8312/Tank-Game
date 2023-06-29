package ir.ac.kntu.gamelogic.model;

public interface Collidable {
    boolean isColliding(GameObject gameObject, double velocity);
}
