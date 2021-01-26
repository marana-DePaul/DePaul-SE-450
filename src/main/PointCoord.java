package main;

import main.interfaces.IPoints;

import java.awt.Point;

public class PointCoord implements IPoints {
    private int x;
    private int y;

    public PointCoord() {
        x = 0;
        y = 0;
    }

    // method to update the coordinates using a Point
    public void updateCoord(Point pt) {
        this.x = pt.x;
        this.y = pt.y;
    }

    public void set_x(int n) {
        x = n;
    }

    public void set_y(int n) {
        y = n;
    }

    public int get_x() {
        return x;
    }

    public int get_y() {
        return y;
    }

    @Override
    public String toString() {
        return ("["+ x + "," + y +"]");
    }
}
