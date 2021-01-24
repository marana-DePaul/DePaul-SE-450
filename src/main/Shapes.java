package main;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import view.interfaces.PaintCanvasBase;

public class Shapes implements IShapes {
    protected PointCoord startPt;
    protected PointCoord endPt;
    protected ShapeType  shapeType;
    protected ShapeColor primaryColor;
    protected ShapeColor secondaryColor;
    protected ShapeShadingType shadingType;

    public Shapes () {
        startPt = new PointCoord();
        endPt   = new PointCoord();
        shapeType = ShapeType.RECTANGLE;
        primaryColor   = ShapeColor.GREEN;
        secondaryColor = ShapeColor.ORANGE;
        shadingType = ShapeShadingType.FILLED_IN;
    }

    public void drawShape(PaintCanvasBase canvasBase) { }

}
