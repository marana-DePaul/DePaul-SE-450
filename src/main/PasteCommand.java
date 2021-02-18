package main;

import main.interfaces.ICommands;

public class PasteCommand implements ICommands {

    @Override
    public void run() {
        System.out.println("hello from the paste command");
    }
}
