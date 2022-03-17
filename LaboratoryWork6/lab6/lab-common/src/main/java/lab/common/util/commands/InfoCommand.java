package lab.common.util.commands;


import lab.common.util.entities.CollectionManager;

import java.util.ArrayList;

public class InfoCommand extends CommandAbstract {

    private final CollectionManager manager;

    public InfoCommand(CollectionManager manager) {
        super("info", "Вывести информацию о коллекции", 0);
        this.manager = manager;
    }

    @Override
    public boolean execute(ArrayList<String> args) {
        manager.showInfo();
        return true;
    }
}
