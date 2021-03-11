package main;

import main.interfaces.IDrawStrategy;
import main.interfaces.IPoints;
import model.ShapeColor;
import model.ShapeShadingType;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class RectangleStrategy implements IDrawStrategy {

    @Override
    public void drawShapeType(IPoints start, IPoints end, ShapeShadingType shade, ShapeColor color1, ShapeColor color2, PaintCanvasBase canvas) {
        if (start == null || end == null || shade == null || canvas == null)
            throw new IllegalArgumentException();

        int width = Math.abs(end.get_x() - start.get_x());
        int height = Math.abs(end.get_y() - start.get_y());

        // these variables will be used to allow the user to draw from any direction
        int startX = Math.min(start.get_x(), end.get_x());
        int startY = Math.min(start.get_y(), end.get_y());

        Graphics2D graphics2d = canvas.getGraphics2D();
        graphics2d.setColor(color1.getColor());

        if (shade == ShapeShadingType.FILLED_IN) {
            graphics2d.fillRect(startX, startY, width, height);
        }
        else if (shade == ShapeShadingType.OUTLINE) {
            graphics2d.setStroke(new BasicStroke(5));
            graphics2d.drawRect(startX, startY, width, height);
        }
        else if (shade == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
            graphics2d.fillRect(startX, startY, width, height);
            graphics2d.setStroke(new BasicStroke(5));
            graphics2d.setColor(color2.getColor());
            graphics2d.drawRect(startX, startY, width, height);
        }

    }

    // outline of Rectangle
    @Override
    public void drawShapeType(IPoints start, IPoints end, PaintCanvasBase canvas)  {
        if (start == null || end == null || canvas == null) throw new IllegalArgumentException();

        int width = Math.abs(end.get_x() - start.get_x());
        int height = Math.abs(end.get_y() - start.get_y());

        // these variables will be used to allow the user to draw from any direction
        int startX = Math.min(start.get_x(), end.get_x());
        int startY = Math.min(start.get_y(), end.get_y());

        Graphics2D graphics2d = canvas.getGraphics2D();

        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawRect(startX, startY, width, height);
    }


}
