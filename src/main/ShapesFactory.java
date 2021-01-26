package main;

import model.interfaces.IApplicationState;

public class ShapesFactory implements IShapesFactory {
    public IShapes createRectangle(IPoints startPt, IPoints endPt, IApplicationState appState) {
        return new RectangleShape(startPt, endPt, appState);
    }
}
