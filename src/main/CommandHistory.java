package main;

import main.interfaces.IUndoRedo;

import java.util.Stack;

public class CommandHistory {
    private static final Stack<IUndoRedo> undoStack = new Stack<IUndoRedo>();
    private static final Stack<IUndoRedo> redoStack = new Stack<IUndoRedo>();

    public static void add(IUndoRedo command) {
        undoStack.push(command);
        redoStack.clear();
    }

    public static boolean undo() {
        boolean status = !undoStack.isEmpty();

        if (status) {
            IUndoRedo cmd = undoStack.pop();
            redoStack.push(cmd);
            cmd.undo();
        }
        return status;
    }

    public static boolean redo() {
        boolean status = !redoStack.isEmpty();

        if (!redoStack.isEmpty()) {
            IUndoRedo cmd = redoStack.pop();
            undoStack.push(cmd);
            cmd.redo();
        }
        return status;
    }

}
