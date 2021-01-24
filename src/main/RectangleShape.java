package main;

import model.ShapeShadingType;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class RectangleShape extends Shapes {
    public RectangleShape (IPoints start, IPoints end, IApplicationState state) {
        startPt = new PointCoord();
        startPt.set_x(start.get_x());
        startPt.set_y(start.get_y());

        endPt = new PointCoord();
        endPt.set_x(end.get_x());
        endPt.set_y(end.get_y());

        shapeType = state.getActiveShapeType();
        primaryColor = state.getActivePrimaryColor();
        secondaryColor = state.getActiveSecondaryColor();
        shadingType = state.getActiveShapeShadingType();
    }

    @Override
    public void drawShape(PaintCanvasBase canvas) {
        int width  = Math.abs(endPt.get_x() - startPt.get_x());
        int height = Math.abs(endPt.get_y() - startPt.get_y());

        // these variables will be used to allow the user to draw from any direction
        int startX = Math.min(startPt.get_x(), endPt.get_x());
        int startY = Math.min(startPt.get_y(), endPt.get_y());

        // check the state of the application to decide how to draw the rectangle
        if (shadingType == ShapeShadingType.FILLED_IN) {
            Graphics2D graphics2D = canvas.getGraphics2D();
            graphics2D.setColor(Color.GREEN);
            graphics2D.fillRect(startX,startY,width,height);
        }
    }


}
