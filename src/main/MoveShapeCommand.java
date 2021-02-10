package main;

import main.interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class MoveShapeCommand implements ICommands, IUndoRedo {
    private int deltaX;
    private int deltaY;
    private List<IShapes> selectedList;
    private List<IShapes> prevSelected;
    private IShapesRepository shapesList;

    public MoveShapeCommand (int deltaX, int deltaY, List<IShapes> selectedList, List<IShapes> prevSelected, IShapesRepository shapesList) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.selectedList = selectedList;
        this.prevSelected = prevSelected;
        this.shapesList = shapesList;
    }

    @Override
    public void run() {

        List<IShapes> dummySelected = new ArrayList<>();
        for (IShapes s : selectedList) {
            dummySelected.add(s);
        }

        // for each shape in the selected list, update them with the delta values
        for (IShapes s : dummySelected) {

            // since Shapes are immutable, have to clone them, pass in updated points.
            IPoints start = new PointCoord(deltaX + s.getStart().get_x(), deltaY + s.getStart().get_y());
            IPoints end   = new PointCoord(deltaX + s.getEnd().get_x(), deltaY + s.getEnd().get_y());
            IShapes deltaShape = new GenericShape(start, end, s);

            // pulling selected shapes out of the shape repository, and adding in the modified ones
            int shapesIndex = shapesList.removeShape(s);
            shapesList.addShape(shapesIndex, deltaShape);

            // update the selectedList
            selectedList.remove(s);
            selectedList.add(deltaShape);
        }

        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        
    }

    @Override
    public void redo() {

    }

}
