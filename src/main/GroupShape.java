package main;


import main.interfaces.IDrawStrategy;
import main.interfaces.IPoints;
import main.interfaces.IShapes;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;
import java.util.List;

public class GroupShape implements IShapes {
    private final List<IShapes> children;

    public GroupShape() {
        this.children = new ArrayList<IShapes>();
    }

    public void addChild(IShapes shape) {
        children.add(shape);
        System.out.println(children.size());
    }

    @Override
    public void copyShape() {}

    @Override
    public void pasteShape() {}

    @Override
    public void deleteShape() {}

    @Override
    public void groupShape() {}

    @Override
    public void unGroupShape() {}

    @Override
    public void drawShape(PaintCanvasBase canvasBase) { }

    @Override
    public IPoints getStart() { return null; }

    @Override
    public IPoints getEnd() {
        return null;
    }

    @Override
    public ShapeType getShapeType() { return null; }

    @Override
    public ShapeColor getPrimaryColor() {return null;}

    @Override
    public ShapeColor getSecondaryColor() {return null;}

    @Override
    public ShapeShadingType getShadingType() {return null;}

    @Override
    public IDrawStrategy getShapeStrategy() {return  null;}

}
