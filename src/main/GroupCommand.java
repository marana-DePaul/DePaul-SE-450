package main;

import main.interfaces.*;
import model.ShapeType;
import model.interfaces.IApplicationState;

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
        List<IShapes> selected = SharedContainers.getInstance().getSelectList();
        List<IShapes> outlines = SharedContainers.getInstance().getOutlineList();
        GroupShape groups = SharedContainers.getInstance().getGroupShape();
        IPoints topLeft;
        IPoints bottomRight;

        int x1 = Integer.MAX_VALUE;
        int y1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE;
        int y2 = Integer.MIN_VALUE;

        // deleting old outlines
        for (IShapes s : outlines) {
            shapesRepo.removeShape(s);
            selected.remove(s);
        }

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

        // creating group by rectangle shape
        IShapesFactory factory = new ShapesFactory(appState);
        IShapes outline = factory.createOutline(topLeft,bottomRight,ShapeType.RECTANGLE);

        shapesRepo.addShape(outline);
        CommandHistory.add(this);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

}
