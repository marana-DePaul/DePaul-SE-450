package main;

import view.interfaces.PaintCanvasBase;
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

}
