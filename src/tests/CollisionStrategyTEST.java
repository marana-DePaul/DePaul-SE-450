package tests;

import main.*;
import main.interfaces.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CollisionStrategyTEST {

    @Test
    public void detectCollisionSuccess() {
        ICollisionStrategy testStrategy = new CollisionStrategy();
        IPoints testShpStart = new PointCoord(100,100);
        IPoints testShpEnd   = new PointCoord(400, 400);
        IPoints testCollStart = new PointCoord(0,0);
        IPoints testCollEnd = new PointCoord(300,300);
        IShapes testShape = new GenericShape(testShpStart, testShpEnd, new RectangleStrategy());
        boolean status;

        status = testStrategy.detectCollision(testCollStart,testCollEnd,testShape);
        assertTrue(status);

        testCollStart = new PointCoord(10,10);
        testCollEnd = new PointCoord(90,90);

        status = testStrategy.detectCollision(testCollStart,testCollEnd,testShape);
        assertFalse(status);
    }
}
