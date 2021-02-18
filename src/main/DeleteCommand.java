package main;

import main.interfaces.ICommands;

public class DeleteCommand implements ICommands {

    @Override
    public void run() {
        System.out.println("hello from the delete command");
    }
}
