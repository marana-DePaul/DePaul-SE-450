package main;

import main.interfaces.IPoints;
import main.interfaces.IShapes;
import main.interfaces.IShapesFactory;
import model.interfaces.IApplicationState;

public class ShapesFactory implements IShapesFactory {
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
}
