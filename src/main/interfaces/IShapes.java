package main.interfaces;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import view.interfaces.PaintCanvasBase;

public interface IShapes {

    void drawShape(PaintCanvasBase canvasBase);

    IPoints getStart();

    IPoints getEnd();

    ShapeType getShapeType();

    ShapeColor getPrimaryColor();

    ShapeColor getSecondaryColor();

    ShapeShadingType getShadingType();

    IDrawStrategy getShapeStrategy();

    int getSize();

    void copyShape();

    void pasteShape();

    void deleteShape();

    void groupShape();

    void unGroupShape();

}
