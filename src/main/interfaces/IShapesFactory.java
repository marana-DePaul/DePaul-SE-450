package main.interfaces;

import model.interfaces.IApplicationState;

public interface IShapesFactory {
    IShapes createRectangle(IPoints startPt, IPoints endPt, IApplicationState appState);
}
