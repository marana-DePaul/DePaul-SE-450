package main;

import main.interfaces.*;
import model.interfaces.IApplicationState;

import java.util.ArrayList;
import java.util.List;

public class SelectShapeProxy implements ICommands {
    private final SelectShapeCommand selectCmd;
    private final List<IShapes> selectList;
    private final IApplicationState appState;
    private final IShapesRepository shapesRepo;

    public SelectShapeProxy (IPoints startPt, IPoints endPt, List<IShapes> currentSelect, IApplicationState appState, IShapesRepository shapesRepo) {
        if (currentSelect == null || shapesRepo == null || appState == null) throw new IllegalArgumentException();

        selectCmd = new SelectShapeCommand(startPt,endPt,currentSelect,shapesRepo, new CollisionStrategy());
        this.selectList = currentSelect;
        this.appState = appState;
        this.shapesRepo = shapesRepo;
    }


    @Override
    public void run() {
        List<IShapes> tmp = new ArrayList<>();

        // run the select command in order to get the updated select list
        selectCmd.run();

        // keep track of all shapes selected until now
        for (IShapes s : selectList)
            tmp.add(s);

        // for every shape that is selected, draw an identical outline shape
        for (IShapes s : tmp) {
            int startX = s.getStart().get_x() - 5;
            int startY = s.getStart().get_y() - 5;

            if (startX < 0) startX = 0;
            if (startY < 0) startY = 0;

            IPoints start = new PointCoord(startX,startY);
            IPoints end = new PointCoord(s.getEnd().get_x() + 5, s.getEnd().get_y() + 5);

            ICommands cmd = new CreateShapeCommand(start,end,appState,shapesRepo,s);
            cmd.run();
        }

        // if user has not selected anything, remove the outlines
        if (selectList.isEmpty()) {
            List<IShapes> outlines = SharedContainers.getInstance().getOutlineList();

            for (IShapes s : outlines) {
                shapesRepo.removeShape(s);
            }

            outlines.clear();
        }

    }
}
