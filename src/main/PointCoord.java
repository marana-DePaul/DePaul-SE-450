package main;

public class PointCoord {
    private int x;
    private int y;

    public PointCoord() {
        x = 0;
        y = 0;
    }

    public PointCoord(int a, int b) {
        x = a;
        y = b;
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
}
