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

        System.out.println("ShapeRepo size b4 copy -> " + shapeRepo.getNumItems());

        // for every copied shape, clone a new shape at an offset from the origin
        for (IShapes x : copyList) {
            // needed to maintain same shape, especially for triangles
            int shiftX = Math.min(x.getStart().get_x(), x.getEnd().get_x());
            int shiftY = Math.min(x.getStart().get_y(), x.getEnd().get_y());

            IPoints startPt = new PointCoord(x.getStart().get_x() - shiftX,x.getStart().get_y() - shiftY);
            IPoints endPt = new PointCoord(x.getEnd().get_x() - shiftX, x.getEnd().get_y() - shiftY );
            IShapes copiedShape = new GenericShape(startPt, endPt, x);

            shapeRepo.addShape(copiedShape);
        }

        System.out.println("ShapeRepo items after copy -> " + shapeRepo.getNumItems());
    }


}
