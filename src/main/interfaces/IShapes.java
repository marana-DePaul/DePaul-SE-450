package main.interfaces;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import view.interfaces.PaintCanvasBase;

import java.util.List;

public interface IShapes {

    void drawShape(PaintCanvasBase canvasBase);

    IPoints getStart();

    IPoints getEnd();

    ShapeType getShapeType();

    ShapeColor getPrimaryColor();

    ShapeColor getSecondaryColor();

    ShapeShadingType getShadingType();

    IDrawStrategy getShapeStrategy();

    void addChild(IShapes shape);

    void addToCopyList(List<IShapes> copyList);

    IShapes moveShape(int deltaX, int deltaY);

    void groupShape();

    void unGroupShape();

}
