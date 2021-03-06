package main;

import main.interfaces.ICommands;

public class UnGroupCommand implements ICommands {

    @Override
    public void run () {
        System.out.println("Clicked on UnGroup");
    }
}
