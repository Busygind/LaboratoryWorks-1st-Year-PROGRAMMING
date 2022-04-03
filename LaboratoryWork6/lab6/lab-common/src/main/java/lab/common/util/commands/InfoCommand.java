package lab.common.util.commands;

import lab.common.util.entities.CollectionManager;

public class InfoCommand extends CommandAbstract {

    public InfoCommand() {
        super("info", "Вывести информацию о коллекции", 0);
    }

    @Override
    public String execute(CollectionManager manager) {
        return manager.showInfo();
    }
}
