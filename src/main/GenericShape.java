package main;

import main.interfaces.IDrawStrategy;
import main.interfaces.IPoints;
import main.interfaces.IShapes;
import model.ShapeColor;
import model.ShapeShadingType;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;

public class GenericShape implements IShapes {
    private IPoints           start;
    private IPoints           end;
    private ShapeColor        primaryColor;
    private ShapeColor        secondaryColor;
    private ShapeShadingType  shadingType;
    private IDrawStrategy     shapeStrategy;
    
    public GenericShape (IPoints start, IPoints end, IApplicationState appState, IDrawStrategy strategy) {
        this.start = new PointCoord(start);
        this.end = new PointCoord(end);
        this.primaryColor   = appState.getActivePrimaryColor();
        this.secondaryColor = appState.getActiveSecondaryColor();
        this.shadingType    = appState.getActiveShapeShadingType();
        this.shapeStrategy  = strategy;
    }

    public GenericShape() {
        start = new PointCoord(100,100);
        end   = new PointCoord(400,400);
        primaryColor = ShapeColor.GREEN;
        secondaryColor = ShapeColor.ORANGE;
        shadingType = ShapeShadingType.FILLED_IN;
        shapeStrategy = new RectangleStrategy();
    }

    @Override
    public void drawShape(PaintCanvasBase canvasBase) {
        if (canvasBase == null)
            throw new IllegalArgumentException();

        shapeStrategy.drawShapeType(start, end, shadingType, primaryColor, secondaryColor, canvasBase);
    }

    @Override
    public IPoints getStart() {
        return start;
    }

    @Override
    public IPoints getEnd() {
        return end;
    }

}
