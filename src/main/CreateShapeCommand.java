package main;

import model.interfaces.IApplicationState;

public class CreateShapeCommand implements ICommands {
    final private IPoints startPt;
    final private IPoints endPt;
    final private IApplicationState appState;
    final private IShapesRepository shapeList;

    // appState will be used to determine future specific shape types
    public CreateShapeCommand(IPoints startPt, IPoints endPt, IApplicationState appState, IShapesRepository shapeList) {
        this.startPt   = startPt;
        this.endPt     = endPt;
        this.appState  = appState;
        this.shapeList = shapeList;
    }

    @Override
    public void run() {
        // create the shape, add it to the shape list
        IShapesFactory shapeFactory = new ShapesFactory();
        IShapes createdShape = shapeFactory.createShape(startPt, endPt, appState);
        shapeList.addShape(createdShape);
    }

}
