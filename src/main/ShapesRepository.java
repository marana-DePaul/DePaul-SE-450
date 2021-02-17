package main;

import main.interfaces.*;

import view.interfaces.PaintCanvasBase;
import java.util.ArrayList;
import java.util.List;

public class ShapesRepository implements IShapesRepository {
    final private List<IShapes> shapesList;
    final private IShapesDrawer drawer;

    public ShapesRepository(PaintCanvasBase canvas) {
        this.shapesList = new ArrayList<IShapes>();
        this.drawer = new ShapesDrawer(canvas);
    }


    // adds a shape, then tells ShapesDrawer to draw all the shapes in the list
    @Override
    public void addShape(IShapes shape) {
        if (shape == null) throw new IllegalArgumentException();

        shapesList.add(shape);
        drawer.drawAllShapes(shapesList);
    }

    // removes a shape from end of list, then tells ShapesDrawer to clear the canvas and redraw all shapes
    @Override
    public IShapes removeShape() {
        if (shapesList.isEmpty()) throw new IllegalStateException();

        IShapes delShape = shapesList.remove(shapesList.size()-1);

        drawer.clearAllShapes();
        drawer.drawAllShapes(shapesList);

        return delShape;
    }

    // removes the given shape item from the list. Returns the index of the deleted shape on success, -1 on failure.
    @Override
    public void removeShape(IShapes item) {
        if (shapesList.isEmpty()) throw new IllegalStateException();

        shapesList.remove(item);
        drawer.clearAllShapes();
        drawer.drawAllShapes(shapesList);
    }

    @Override
    public int getNumItems() {
        return shapesList.size();
    }

    // method returns a copy of the shapeList
    @Override
    public List<IShapes> getShapeList() {
        List<IShapes> copy = new ArrayList<IShapes>();

        for (IShapes x : shapesList)
            copy.add(x);

        return copy;
    }
}
