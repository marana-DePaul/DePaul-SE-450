package main.interfaces;

import model.ShapeColor;
import model.ShapeShadingType;
import view.interfaces.PaintCanvasBase;

public interface IDrawStrategy {
    void drawShapeType(IPoints startPt, IPoints endPt, ShapeShadingType shading, ShapeColor color1, ShapeColor color2, PaintCanvasBase canvas);

    void drawShapeType(IPoints start, IPoints end, PaintCanvasBase canvas);
}
