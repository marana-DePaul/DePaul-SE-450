package main;

import main.interfaces.ICommands;


import main.interfaces.IShapesRepository;


public class UnGroupCommand implements ICommands {
    private final IShapesRepository shapeRepo;

    public UnGroupCommand (IShapesRepository shapeRepo) {
        this.shapeRepo = shapeRepo;
    }


    @Override
    public void run () {

        System.out.println("Clicked on UnGroup");
        System.out.println("Num in repo-> "+ shapeRepo.getNumItems());
        System.out.println("Num in selectlist-> "+SharedContainers.getInstance().getSelectList().size());
        System.out.println("Num in prevSelect-> "+SharedContainers.getInstance().getPrevSelectList().size());
        System.out.println("Num in copylist-> "+SharedContainers.getInstance().getCopyList().size());
        System.out.println("Num in outlinelist-> "+SharedContainers.getInstance().getOutlineList().size());


    }
}
