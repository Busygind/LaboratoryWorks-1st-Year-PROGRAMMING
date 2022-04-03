package lab.common.util.commands;

import lab.common.util.entities.CollectionManager;
import lab.common.util.handlers.TextFormatter;

public class ClearCommand extends CommandAbstract {

    public ClearCommand() {
        super("clear", "Очищение коллекции", 0);
    }

    @Override
    public String execute(CollectionManager manager) {
        manager.clear();
        return TextFormatter.colorInfoMessage("Collection successfully cleared");
    }
}
