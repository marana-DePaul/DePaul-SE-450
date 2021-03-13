package main;

import main.interfaces.IPoints;

import java.awt.Point;

public class PointCoord implements IPoints {
    private final int x;
    private final int y;

    public PointCoord(int xCoord, int yCoord) {
        if (xCoord < 0 || yCoord < 0) throw new IllegalArgumentException();

        this.x = xCoord;
        this.y = yCoord;
    }

    public PointCoord(Point pt) {
        this.x = pt.x;
        this.y = pt.y;
    }

    public PointCoord(IPoints pt) {
        this.x = pt.get_x();
        this.y = pt.get_y();
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
