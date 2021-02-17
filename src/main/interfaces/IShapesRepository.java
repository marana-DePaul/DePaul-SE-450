package main.interfaces;

import java.util.List;

public interface IShapesRepository {
    void addShape(IShapes shape);

    IShapes removeShape();

    void removeShape(IShapes item);

    int getNumItems();

    List<IShapes> getShapeList();
}
