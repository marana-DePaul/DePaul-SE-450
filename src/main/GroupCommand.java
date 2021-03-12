package main;

import main.interfaces.*;

import model.interfaces.IApplicationState;

import java.util.ArrayList;
import java.util.List;

public class GroupCommand implements ICommands, IUndoRedo {
    private final IShapesRepository shapesRepo;
    private final IApplicationState appState;

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
        List<IShapes> tmp = new ArrayList<>();
        IPoints topLeft;
        IPoints bottomRight;

        int x1 = Integer.MAX_VALUE;
        int y1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE;
        int y2 = Integer.MIN_VALUE;

        for (IShapes s : outlines)
            tmp.add(s);

        // deleting old outlines
        for (IShapes s : tmp) {
            shapesRepo.removeShape(s);
            outlines.remove(s);
            selected.remove(s);
        }

        System.out.println("shaperepo-> "+shapesRepo.getNumItems());
        System.out.println("outlines -> "+outlines.size());

        // calculating coordinates for the start and end points of group box
        for (IShapes s : selected) {
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

        topLeft = new PointCoord(x1 - 5,y1 - 5);
        bottomRight = new PointCoord(x2 + 5,y2 + 5);
        IShapes group = factory.createGroup(topLeft, bottomRight);


        for (IShapes s : selected) {
            tmp.add(s);
        }

        // add in selected shapes to the group
        for (IShapes s : tmp) {
            group.addChild(s);
            shapesRepo.removeShape(s);
        }

        shapesRepo.addShape(group);

        System.out.println("num items in group-> "+group.getSize());
        CommandHistory.add(this);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

}
