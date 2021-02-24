package tests;

import main.PointCoord;
import main.interfaces.IPoints;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class PointCoordTEST {

    @Test
    public void testGetXGetYSuccess() {
        IPoints testPt = new PointCoord(10,20);
        int resultX;
        int resultY;

        resultX = testPt.get_x();
        resultY = testPt.get_y();

        assertEquals(resultX,10);
        assertEquals(resultY, 20);
    }

    @Test
    public void testPointCoordDifferentConstructorsSuccess() {
        Point point = new Point(5,6);
        IPoints testPt1 = new PointCoord(point);
        IPoints testPt2 = new PointCoord(5,6);

        assertEquals(5, testPt1.get_x());
        assertEquals(6, testPt1.get_y());

        assertEquals(5, testPt2.get_x());
        assertEquals(6, testPt2.get_y());
    }

    @Test
    public void testPointCoordConstructorWithNegativeValuesThrowsException() {
        IPoints testPt1 = null;

        try {
            testPt1 = new PointCoord(-1, 9);
        }
        catch (IllegalArgumentException ex) { }

        assertNull(testPt1);

        try {
            testPt1 = new PointCoord(9, -1);
        }
        catch (IllegalArgumentException ex) { }

        assertNull(testPt1);
    }


}
