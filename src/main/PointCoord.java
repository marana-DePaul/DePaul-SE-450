package main;

import main.interfaces.IPoints;

import java.awt.Point;

public class PointCoord implements IPoints {
    private int x;
    private int y;

    public PointCoord(int xCoord, int yCoord) {
        x = xCoord;
        y = yCoord;
    }

    @Override
    public void updateCoord(Point pt) {
        this.x = pt.x;
        this.y = pt.y;
    }

    @Override
    public int get_x() {
        return x;
    }

    @Override
    public int get_y() {
        return y;
    }

    @Override
    public String toString() {
        return ("["+ x + "," + y +"]");
    }
}
