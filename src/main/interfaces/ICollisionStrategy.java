package main.interfaces;

public interface ICollisionStrategy {

    boolean detectCollision(IPoints collStart, IPoints collEnd, IShapes shape);
}
