package main.interfaces;

import java.util.List;

public interface IShapesRepository {
    void addShape(IShapes shape);

    void addShape(int index, IShapes shape);

    IShapes removeShape();

    int removeShape(IShapes item);

    int getNumItems();

    List<IShapes> getShapeList();
}
