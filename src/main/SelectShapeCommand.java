package main;

import main.interfaces.*;

import java.util.List;

public class SelectShapeCommand implements ICommands {
    private final IPoints collStart;
    private final IPoints collEnd;
    private final List<IShapes> selectedList;
    private final IShapesRepository shapesList;

    public SelectShapeCommand(IPoints startPt, IPoints endPt, List<IShapes> current, IShapesRepository shapesList) {
        if (current == null || shapesList == null) throw new IllegalArgumentException();

        this.collStart = startPt;
        this.collEnd = endPt;
        this.selectedList = current;
        this.shapesList = shapesList;
    }

    @Override
    public void run() {
        // clearing selectedList
        selectedList.clear();

        List<IShapes> copy = shapesList.getShapeList();
        List<IShapes> prevSelected = SharedContainers.getInstance().getPrevSelectList();
        ICollisionStrategy collision = new CollisionStrategy();

        // check the shapes in the repository to see if they were selected; if so add to selectedList
        for (IShapes s : copy) {
            if (collision.detectCollision(collStart,collEnd,s)) {
                selectedList.add(s);

                // add shape to the previously selected list
                prevSelected.add(s);
            }
        }
        //System.out.println("objects selected -> " + selectedList.size());
    }

}
