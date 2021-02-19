package main;

import main.interfaces.ICommands;
import main.interfaces.IPoints;
import main.interfaces.IShapes;
import main.interfaces.IShapesRepository;

import java.util.List;

public class PasteCommand implements ICommands {
    private final IShapesRepository shapeRepo;

    public PasteCommand (IShapesRepository shapeRepo) {
        if (shapeRepo == null) throw new IllegalArgumentException();

        this.shapeRepo = shapeRepo;
    }

    @Override
    public void run() {
        List<IShapes> copyList = SelectContainer.getCopyList();

        // for every copied shape, clone a new shape at an offset from the origin
        for (IShapes x : copyList) {
            IPoints startPt = new PointCoord(x.getStart().get_x() + 65,x.getStart().get_y() + 65);
            IPoints endPt = new PointCoord(x.getEnd().get_x() + 65, x.getEnd().get_y() + 65);
            IShapes copiedShape = new GenericShape(startPt, endPt, x);

            shapeRepo.addShape(copiedShape);
        }

    }


}
