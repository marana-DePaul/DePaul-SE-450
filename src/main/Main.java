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
    }
}
