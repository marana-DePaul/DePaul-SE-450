package main;

import main.interfaces.IShapes;

import java.util.ArrayList;
import java.util.List;

public class SelectContainer {
    private static final List<IShapes> selectList = new ArrayList<>();
    private static final List<IShapes> prevSelectList = new ArrayList<>();
    private static final List<IShapes> copyList = new ArrayList<>();

    public static List<IShapes> getSelectedList() {
        return selectList;
    }

    public static List<IShapes> getPrevSelectedList() {
        return prevSelectList;
    }

    public static List<IShapes> getCopyList() {
        return copyList;
    }

}
