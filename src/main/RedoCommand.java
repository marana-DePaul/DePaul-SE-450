package main;

public class RedoCommand implements ICommands {
    @Override
    public void run() {
        CommandHistory.redo();
    }
}
