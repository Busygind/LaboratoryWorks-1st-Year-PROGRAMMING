package lab7.server.commands;

import lab7.common.util.entities.CollectionManager;
import lab7.common.util.requestSystem.responses.CommandResponse;
import lab7.server.databaseHandlers.DatabaseWorker;

public class InfoCommand extends CommandAbstract {

    public InfoCommand(DatabaseWorker databaseWorker) {
        super("info", databaseWorker);
    }

    @Override
    public CommandResponse execute(CollectionManager manager) {
        return new CommandResponse(manager.showInfo());
    }
}
