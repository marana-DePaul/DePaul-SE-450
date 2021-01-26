package main;

import main.interfaces.ICommands;

public class UndoCommand implements ICommands {
    @Override
    public void run() {
        CommandHistory.undo();
    }
}
