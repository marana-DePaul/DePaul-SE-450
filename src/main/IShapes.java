package main;

import model.ShapeColor;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;

public interface IShapes {

    void drawShape(PaintCanvasBase canvasBase);

}
