package main;

import main.interfaces.ICommands;


import main.interfaces.IShapes;
import main.interfaces.IShapesRepository;

import java.util.List;


public class UnGroupCommand implements ICommands {
    private final IShapesRepository shapeRepo;

    public UnGroupCommand (IShapesRepository shapeRepo) {
        this.shapeRepo = shapeRepo;
    }

    @Override
    public void run () {
        List<IShapes> selected  = SharedContainers.getInstance().getSelectList();
        List<IShapes> groupList = SharedContainers.getInstance().getGroupList();

        if (selected.isEmpty()) return;

        // any selected shapes that are grouped will no longer be grouped
        for (IShapes s : selected) {
            s.unGroupShape(shapeRepo);
            groupList.remove(s);
        }


        // if a group has groups inside, delete outer most one

    }
}
