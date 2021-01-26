package main;

import model.ShapeColor;
import model.ShapeShadingType;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;
import java.awt.Graphics2D;
import java.awt.Color;

public class Shapes implements IShapes {
    protected PointCoord startPt;
    protected PointCoord endPt;
    protected ShapeColor primaryColor;
    protected ShapeColor secondaryColor;
    protected ShapeShadingType shadingType;

    public Shapes () {
        startPt = new PointCoord();
        endPt   = new PointCoord();
        primaryColor   = ShapeColor.GREEN;
        secondaryColor = ShapeColor.ORANGE;
        shadingType = ShapeShadingType.FILLED_IN;
    }

    public Shapes (IPoints startPt, IPoints endPt, IApplicationState appState) {
        this.startPt = new PointCoord();
        this.startPt.set_x(startPt.get_x());
        this.startPt.set_y(startPt.get_y());

        this.endPt = new PointCoord();
        this.endPt.set_x(endPt.get_x());
        this.endPt.set_y(endPt.get_y());

        primaryColor = appState.getActivePrimaryColor();
        secondaryColor = appState.getActiveSecondaryColor();
        shadingType = appState.getActiveShapeShadingType();
    }

    public void drawShape(PaintCanvasBase canvasBase) {
        int width  = Math.abs(endPt.get_x() - startPt.get_x());
        int height = Math.abs(endPt.get_y() - startPt.get_y());

        // these variables will be used to allow the user to draw from any direction
        int startX = Math.min(startPt.get_x(), endPt.get_x());
        int startY = Math.min(startPt.get_y(), endPt.get_y());

        // check the shading to decide how to draw the shape
        if (shadingType == ShapeShadingType.FILLED_IN) {
            Graphics2D graphics2D = canvasBase.getGraphics2D();
            graphics2D.setColor(Color.GREEN);
            graphics2D.fillRect(startX,startY,width,height);
        }
    }
}
