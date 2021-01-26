package main;

import model.MouseMode;
import model.interfaces.IApplicationState;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
    private final IPoints startCoord;
    private final IPoints endCoord;
    private final IApplicationState appState;
    private final IShapesRepository shapesList;

    public MouseHandler(IApplicationState state, IShapesRepository shapesList) {
        startCoord = new PointCoord();
        endCoord   = new PointCoord();
        appState   = state;
        this.shapesList = shapesList;
    }

    @Override
    public void mousePressed(MouseEvent e){
        startCoord.updateCoord(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endCoord.updateCoord(e.getPoint());
        ICommands command;

        // if in draw mode, invoke the method to draw the shape
        if (appState.getActiveMouseMode() == MouseMode.DRAW) {
            command = new CreateShapeCommand(startCoord,endCoord,appState,shapesList);
            command.run();
        }

        System.out.println("Number of Shapes in list -> " + shapesList.getNumItems());

    }
}
