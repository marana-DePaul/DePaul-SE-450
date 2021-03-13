package main;


import main.interfaces.IDrawStrategy;
import main.interfaces.IPoints;
import main.interfaces.IShapes;
import main.interfaces.IShapesRepository;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;
import java.util.List;

public class GroupShape implements IShapes {
    private final IPoints start;
    private final IPoints end;
    private final List<IShapes> children;
    private final IDrawStrategy shapeStrategy;

    public GroupShape(IPoints start, IPoints end, IDrawStrategy shapeStrategy) {
        this.start = new PointCoord(start);
        this.end = new PointCoord(end);
        this.children = new ArrayList<>();
        this.shapeStrategy = shapeStrategy;
    }

    @Override
    public void addChild(IShapes shape) {
        children.add(shape);
    }

    @Override
    public void addToCopyList(List<IShapes> copyList) {
        for (IShapes s : children)
            copyList.add(s);

        copyList.add(this);
    }

    @Override
    public IShapes moveShape(int deltaX, int deltaY) {
        List<IShapes> oldChildren = new ArrayList<>();
        IPoints newStart = new PointCoord(deltaX + start.get_x(), deltaY + start.get_y());
        IPoints newEnd   = new PointCoord(deltaX + end.get_x(), deltaY + end.get_y());
        IShapes deltaGroup = new GroupShape(newStart, newEnd, shapeStrategy);

        for (IShapes s : children)
            oldChildren.add(s);

        children.clear();

        for (IShapes s : oldChildren) {
            deltaGroup.addChild(s.moveShape(deltaX, deltaY));
        }

        return deltaGroup;
    }

    @Override
    public void groupShape() {}

    @Override
    public void unGroupShape() {}

    @Override
    public void drawShape(PaintCanvasBase canvasBase) {
        if (canvasBase == null)
            throw new IllegalArgumentException();

        for (IShapes s : children)
            s.drawShape(canvasBase);

        shapeStrategy.drawShapeType(start, end, canvasBase);
    }

    @Override
    public IPoints getStart() { return this.start; }

    @Override
    public IPoints getEnd() {
        return this.end;
    }

    @Override
    public IDrawStrategy getShapeStrategy() {return this.shapeStrategy;}

    @Override
    public ShapeType getShapeType() { return null; }

    @Override
    public ShapeColor getPrimaryColor() {return null;}

    @Override
    public ShapeColor getSecondaryColor() {return null;}

    @Override
    public ShapeShadingType getShadingType() {return null;}


}
