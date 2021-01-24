package main;

import model.MouseMode;
import model.ShapeType;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
    private IPoints startCoord;
    private IPoints endCoord;
    private PaintCanvasBase canvasBase;
    private IApplicationState appState;

    public MouseHandler() {
        startCoord = new PointCoord();
        endCoord   = new PointCoord();
    }

    public MouseHandler(PaintCanvasBase canvas, IApplicationState state) {
        startCoord = new PointCoord();
        endCoord   = new PointCoord();
        canvasBase = canvas;
        appState   = state;
    }

    @Override
    public void mousePressed(MouseEvent e){
        startCoord.point_to_PointCoord(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endCoord.point_to_PointCoord(e.getPoint());

        // if in draw mode, invoke the draw method for that shape
        if (appState.getActiveMouseMode() == MouseMode.DRAW) {
            IShapes shape = new Shapes();

            if (appState.getActiveShapeType() == ShapeType.RECTANGLE) {
                shape = new RectangleShape(startCoord,endCoord,appState);
                shape.drawShape(canvasBase);
            }
        }

    }
}
