package main;

import main.interfaces.ICommands;
import main.interfaces.IShapes;
import main.interfaces.IShapesRepository;
import main.interfaces.IUndoRedo;

import java.util.List;

public class DeleteCommand implements ICommands, IUndoRedo {
    private final IShapesRepository shapeRepo;
    private final List<IShapes> selectedList;

    public DeleteCommand (IShapesRepository shapeRepo) {
        if (shapeRepo == null) throw new IllegalArgumentException();

        this.shapeRepo = shapeRepo;
        this.selectedList = SelectContainer.getSelectedList();
    }

    @Override
    public void run() {
        // delete any selected shapes
        if (selectedList == null) throw new IllegalStateException();

        for (IShapes x : selectedList)
            shapeRepo.removeShape(x);

        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        List<IShapes> prevSelected = SelectContainer.getPrevSelectedList();

        for (IShapes x : prevSelected)
            shapeRepo.addShape(x);
    }

    @Override
    public void redo() {
        for (IShapes x : selectedList)
            shapeRepo.removeShape(x);
    }





}
