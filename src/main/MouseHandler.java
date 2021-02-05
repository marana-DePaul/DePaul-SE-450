package main;

import main.interfaces.ICommands;
import main.interfaces.IPoints;
import main.interfaces.IShapesRepository;
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
        startCoord = new PointCoord(0,0);
        endCoord   = new PointCoord(0,0);
        appState   = state;
        this.shapesList = shapesList;
    }

    @Override
    public void mousePressed(MouseEvent e){
        if (e == null) throw new IllegalArgumentException();
        startCoord.updateCoord(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e == null) throw new IllegalArgumentException();

        endCoord.updateCoord(e.getPoint());
        ICommands command;

        // if in draw mode, create the shape
        if (appState.getActiveMouseMode() == MouseMode.DRAW) {

            command = new CreateShapeCommand(startCoord,endCoord,appState,shapesList);
            command.run();
        }

    }
}
