package main;

import main.interfaces.*;
import model.ShapeType;
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
        List<IShapes> selected = SharedContainers.getInstance().getSelectList();
        List<IShapes> outlines = SharedContainers.getInstance().getOutlineList();
        GroupShape groups = SharedContainers.getInstance().getGroupShape();

        IPoints topLeft;
        IPoints bottomRight;
        int x1 = selected.get(0).getStart().get_x();
        int y1 = selected.get(0).getStart().get_y();
        int x2 = selected.get(0).getEnd().get_x();
        int y2 = selected.get(0).getEnd().get_y();


        // calculating the top leftmost, and bottom rightmost points
        for (IShapes x : selected) {
            // check if have to update top leftmost point
            if (x.getStart().get_x() < x1) {
                x1 = x.getStart().get_x();
                y1 = x.getStart().get_y();
            }

            // check if have to update bottom rightmost point
            if (x.getEnd().get_x() > x2) {
                x2 = x.getEnd().get_x();
                y2 = x.getEnd().get_y();
            }

            // adding to the groups
            //groups.addChild(x);
        }

        // deleting old outlines
        for (IShapes s : outlines) {
            shapesRepo.removeShape(s);
            //selected.remove(s);
        }

        topLeft = new PointCoord(x1,y1);
        bottomRight = new PointCoord(x2,y2);



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
