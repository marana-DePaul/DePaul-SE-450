package main;

import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class ShapesRepository implements IShapesRepository {
    final private List<IShapes> shapesList;
    final private PaintCanvasBase canvas;

    public ShapesRepository(PaintCanvasBase canvas) {
        shapesList = new ArrayList<IShapes>();
        this.canvas = canvas;
    }

    public boolean isEmpty() {
        return shapesList.isEmpty();
    }

    // add the new shape, then draw it
    public void addShape(IShapes shape) {
        if (shape == null) throw new IllegalArgumentException();
        shapesList.add(shape);

        ShapesDrawer drawer = new ShapesDrawer(shapesList,canvas);
        drawer.drawAllShapes();
    }

    public IShapes removeShape() {
        if (shapesList.isEmpty()) throw new EmptyStackException();
        return shapesList.remove(shapesList.size()-1);
    }

    public int getNumItems() {
        return shapesList.size();
    }

}
