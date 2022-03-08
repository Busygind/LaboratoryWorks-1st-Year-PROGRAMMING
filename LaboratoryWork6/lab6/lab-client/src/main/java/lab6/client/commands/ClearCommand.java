package lab6.client.commands;

import lab6.client.entities.CollectionManager;

import java.util.ArrayList;

public class ClearCommand extends CommandAbstract {

    CollectionManager manager;

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
