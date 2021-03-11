package main.interfaces;

public interface IShapesFactory {
    IShapes createRectangle(IPoints startPt, IPoints endPt);

    IShapes createTriangle(IPoints startPt, IPoints endPt);

    IShapes createEllipse(IPoints startPt, IPoints endPt);

    IShapes createGroup(IPoints startPt, IPoints endPt);

    IShapes createOutlineCopy(IPoints startPt, IPoints endPt, IShapes shape);

}
