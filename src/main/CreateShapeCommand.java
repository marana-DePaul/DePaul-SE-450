package main;

import main.interfaces.*;
import model.ShapeType;
import model.interfaces.IApplicationState;

public class CreateShapeCommand implements ICommands, IUndoRedo {
    final private IPoints startPt;
    final private IPoints endPt;
    final private IApplicationState appState;
    final private IShapesRepository shapeList;
    private IShapes createdShape;

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

        if (appState.getActiveShapeType() == ShapeType.RECTANGLE) {
            createdShape = shapeFactory.createRectangle(startPt, endPt, appState);
            shapeList.addShape(createdShape);
            CommandHistory.add(this);
        }
    }

    @Override
    public void undo() {
        shapeList.removeShape();
    }

    @Override
    public void redo() {
        shapeList.addShape(createdShape);
    }




}
