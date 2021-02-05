package main;

import main.interfaces.IDrawStrategy;
import main.interfaces.IPoints;
import main.interfaces.IShapes;
import model.ShapeColor;
import model.ShapeShadingType;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;

public class GenericShape implements IShapes {
    private PointCoord          startPt;
    private PointCoord          endPt;
    private ShapeColor          primaryColor;
    private ShapeColor          secondaryColor;
    private ShapeShadingType    shadingType;
    private IDrawStrategy       shapeStrategy;
    
    public GenericShape (IPoints startPt, IPoints endPt, IApplicationState appState, IDrawStrategy strategy) {
        this.startPt        = new PointCoord(startPt.get_x(), startPt.get_y());
        this.endPt          = new PointCoord(endPt.get_x(), endPt.get_y());
        this.primaryColor   = appState.getActivePrimaryColor();
        this.secondaryColor = appState.getActiveSecondaryColor();
        this.shadingType    = appState.getActiveShapeShadingType();
        this.shapeStrategy  = strategy;
    }

    public GenericShape() {
        startPt = new PointCoord(100,100);
        endPt   = new PointCoord(400,400);
        primaryColor = ShapeColor.GREEN;
        secondaryColor = ShapeColor.ORANGE;
        shadingType = ShapeShadingType.FILLED_IN;
        shapeStrategy = new RectangleStrategy();
    }

    @Override
    public void drawShape(PaintCanvasBase canvasBase) {
        if (canvasBase == null)
            throw new IllegalArgumentException();

        shapeStrategy.drawShapeType(startPt, endPt, shadingType, primaryColor, secondaryColor, canvasBase);
    }

}
