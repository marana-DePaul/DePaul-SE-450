package main;

public interface IShapesRepository {
    boolean isEmpty();
    void addShape(IShapes shape);
    IShapes removeShape();
    int getNumItems();
}
