package main.interfaces;

import model.ShapeColor;
import model.ShapeShadingType;
import view.interfaces.PaintCanvasBase;

public interface IShapes {

    void drawShape(PaintCanvasBase canvasBase);

    IPoints getStart();

    IPoints getEnd();

    ShapeColor getPrimaryColor();

    ShapeColor getSecondaryColor();

    ShapeShadingType getShadingType();

    IDrawStrategy getShapeStrategy();

}
