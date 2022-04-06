package lab.common.util.commands;

import lab.common.util.entities.CollectionManager;
import lab.common.util.handlers.TextFormatter;
import lab.common.util.requestSystem.Response;

public class ClearCommand extends CommandAbstract {

    public ClearCommand() {
        super("clear", "Очищение коллекции", 0);
    }

    @Override
    public Response execute(CollectionManager manager) {
        manager.clear();
        return new Response(TextFormatter.colorInfoMessage("Collection successfully cleared"));
    }
}
