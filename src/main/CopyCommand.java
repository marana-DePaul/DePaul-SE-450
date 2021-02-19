package main;

import main.interfaces.ICommands;
import main.interfaces.IShapes;

import java.util.List;

public class CopyCommand implements ICommands {

    @Override
    public void run() {
        List<IShapes> copyList = SelectContainer.getCopyList();
        List<IShapes> selectedList = SelectContainer.getSelectedList();

        copyList.clear();
        // for every selected shape, add it to the copyList
        for (IShapes x : selectedList) {
            copyList.add(x);
        }

        //System.out.println("Shapes copied -> " + copyList.size());
    }
}
