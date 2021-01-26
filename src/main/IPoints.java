package main;

import java.awt.Point;

public interface IPoints {
    void updateCoord(Point pt);

    void set_x(int n);

    void set_y(int n);

    int get_x();

    int get_y();
}
