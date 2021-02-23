package main;

import main.interfaces.IDrawStrategy;
import main.interfaces.IPoints;
import main.interfaces.IShapes;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;

public class GenericShape implements IShapes {
    private final IPoints           start;
    private final IPoints           end;
    private final ShapeType         shapeType;
    private final ShapeColor        primaryColor;
    private final ShapeColor        secondaryColor;
    private final ShapeShadingType  shadingType;
    private final IDrawStrategy     shapeStrategy;
    
    public GenericShape (IPoints start, IPoints end, IApplicationState appState, IDrawStrategy strategy) {
        this.start = new PointCoord(start);
        this.end = new PointCoord(end);
        this.shapeType = appState.getActiveShapeType();
        this.primaryColor   = appState.getActivePrimaryColor();
        this.secondaryColor = appState.getActiveSecondaryColor();
        this.shadingType    = appState.getActiveShapeShadingType();
        this.shapeStrategy  = strategy;
    }

    public GenericShape() {
        start = new PointCoord(100,100);
        end   = new PointCoord(400,400);
        shapeType = ShapeType.RECTANGLE;
        primaryColor = ShapeColor.GREEN;
        secondaryColor = ShapeColor.ORANGE;
        shadingType = ShapeShadingType.FILLED_IN;
        shapeStrategy = new RectangleStrategy();
    }

    public GenericShape(IPoints start, IPoints end, IShapes shape) {
        this.start = new PointCoord(start);
        this.end = new PointCoord(end);
        this.shapeType = shape.getShapeType();
        this.primaryColor = shape.getPrimaryColor();
        this.secondaryColor = shape.getSecondaryColor();
        this.shadingType = shape.getShadingType();
        this.shapeStrategy = shape.getShapeStrategy();
    }

    public GenericShape(IPoints start, IPoints end, IShapes shape, IDrawStrategy strategy) {
        this.start = new PointCoord(start);
        this.end = new PointCoord(end);
        this.shapeType = shape.getShapeType();
        this.primaryColor = shape.getPrimaryColor();
        this.secondaryColor = shape.getSecondaryColor();
        this.shadingType = shape.getShadingType();
        this.shapeStrategy = strategy;
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

    @Override
    public ShapeType getShapeType() { return shapeType; }

    @Override
    public ShapeColor getPrimaryColor() {return primaryColor;}

    @Override
    public ShapeColor getSecondaryColor() {return secondaryColor;}

    @Override
    public ShapeShadingType getShadingType() {return shadingType;}

    @Override
    public IDrawStrategy getShapeStrategy() {return  shapeStrategy;}
}
