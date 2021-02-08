package main;

import main.interfaces.ICommands;
import main.interfaces.IPoints;
import main.interfaces.IShapes;
import main.interfaces.IShapesRepository;
import model.MouseMode;
import model.interfaces.IApplicationState;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MouseHandler extends MouseAdapter {
    private IPoints startCoord;
    private IPoints endCoord;
    private final IApplicationState appState;
    private final IShapesRepository shapesList;
    private final List<IShapes> selectedList;

    public MouseHandler(IApplicationState state, IShapesRepository shapesList) {
        startCoord = new PointCoord(0,0);
        endCoord   = new PointCoord(0,0);
        appState   = state;
        this.shapesList = shapesList;
        selectedList = new ArrayList<IShapes>();
    }

    @Override
    public void mousePressed(MouseEvent e){
        if (e == null) throw new IllegalArgumentException();
        startCoord = new PointCoord(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e == null) throw new IllegalArgumentException();

        endCoord = new PointCoord(e.getPoint());

        ICommands command;

        // if in draw mode, create the shape
        if (appState.getActiveMouseMode() == MouseMode.DRAW) {
            command = new CreateShapeCommand(startCoord,endCoord,appState,shapesList);
            command.run();
        }

        else if (appState.getActiveMouseMode() == MouseMode.SELECT) {
            command = new SelectShapeCommand(startCoord,endCoord,selectedList,shapesList);
            command.run();
        }

        else if (appState.getActiveMouseMode() == MouseMode.MOVE) {
            System.out.println("I clicked move mode");
        }


        //System.out.println(shapesList.getNumItems());
    }
}
