package main;

import main.interfaces.IDrawStrategy;
import main.interfaces.IPoints;
import main.interfaces.IShapes;
import main.interfaces.IShapesFactory;
import model.MouseMode;
import model.ShapeType;
import model.interfaces.IApplicationState;

public class ShapesFactory implements IShapesFactory {
    private final IApplicationState appState;

    public ShapesFactory (IApplicationState appState) {
        this.appState = appState;
    }

    @Override
    public IShapes createRectangle(IPoints startPt, IPoints endPt, IApplicationState appState) {
        return new GenericShape(startPt, endPt, appState, new RectangleStrategy());
    }

    @Override
    public IShapes createTriangle(IPoints startPt, IPoints endPt, IApplicationState appState) {
        return new GenericShape(startPt, endPt, appState, new TriangleStrategy());
    }

    @Override
    public IShapes createEllipse(IPoints startPt, IPoints endPt, IApplicationState appState) {
        return new GenericShape(startPt, endPt, appState, new EllipseStrategy());
    }

    @Override
    public IShapes createCopy(IPoints startPt, IPoints endPt, IShapes shape) {
        IDrawStrategy strategy = shape.getShapeStrategy();

        if (appState.getActiveMouseMode() == MouseMode.SELECT) {
            if (shape.getShapeType() == ShapeType.RECTANGLE)
                strategy = new RectangleOutlineStrategy();
            else if (shape.getShapeType() == ShapeType.TRIANGLE)
                strategy = new TriangleOutlineStrategy();
            else if (shape.getShapeType() == ShapeType.ELLIPSE)
                strategy = new EllipseOutlineStrategy();
        }

        return new GenericShape(startPt, endPt, shape, strategy);
    }
}
