package main.interfaces;

import model.interfaces.IApplicationState;

public interface IShapesFactory {
    IShapes createRectangle(IPoints startPt, IPoints endPt, IApplicationState appState);

    IShapes createTriangle(IPoints startPt, IPoints endPt, IApplicationState appState);

    IShapes createEllipse(IPoints startPt, IPoints endPt, IApplicationState appState);

    IShapes createCopy(IPoints startPt, IPoints endPt, IShapes shape);
}
