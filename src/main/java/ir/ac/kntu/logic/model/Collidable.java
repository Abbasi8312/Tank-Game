package ir.ac.kntu.logic.model;

public interface Collidable {
    boolean isColliding(GameObject gameObject, double velocity);
}
