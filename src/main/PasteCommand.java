package main;

import main.interfaces.*;

import java.util.List;

public class PasteCommand implements ICommands, IUndoRedo {
    private final List<IShapes> copyList;
    private final IShapesRepository shapesRepo;

    public PasteCommand (IShapesRepository shapeRepo) {
        if (shapeRepo == null) throw new IllegalArgumentException();

        this.copyList = SelectContainer.getCopyList();
        this.shapesRepo = shapeRepo;
    }

    @Override
    public void run() {

        // for every copied shape, clone a new shape at an offset from the origin
        for (IShapes x : copyList) {
            IPoints startPt = new PointCoord(x.getStart().get_x() + 65,x.getStart().get_y() + 65);
            IPoints endPt = new PointCoord(x.getEnd().get_x() + 65, x.getEnd().get_y() + 65);
            IShapes copiedShape = new GenericShape(startPt, endPt, x);

            shapesRepo.addShape(copiedShape);
        }

        //System.out.println("Shapes copied -> " + shapesRepo.getNumItems());
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        int numCopy = SelectContainer.getCopyList().size();

        for (int i = 0; i < numCopy; i++)
            shapesRepo.removeShape();

    }

    @Override
    public void redo() {

        for (IShapes x : copyList) {
            IPoints startPt = new PointCoord(x.getStart().get_x() + 65,x.getStart().get_y() + 65);
            IPoints endPt = new PointCoord(x.getEnd().get_x() + 65, x.getEnd().get_y() + 65);
            IShapes copiedShape = new GenericShape(startPt, endPt, x);

            shapesRepo.addShape(copiedShape);
            //System.out.println("Shapes copied -> " + shapesRepo.getNumItems());
        }

    }

}
