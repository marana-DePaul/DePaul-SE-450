package main;

import model.interfaces.IApplicationState;

public class ShapesFactory implements IShapesFactory {
    public IShapes createShape(IPoints startPt, IPoints endPt, IApplicationState appState) {
        return new Shapes(startPt,endPt,appState);
    }
}
