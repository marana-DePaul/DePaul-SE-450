package main.interfaces;

import java.util.List;

public interface IShapesDrawer {
    void drawAllShapes(List<IShapes> shapesList);
    void clearAllShapes();
}
