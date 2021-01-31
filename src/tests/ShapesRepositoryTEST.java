package tests;


import main.RectangleShape;
import main.ShapesRepository;
import main.interfaces.IShapes;
import main.interfaces.IShapesRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;


public class ShapesRepositoryTEST {


    @Test
    public void testAddShapeSuccess() {
        // arrange
        // testWindow needed as when addShape is called, it tells the ShapeDrawer to draw the shape
        PaintCanvasBase testCanvas = new PaintCanvas();
        IGuiWindow testWindow = new GuiWindow(testCanvas);
        IShapesRepository testRepo = new ShapesRepository(testCanvas);
        IShapes testShape1 = new RectangleShape();

        // act
        testRepo.addShape(testShape1);

        // assert
        assertEquals(testRepo.getNumItems(),1);
    }

    @Test
    public void testAddShapeWithNullArgumentThrowsException() {
        PaintCanvasBase testCanvas = new PaintCanvas();
        IGuiWindow testWindow = new GuiWindow(testCanvas);
        IShapesRepository testRepo = new ShapesRepository(testCanvas);

        try {
            testRepo.addShape(null);
        }
        catch (IllegalArgumentException ex) { }

        assertEquals(testRepo.getNumItems(),0);
    }

    @Test
    public void testRemoveShapeSuccess() {
        PaintCanvasBase testCanvas = new PaintCanvas();
        IGuiWindow testWindow = new GuiWindow(testCanvas);
        IShapesRepository testRepo = new ShapesRepository(testCanvas);
        IShapes testShape1 = new RectangleShape();
        IShapes testShape2 = new RectangleShape();

        testRepo.addShape(testShape1);
        testRepo.addShape(testShape2);
        testRepo.removeShape();

        assertEquals(testRepo.getNumItems(),1);
    }

    @Test
    public void testRemoveShapeWhenEmptyThrowsException() {
        PaintCanvasBase testCanvas = new PaintCanvas();
        IGuiWindow testWindow = new GuiWindow(testCanvas);
        IShapesRepository testRepo = new ShapesRepository(testCanvas);

        try {
            testRepo.removeShape();
        }
        catch (IllegalStateException ex) { }

        assertEquals(testRepo.getNumItems(),0);
    }



}
