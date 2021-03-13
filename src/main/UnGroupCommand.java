package main;

import main.interfaces.ICommands;


import main.interfaces.IShapes;
import main.interfaces.IShapesRepository;
import main.interfaces.IUndoRedo;

import java.util.ArrayList;
import java.util.List;


public class UnGroupCommand implements ICommands, IUndoRedo {
    private final IShapesRepository shapeRepo;
    private final List<IShapes> prevGrouped;

    public UnGroupCommand (IShapesRepository shapeRepo) {
        this.shapeRepo = shapeRepo;
        this.prevGrouped = new ArrayList<>();
    }

    @Override
    public void run () {
        List<IShapes> selected  = SharedContainers.getInstance().getSelectList();
        List<IShapes> groupList = SharedContainers.getInstance().getGroupList();

        // any selected shapes that are grouped will no longer be grouped
        for (IShapes s : selected) {
            if (groupList.contains(s)) {
                prevGrouped.add(s);
                s.unGroupShape(shapeRepo);
                groupList.remove(s);
                shapeRepo.removeShape(s);
            }
        }

        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        List<IShapes> groupList = SharedContainers.getInstance().getGroupList();

        for (IShapes s : prevGrouped) {
            shapeRepo.addShape(s);
            groupList.add(s);
        }

    }

    @Override
    public void redo() {
        List<IShapes> selected  = SharedContainers.getInstance().getSelectList();
        List<IShapes> groupList = SharedContainers.getInstance().getGroupList();

        // any selected shapes that are grouped will no longer be grouped
        for (IShapes s : selected) {
            if (groupList.contains(s)) {
                prevGrouped.add(s);
                s.unGroupShape(shapeRepo);
                groupList.remove(s);
                shapeRepo.removeShape(s);
            }
        }
    }

}
