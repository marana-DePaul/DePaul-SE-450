package main;

import main.interfaces.IPoints;
import main.interfaces.IShapes;
import main.interfaces.IShapesFactory;

import model.interfaces.IApplicationState;

public class ShapesFactory implements IShapesFactory {
    private final IApplicationState appState;

    public ShapesFactory (IApplicationState appState) {
        this.appState = appState;
    }

    @Override
    public IShapes createRectangle(IPoints startPt, IPoints endPt) {
        return new GenericShape(startPt, endPt, appState, new RectangleStrategy());
    }

    @Override
    public IShapes createTriangle(IPoints startPt, IPoints endPt) {
        return new GenericShape(startPt, endPt, appState, new TriangleStrategy());
    }

    @Override
    public IShapes createEllipse(IPoints startPt, IPoints endPt) {
        return new GenericShape(startPt, endPt, appState, new EllipseStrategy());
    }

    @Override
    public IShapes createGroup(IPoints startPt, IPoints endPt) {
        return new GroupShape(startPt, endPt, new RectangleStrategy());
    }

    @Override
    public IShapes createOutlineCopy(IPoints startPt, IPoints endPt, IShapes shape) {
        return new GenericShape(startPt, endPt, shape.getShapeStrategy());
    }

}
