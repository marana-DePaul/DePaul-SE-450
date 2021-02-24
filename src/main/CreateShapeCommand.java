package main;

import main.interfaces.*;
import model.MouseMode;
import model.ShapeType;
import model.interfaces.IApplicationState;

public class CreateShapeCommand implements ICommands, IUndoRedo {
    private final IPoints startPt;
    private final IPoints endPt;
    private final IApplicationState appState;
    private final IShapesRepository shapesRepo;
    private IShapes createdShape;
    private IShapes inShape;

    // appState will be used to determine future specific shape types
    public CreateShapeCommand(IPoints startPt, IPoints endPt, IApplicationState appState, IShapesRepository shapesRepo) {
        this.startPt = startPt;
        this.endPt = endPt;
        this.appState  = appState;
        this.shapesRepo = shapesRepo;
    }

    public CreateShapeCommand(IPoints startPt, IPoints endPt, IApplicationState appState, IShapesRepository shapesRepo, IShapes s) {
        this.startPt = startPt;
        this.endPt = endPt;
        this.appState = appState;
        this.shapesRepo = shapesRepo;
        this.inShape = s;
    }

    @Override
    public void run() {
        // create the shape, add it to the shape list
        IShapesFactory shapeFactory = new ShapesFactory(appState);

        if (inShape == null) {
            if (appState.getActiveShapeType() == ShapeType.RECTANGLE)
                createdShape = shapeFactory.createRectangle(startPt, endPt, appState);

            else if (appState.getActiveShapeType() == ShapeType.TRIANGLE)
                createdShape = shapeFactory.createTriangle(startPt, endPt, appState);

            else if (appState.getActiveShapeType() == ShapeType.ELLIPSE)
                createdShape = shapeFactory.createEllipse(startPt, endPt, appState);
        }
        else
            createdShape = shapeFactory.createCopy(startPt, endPt, inShape);

        // if in select mode, add it to the seleted List
        if (appState.getActiveMouseMode() == MouseMode.SELECT)
            SelectContainer.getSelectedList().add(createdShape);

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
