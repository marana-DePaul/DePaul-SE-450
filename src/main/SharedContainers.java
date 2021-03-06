package main;

import main.interfaces.IShapes;

import java.util.ArrayList;
import java.util.List;

public class SharedContainers {
    private static SharedContainers instance;
    private final List<IShapes> selectList;
    private final List<IShapes> prevSelectList;
    private final List<IShapes> copyList;
    private final List<IShapes> outlineList;

    private SharedContainers () {
        this.selectList = new ArrayList<>();
        this.prevSelectList = new ArrayList<>();
        this.copyList = new ArrayList<>();
        this.outlineList = new ArrayList<>();
    }

    public static SharedContainers getInstance() {
        if (instance == null)
            instance = new SharedContainers();
        return instance;
    }

    public List<IShapes> getSelectList() { return selectList; }

    public List<IShapes> getPrevSelectList() { return prevSelectList; }

    public List<IShapes> getCopyList() { return copyList; }

    public List<IShapes> getOutlineList() { return outlineList; }



}
