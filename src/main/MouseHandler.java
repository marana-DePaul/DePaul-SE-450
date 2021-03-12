package main;

import main.interfaces.*;
import model.MouseMode;
import model.interfaces.IApplicationState;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MouseHandler extends MouseAdapter {
    private IPoints startCoord;
    private IPoints endCoord;
    private final IApplicationState appState;
    private final IShapesRepository shapesRepo;
    private final List<IShapes> selectList;
    private final List<IShapes> prevSelected;

    public MouseHandler(IApplicationState state, IShapesRepository shapesRepo) {
        startCoord = new PointCoord(0,0);
        endCoord   = new PointCoord(0,0);
        appState   = state;
        this.shapesRepo = shapesRepo;
        this.selectList = SharedContainers.getInstance().getSelectList();
        prevSelected = SharedContainers.getInstance().getPrevSelectList();
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

        if (appState.getActiveMouseMode() == MouseMode.DRAW) {
            command = new CreateShapeCommand(startCoord,endCoord,appState,shapesRepo);
            command.run();
        }

        else if (appState.getActiveMouseMode() == MouseMode.SELECT) {
            System.out.println("in select mode");
            command = new SelectShapeProxy(startCoord,endCoord,selectList, appState, shapesRepo);
            command.run();
        }

        else if (appState.getActiveMouseMode() == MouseMode.MOVE) {
            int deltaX = endCoord.get_x() - startCoord.get_x();
            int deltaY = endCoord.get_y() - startCoord.get_y();

            command = new MoveShapeCommand(deltaX, deltaY, selectList, prevSelected, shapesRepo);
            command.run();
        }
    }

}
