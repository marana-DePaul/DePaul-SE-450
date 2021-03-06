package main;

import main.interfaces.ICommands;

public class GroupCommand implements ICommands {

    @Override
    public void run() {
        System.out.println("clicked on group");
    }
}
