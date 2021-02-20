package main;

import main.interfaces.*;
import model.ShapeType;
import model.interfaces.IApplicationState;

public class CreateShapeCommand implements ICommands, IUndoRedo {
    private final IPoints startPt;
    private final IPoints endPt;
    private final IApplicationState appState;
    private final IShapesRepository shapesRepo;
    private IShapes createdShape;

    // appState will be used to determine future specific shape types
    public CreateShapeCommand(IPoints startPt, IPoints endPt, IApplicationState appState, IShapesRepository shapesRepo) {
        this.startPt = startPt;
        this.endPt = endPt;
        this.appState  = appState;
        this.shapesRepo = shapesRepo;
    }

    @Override
    public void run() {
        // create the shape, add it to the shape list
        IShapesFactory shapeFactory = new ShapesFactory();

        if (appState.getActiveShapeType() == ShapeType.RECTANGLE)
            createdShape = shapeFactory.createRectangle(startPt, endPt, appState);

        else if (appState.getActiveShapeType() == ShapeType.TRIANGLE)
            createdShape = shapeFactory.createTriangle(startPt, endPt, appState);

        else if (appState.getActiveShapeType() == ShapeType.ELLIPSE)
            createdShape = shapeFactory.createEllipse(startPt, endPt, appState);

        shapesRepo.addShape(createdShape);
        //System.out.println("NumShapes-> " + shapeList.getNumItems());
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        shapesRepo.removeShape();
    }

    @Override
    public void redo() {
        shapesRepo.addShape(createdShape);
    }
}
