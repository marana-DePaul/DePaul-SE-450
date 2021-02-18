package main;

import main.interfaces.IShapes;
import main.interfaces.IShapesDrawer;
import view.interfaces.PaintCanvasBase;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.List;

public class ShapesDrawer implements IShapesDrawer {
    private final PaintCanvasBase canvasBase;

    public ShapesDrawer(PaintCanvasBase canvasBase) {
        this.canvasBase = canvasBase;
    }

    @Override
    public void drawAllShapes(List<IShapes> shapesList) {
        for (IShapes shape : shapesList) {
            shape.drawShape(canvasBase);
        }
    }

    @Override
    public void clearAllShapes() {
        Graphics2D graphics2d = canvasBase.getGraphics2D();
        graphics2d.setColor(Color.WHITE);
        graphics2d.fillRect(0,0,canvasBase.getWidth(),canvasBase.getHeight());
    }

}
