package lab.common.util.commands;

import lab.common.util.entities.CollectionManager;
import lab.common.util.requestSystem.Response;

public class InfoCommand extends CommandAbstract {

    public InfoCommand() {
        super("info", "Вывести информацию о коллекции", 0);
    }

    @Override
    public Response execute(CollectionManager manager) {
        return new Response(manager.showInfo());
    }
}
