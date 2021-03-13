package main;

import main.interfaces.ICommands;
import main.interfaces.IShapes;

import java.util.List;

public class CopyCommand implements ICommands {

    @Override
    public void run() {
        List<IShapes> copyList = SharedContainers.getInstance().getCopyList();
        List<IShapes> selectedList = SharedContainers.getInstance().getSelectList();

        copyList.clear();
        // for every selected shape, add it to the copyList
        for (IShapes x : selectedList) {
            x.addToCopyList(copyList);
        }
    }
}
