package main;

import main.interfaces.ICollisionStrategy;
import main.interfaces.IPoints;
import main.interfaces.IShapes;

public class CollisionStrategy implements ICollisionStrategy {

    @Override
    public boolean detectCollision(IPoints collStart, IPoints collEnd, IShapes shape) {
        if (shape == null) throw new IllegalArgumentException();


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
