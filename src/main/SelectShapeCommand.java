package main;

import main.interfaces.ICommands;
import main.interfaces.IPoints;
import main.interfaces.IShapes;
import main.interfaces.IShapesRepository;

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

        // check the shapes in the repository to see if they were selected; if so add to selectedList
        for (IShapes s : copy) {
            if (detectCollision(s)) {
                selectedList.add(s);
            }
        }
        //System.out.println("objects selected -> " + selectedList.size());
    }

    private boolean detectCollision(IShapes shape) {
        boolean status = false;
        boolean cond1 = false;
        boolean cond2 = false;
        boolean cond3 = false;
        boolean cond4 = false;

        // collision box
        int collWidth = Math.abs(collEnd.get_x() - collStart.get_x());
        int collHeight = Math.abs(collEnd.get_y() - collStart.get_y());
        int startX = Math.min(collStart.get_x(), collEnd.get_x());
        int startY = Math.min(collStart.get_y(), collEnd.get_y());
        int endX = startX + collWidth;
        int endY = startY + collHeight;


        // shape collision box
        int shapeWidth = Math.abs(shape.getEnd().get_x() - shape.getStart().get_x());
        int shapeHeight = Math.abs(shape.getEnd().get_y() - shape.getStart().get_y());
        int shapeStX = Math.min(shape.getStart().get_x(), shape.getEnd().get_x());
        int shapeStY = Math.min(shape.getStart().get_y(), shape.getEnd().get_y());
        int shapeEndX = shapeStX + shapeWidth;
        int shapeEndY = shapeStY + shapeHeight;

        if (startX < shapeEndX)
            cond1 = true;
        if (endX > shapeStX)
            cond2 = true;
        if (startY < shapeEndY)
            cond3 = true;
        if (endY > shapeStY)
            cond4 = true;

        // if all conditions are true then there is a collision
        if (cond1 && cond2 && cond3 && cond4)
            status = true;

        return status;
    }



}
