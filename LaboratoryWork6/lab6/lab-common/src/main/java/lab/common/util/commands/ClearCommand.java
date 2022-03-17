package lab.common.util.commands;


import lab.common.util.entities.CollectionManager;

import java.util.ArrayList;

public class ClearCommand extends CommandAbstract {

    private final CollectionManager manager;

    public ClearCommand(CollectionManager manager) {
        super("clear", "Очищение коллекции", 0);
        this.manager = manager;
    }

    @Override
    public boolean execute(ArrayList<String> args) {
        manager.clear();
        return true;
    }
}
