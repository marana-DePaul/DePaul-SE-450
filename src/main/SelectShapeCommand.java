package main;

import main.interfaces.*;

import java.util.List;

public class SelectShapeCommand implements ICommands {
    private final IPoints collStart;
    private final IPoints collEnd;
    private final List<IShapes> selectedList;
    private final IShapesRepository shapesRepo;
    private final ICollisionStrategy strategy;

    public SelectShapeCommand(IPoints startPt, IPoints endPt, List<IShapes> current, IShapesRepository shapesList, ICollisionStrategy strategy) {
        if (current == null || shapesList == null) throw new IllegalArgumentException();

        this.collStart = startPt;
        this.collEnd = endPt;
        this.selectedList = current;
        this.shapesRepo = shapesList;
        this.strategy = strategy;
    }

    @Override
    public void run() {
        // clearing selectedList
        selectedList.clear();

        List<IShapes> copy = shapesRepo.getShapeList();
        List<IShapes> prevSelected = SharedContainers.getInstance().getPrevSelectList();

        // check the shapes in the repository to see if they were selected; if so add to selectedList
        for (IShapes s : copy) {
            boolean status = strategy.detectCollision(collStart,collEnd,s);

            if (status) {
                selectedList.add(s);

                // add shape to the previously selected list
                prevSelected.add(s);
            }
        }
    }

}
