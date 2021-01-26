package main.interfaces;

public interface IShapesRepository {
    void addShape(IShapes shape);
    IShapes removeShape();
    int getNumItems();
}
