package main.interfaces;

import main.PointCoord;
import model.ShapeColor;
import model.ShapeShadingType;
import view.interfaces.PaintCanvasBase;

public interface IDrawStrategy {
    void drawShapeType(PointCoord startPt, PointCoord endPt, ShapeShadingType shading, ShapeColor color1, ShapeColor color2, PaintCanvasBase canvas);
}
