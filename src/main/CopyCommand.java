package main;

import main.interfaces.ICommands;
import main.interfaces.IShapes;

import java.util.List;

public class CopyCommand implements ICommands {
    private List<IShapes> copyList;
    private List<IShapes> selectedList;

    public CopyCommand (List<IShapes> copyList, List<IShapes> selectedList) {
        this.copyList = copyList;
        this.selectedList = selectedList;
    }

    @Override
    public void run() {
        System.out.println("hello from the copy command");
    }
}
