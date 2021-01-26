package main;

import model.interfaces.IApplicationState;

public interface IShapesFactory {
    IShapes createShape(IPoints startPt, IPoints endPt, IApplicationState appState);
}
