package main;

import main.interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class MoveShapeCommand implements ICommands, IUndoRedo {
    private final int deltaX;
    private final int deltaY;
    private final List<IShapes> selectedList;
    private final List<IShapes> prevSelected;
    private final IShapesRepository shapesRepo;

    public MoveShapeCommand (int deltaX, int deltaY, List<IShapes> selectedList, List<IShapes> prevSelected, IShapesRepository shapesRepo) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.selectedList = selectedList;
        this.prevSelected = prevSelected;
        this.shapesRepo = shapesRepo;
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
            IShapes deltaShape = s.moveShape(deltaX, deltaY);

            shapesRepo.removeShape(s);
            shapesRepo.addShape(deltaShape);

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
            shapesRepo.removeShape(s);
        }

        for (IShapes s : prevSelected) {
            shapesRepo.addShape(s);
        }
    }

    @Override
    public void redo() {
        for (IShapes s : prevSelected) {
            shapesRepo.removeShape(s);
        }

        for (IShapes s : selectedList) {
            shapesRepo.addShape(s);
        }
    }

}
