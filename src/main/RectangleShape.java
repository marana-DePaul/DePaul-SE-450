package main;

import main.interfaces.IPoints;
import main.interfaces.IShapes;
import model.ShapeColor;
import model.ShapeShadingType;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;
import java.awt.Graphics2D;
import java.awt.Color;

public class RectangleShape implements IShapes {
    protected PointCoord startPt;
    protected PointCoord endPt;
    protected ShapeColor primaryColor;
    protected ShapeColor secondaryColor;
    protected ShapeShadingType shadingType;
    
    public RectangleShape (IPoints startPt, IPoints endPt, IApplicationState appState) {
        this.startPt = new PointCoord(startPt.get_x(), startPt.get_y());
        this.endPt = new PointCoord(endPt.get_x(), endPt.get_y());
        primaryColor = appState.getActivePrimaryColor();
        secondaryColor = appState.getActiveSecondaryColor();
        shadingType = appState.getActiveShapeShadingType();
    }

    public RectangleShape() {
        startPt = new PointCoord(100,100);
        endPt   = new PointCoord(400,400);
        primaryColor = ShapeColor.GREEN;
        secondaryColor = ShapeColor.ORANGE;
        shadingType = ShapeShadingType.FILLED_IN;
    }

    public void drawShape(PaintCanvasBase canvasBase) {
        if (canvasBase == null)
            throw new IllegalArgumentException();

        int width  = Math.abs(endPt.get_x() - startPt.get_x());
        int height = Math.abs(endPt.get_y() - startPt.get_y());

        // these variables will be used to allow the user to draw from any direction
        int startX = Math.min(startPt.get_x(), endPt.get_x());
        int startY = Math.min(startPt.get_y(), endPt.get_y());

        // check the shading to decide how to draw the shape
        if (shadingType == ShapeShadingType.FILLED_IN) {
            drawShape_Fill(canvasBase,startX,startY,width,height);
        }

    }

    private void drawShape_Fill(PaintCanvasBase canvasBase, int start, int end, int w, int h) {
        Graphics2D graphics2D = canvasBase.getGraphics2D();
        graphics2D.setColor(Color.GREEN);
        graphics2D.fillRect(start,end,w,h);
    }
}
