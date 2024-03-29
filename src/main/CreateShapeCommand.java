package main;

import main.interfaces.*;
import model.MouseMode;
import model.ShapeType;
import model.interfaces.IApplicationState;

import java.util.List;

public class CreateShapeCommand implements ICommands, IUndoRedo {
    private final IPoints startPt;
    private final IPoints endPt;
    private final IApplicationState appState;
    private final IShapesRepository shapesRepo;
    private IShapes createdShape;
    private IShapes inShape;

    public CreateShapeCommand(IPoints startPt, IPoints endPt, IApplicationState appState, IShapesRepository shapesRepo) {
        if (startPt == null || endPt == null || appState == null) throw new IllegalArgumentException();

        this.startPt = startPt;
        this.endPt = endPt;
        this.appState  = appState;
        this.shapesRepo = shapesRepo;
    }
    
    public CreateShapeCommand(IPoints startPt, IPoints endPt, IApplicationState appState, IShapesRepository shapesRepo, IShapes s) {
        if (startPt == null || endPt == null || appState == null || s == null) throw new IllegalArgumentException();

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
                createdShape = shapeFactory.createRectangle(startPt, endPt);

            else if (appState.getActiveShapeType() == ShapeType.TRIANGLE)
                createdShape = shapeFactory.createTriangle(startPt, endPt);

            else if (appState.getActiveShapeType() == ShapeType.ELLIPSE)
                createdShape = shapeFactory.createEllipse(startPt, endPt);
        }
        else {
            List<IShapes> outlines = SharedContainers.getInstance().getOutlineList();
            createdShape = shapeFactory.createOutlineCopy(startPt, endPt, inShape);
            outlines.add(createdShape);
        }

        // if in select mode, add it to the seleted List
        if (appState.getActiveMouseMode() == MouseMode.SELECT) {
            SharedContainers.getInstance().getSelectList().add(createdShape);
            SharedContainers.getInstance().getPrevSelectList().add(createdShape);
            SharedContainers.getInstance().getOutlineList().add(createdShape);
        }

        shapesRepo.addShape(createdShape);
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
