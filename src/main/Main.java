// Michael Arana
// DePaul University
// SE 450 Winter 2021

package main;
import controller.*;
import controller.IJPaintController;
import main.interfaces.IShapesRepository;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;

public class Main {

    public static void main(String[] args){
        PaintCanvasBase paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);

        // setting up shape repository/drawer
        IShapesRepository shapeRepo = new ShapesRepository(paintCanvas);

        IJPaintController controller = new JPaintController(uiModule, appState, shapeRepo);
        controller.setup();

        // setting up mouse interactions
        MouseHandler mouseHandler = new MouseHandler(appState, shapeRepo);
        paintCanvas.addMouseListener(mouseHandler);


        // For example purposes only; remove all lines below from your final project.
        /*
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        // Selected Shape
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawRect(7, 8, 210, 410);
        */
    }
}
