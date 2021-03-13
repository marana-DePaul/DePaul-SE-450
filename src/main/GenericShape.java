package main;

import main.interfaces.IDrawStrategy;
import main.interfaces.IPoints;
import main.interfaces.IShapes;
import main.interfaces.IShapesRepository;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;
import java.util.List;

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

    // shape is an outline using given strategy
    public GenericShape(IPoints start, IPoints end, IDrawStrategy strategy) {
        this.start = new PointCoord(start);
        this.end   = new PointCoord(end);
        shapeType  = null;
        primaryColor   = null;
        secondaryColor = null;
        shadingType    = null;
        this.shapeStrategy = strategy;
    }

    // shape with identical attributes of given shape
    public GenericShape(IPoints start, IPoints end, IShapes shape) {
        this.start = new PointCoord(start);
        this.end = new PointCoord(end);
        this.shapeType = shape.getShapeType();
        this.primaryColor = shape.getPrimaryColor();
        this.secondaryColor = shape.getSecondaryColor();
        this.shadingType = shape.getShadingType();
        this.shapeStrategy = shape.getShapeStrategy();
    }

    @Override
    public void drawShape(PaintCanvasBase canvasBase) {
        if (canvasBase == null)
            throw new IllegalArgumentException();

        if (primaryColor == null)
            shapeStrategy.drawShapeType(start, end, canvasBase);
        else
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

    @Override
    public void addChild(IShapes shape) { };

    @Override
    public void addToCopyList(List<IShapes> copyList) {
        copyList.add(this);
    }

    @Override
    public IShapes moveShape(int deltaX, int deltaY) {
        IPoints newStart   = new PointCoord(deltaX + start.get_x(), deltaY + start.get_y());
        IPoints newEnd     = new PointCoord(deltaX + end.get_x(), deltaY + end.get_y());

        return new GenericShape(newStart, newEnd, this);
    }

    @Override
    public void unGroupShape(IShapesRepository shapesRepo) {
        shapesRepo.addShape(this);
    }

}
