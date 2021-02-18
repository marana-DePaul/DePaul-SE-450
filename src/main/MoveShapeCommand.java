package main;

import main.interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class MoveShapeCommand implements ICommands, IUndoRedo {
    private final int deltaX;
    private final int deltaY;
    private final List<IShapes> selectedList;
    private final List<IShapes> prevSelected;
    private final IShapesRepository shapesList;

    public MoveShapeCommand (int deltaX, int deltaY, List<IShapes> selectedList, List<IShapes> prevSelected, IShapesRepository shapesList) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.selectedList = selectedList;
        this.prevSelected = prevSelected;
        this.shapesList = shapesList;
    }

    @Override
    public void run() {
        if (selectedList == null) throw new IllegalStateException();

        List<IShapes> dummySelected = new ArrayList<>();
        for (IShapes s : selectedList) {
            dummySelected.add(s);
        }

        // for each shape in the selected list, update them with the delta values
        for (IShapes s : dummySelected) {

            // add shape to the previously selected list
            prevSelected.add(s);

            // since Shapes are immutable, have to clone them, pass in updated points.
            IPoints start = new PointCoord(deltaX + s.getStart().get_x(), deltaY + s.getStart().get_y());
            IPoints end   = new PointCoord(deltaX + s.getEnd().get_x(), deltaY + s.getEnd().get_y());
            IShapes deltaShape = new GenericShape(start, end, s);

            shapesList.removeShape(s);
            shapesList.addShape(deltaShape);

            // update the selectedList
            selectedList.remove(s);
            selectedList.add(deltaShape);
        }

        if (!selectedList.isEmpty())
            CommandHistory.add(this);
    }

    @Override
    public void undo() {
        for (IShapes s : selectedList) {
            shapesList.removeShape(s);
        }

        for (IShapes s : prevSelected) {
            shapesList.addShape(s);
        }
    }

    @Override
    public void redo() {
        for (IShapes s : prevSelected) {
            shapesList.removeShape(s);
        }

        for (IShapes s : selectedList) {
            shapesList.addShape(s);
        }
    }

}
