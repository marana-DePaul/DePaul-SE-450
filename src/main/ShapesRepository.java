package main;

import main.interfaces.IShapes;
import main.interfaces.IShapesDrawer;
import main.interfaces.IShapesRepository;
import view.gui.PaintCanvas;
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

    public ShapesRepository() {
        shapesList = new ArrayList<IShapes>();
        this.canvas = new PaintCanvas();
    }

    // adds a shape, then tells ShapesDrawer to draw all the shapes in the list
    public void addShape(IShapes shape) {
        if (shape == null) throw new IllegalArgumentException();
        shapesList.add(shape);

        IShapesDrawer drawer = new ShapesDrawer(shapesList,canvas);
        drawer.drawAllShapes();
    }

    // removes a shape from end of list, then tells ShapesDrawer to clear the canvas and redraw all shapes
    public IShapes removeShape() {
        if (shapesList.isEmpty()) throw new EmptyStackException();
        IShapes delShape = shapesList.remove(shapesList.size()-1);

        IShapesDrawer drawer = new ShapesDrawer(shapesList,canvas);
        drawer.clearAllShapes();
        drawer.drawAllShapes();

        return delShape;
    }

    public int getNumItems() {
        return shapesList.size();
    }

}
