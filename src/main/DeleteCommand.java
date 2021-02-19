package main;

import main.interfaces.ICommands;
import main.interfaces.IShapesRepository;

public class DeleteCommand implements ICommands {
    private final IShapesRepository shapeRepo;

    public DeleteCommand (IShapesRepository shapeRepo) {
        if (shapeRepo == null) throw new IllegalArgumentException();

        this.shapeRepo = shapeRepo;
    }

    @Override
    public void run() {
        System.out.println("hello from the delete command");
        System.out.println("this is a test from the delete command");
    }
}
