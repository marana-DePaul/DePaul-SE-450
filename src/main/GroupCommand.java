package main;

import main.interfaces.*;

import model.interfaces.IApplicationState;

import java.util.ArrayList;
import java.util.List;

public class GroupCommand implements ICommands, IUndoRedo {
    private final IShapesRepository shapesRepo;
    private final IApplicationState appState;
    private IShapes createdShape;

    public GroupCommand (IShapesRepository shapeRepo, IApplicationState appState) {
        if (shapeRepo == null) throw new IllegalArgumentException();

        this.shapesRepo = shapeRepo;
        this.appState = appState;
    }

    @Override
    public void run() {
        IShapesFactory factory = new ShapesFactory(appState);
        List<IShapes> selected = SharedContainers.getInstance().getSelectList();
        List<IShapes> outlines = SharedContainers.getInstance().getOutlineList();
        List<IShapes> outCopy = new ArrayList<>();
        List<IShapes> selectCopy = new ArrayList<>();
        int x1 = Integer.MAX_VALUE;
        int y1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE;
        int y2 = Integer.MIN_VALUE;

        for (IShapes s : outlines)
            outCopy.add(s);

        //delete outlines
        for (IShapes s : outCopy) {
            shapesRepo.removeShape(s);
            outlines.remove(s);
            selected.remove(s);
        }
        //outlines.clear();

        // calculating coordinates for the start and end points of group box
        for (IShapes s : selected) {
            selectCopy.add(s);

            int lowX = Math.min(s.getStart().get_x(), s.getEnd().get_x());
            int lowY = Math.min(s.getStart().get_y(), s.getEnd().get_y());
            int highX = Math.max(s.getStart().get_x(), s.getEnd().get_x());
            int highY = Math.max(s.getStart().get_y(), s.getEnd().get_y());

            if (lowX < x1)
                x1 = lowX;

            if (lowY < y1)
                y1 = lowY;

            if (highX > x2)
                x2 = highX;

            if (highY > y2)
                y2 = highY;
        }

        IPoints topLeft = new PointCoord(x1-5, y1-5);
        IPoints bottomRight = new PointCoord(x2+5, y2+5);
        createdShape = factory.createGroup(new PointCoord(topLeft), new PointCoord(bottomRight));

        for (IShapes s : selectCopy) {
            createdShape.addChild(s);
            shapesRepo.removeShape(s);
            selected.remove(s);
        }
        
        SharedContainers.getInstance().getGroupList().add(createdShape);
        shapesRepo.addShape(createdShape);
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        createdShape.unGroupShape(shapesRepo);
        SharedContainers.getInstance().getGroupList().remove(createdShape);
        shapesRepo.removeShape(createdShape);
    }

    @Override
    public void redo() {
        shapesRepo.addShape(createdShape);
        SharedContainers.getInstance().getGroupList().add(createdShape);
    }

}
