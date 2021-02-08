package main;

import main.interfaces.*;
import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShapesRepository implements IShapesRepository {
    final private List<IShapes> shapesList;
    final private PaintCanvasBase canvas;

    public ShapesRepository(PaintCanvasBase canvas) {
        shapesList   = new ArrayList<IShapes>();
        this.canvas  = canvas;
    }

    public ShapesRepository() {
        shapesList   = new ArrayList<IShapes>();
        canvas       = new PaintCanvas();
    }

    // adds a shape, then tells ShapesDrawer to draw all the shapes in the list
    @Override
    public void addShape(IShapes shape) {
        if (shape == null) throw new IllegalArgumentException();

        shapesList.add(shape);
        IShapesDrawer drawer = new ShapesDrawer(canvas);
        drawer.drawAllShapes(shapesList);
    }

    // removes a shape from end of list, then tells ShapesDrawer to clear the canvas and redraw all shapes
    @Override
    public IShapes removeShape() {
        if (shapesList.isEmpty()) throw new IllegalStateException();

        IShapes delShape = shapesList.remove(shapesList.size()-1);
        IShapesDrawer drawer = new ShapesDrawer(canvas);

        drawer.clearAllShapes();
        drawer.drawAllShapes(shapesList);

        return delShape;
    }

    @Override
    public int getNumItems() {
        return shapesList.size();
    }

    @Override
    // method returns a copy of the shapeList
    public List<IShapes> getShapeList() {
        List<IShapes> copy = new ArrayList<IShapes>();

        for (IShapes x : shapesList)
            copy.add(x);

        return copy;
    }
}
