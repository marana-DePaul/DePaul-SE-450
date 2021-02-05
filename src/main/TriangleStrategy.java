package main;

import main.interfaces.IDrawStrategy;
import model.ShapeColor;
import model.ShapeShadingType;
import view.interfaces.PaintCanvasBase;

import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class TriangleStrategy implements IDrawStrategy {

    @Override
    public void drawShapeType(PointCoord start, PointCoord end, ShapeShadingType shade, ShapeColor color1, ShapeColor color2, PaintCanvasBase canvas) {
        if (start == null || end == null || shade == null || canvas == null)
            throw new IllegalArgumentException();

        int[] xCoords = {start.get_x(), end.get_x(), start.get_x()};
        int[] yCoords = {start.get_y(), end.get_y(), end.get_y()};

        Graphics2D graphics2d = canvas.getGraphics2D();
        graphics2d.setColor(Main.getColor(color1));

        if (shade == ShapeShadingType.FILLED_IN) {
            graphics2d.fillPolygon(xCoords,yCoords,3);
        }
        else if (shade == ShapeShadingType.OUTLINE) {
            graphics2d.setStroke(new BasicStroke(5));
            graphics2d.drawPolygon(xCoords, yCoords, 3);
        }
        else if (shade == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
            graphics2d.fillPolygon(xCoords,yCoords,3);
            graphics2d.setStroke(new BasicStroke(5));
            graphics2d.setColor(Main.getColor(color2));
            graphics2d.drawPolygon(xCoords, yCoords, 3);
        }
    }
}
