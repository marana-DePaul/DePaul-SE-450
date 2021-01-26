package main;

import view.interfaces.PaintCanvasBase;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.List;

public class ShapesDrawer implements IShapesDrawer {
    final private List<IShapes> shapesList;
    final private PaintCanvasBase canvasBase;

    public ShapesDrawer(List<IShapes> shapesList, PaintCanvasBase canvasBase) {
        this.shapesList = shapesList;
        this.canvasBase = canvasBase;
    }

    public void drawAllShapes() {
        for (IShapes shape : shapesList) {
            shape.drawShape(canvasBase);
        }
    }

    public void clearAllShapes() {
        Graphics2D graphics2d = canvasBase.getGraphics2D();
        graphics2d.setColor(Color.WHITE);
        graphics2d.fillRect(0,0,canvasBase.getWidth(),canvasBase.getHeight());
    }

}
