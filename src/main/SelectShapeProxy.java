package main;

import main.interfaces.ICommands;
import main.interfaces.IPoints;
import main.interfaces.IShapes;
import main.interfaces.IShapesRepository;

import java.util.List;

public class SelectShapeProxy implements ICommands {
    private final SelectShapeCommand selectCmd;
    private final List<IShapes> selectList;
    private final IShapesRepository shapesRepo;

    public SelectShapeProxy (IPoints startPt, IPoints endPt, List<IShapes> currentSelect, IShapesRepository shapesRepo) {
        if (currentSelect == null || shapesRepo == null) throw new IllegalArgumentException();

        selectCmd = new SelectShapeCommand(startPt,endPt,currentSelect,shapesRepo);
        this.selectList = currentSelect;
        this.shapesRepo = shapesRepo;
    }


    @Override
    public void run() {

        // run the select command in order to get the updated select list
        selectCmd.run();





    }
}
