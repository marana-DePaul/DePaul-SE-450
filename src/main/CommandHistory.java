package main;

import java.util.Stack;

public class CommandHistory {
    private static final Stack<IUndoRedo> undoStack = new Stack<IUndoRedo>();
    private static final Stack<IUndoRedo> redoStack = new Stack<IUndoRedo>();

    public static void add(IUndoRedo command) {
        undoStack.push(command);
        redoStack.clear();
    }

    public static void undo() {
        if (!undoStack.isEmpty()) {
            IUndoRedo cmd = undoStack.pop();
            redoStack.push(cmd);
            cmd.undo();
        }
    }

    public static void redo() {
        if (!redoStack.isEmpty()) {
            IUndoRedo cmd = redoStack.pop();
            undoStack.push(cmd);
            cmd.redo();
        }
    }

}
