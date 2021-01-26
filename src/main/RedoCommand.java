package main;

import main.interfaces.ICommands;

public class RedoCommand implements ICommands {
    @Override
    public void run() {
        CommandHistory.redo();
    }
}
