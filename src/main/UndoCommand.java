package main;

public class UndoCommand implements ICommands{
    @Override
    public void run() {
        CommandHistory.undo();
    }
}
