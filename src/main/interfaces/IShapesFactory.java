package main.interfaces;

import model.ShapeType;
import model.interfaces.IApplicationState;

public interface IShapesFactory {
    IShapes createRectangle(IPoints startPt, IPoints endPt, IApplicationState appState);

    IShapes createTriangle(IPoints startPt, IPoints endPt, IApplicationState appState);

    IShapes createEllipse(IPoints startPt, IPoints endPt, IApplicationState appState);

    IShapes createOutline(IPoints startPt, IPoints endPt, IShapes shape);

    IShapes createOutline(IPoints startPt, IPoints endPt, ShapeType x);
}
