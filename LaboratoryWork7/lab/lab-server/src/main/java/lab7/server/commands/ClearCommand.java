package lab7.server.commands;

import lab7.common.util.entities.CollectionManager;
import lab7.common.util.handlers.TextFormatter;
import lab7.common.util.requestSystem.responses.CommandResponse;
import lab7.server.databaseHandlers.DatabaseWorker;

public class ClearCommand extends CommandAbstract {

    public ClearCommand(DatabaseWorker databaseWorker) {
        super("clear", databaseWorker);
    }

    @Override
    public CommandResponse execute(CollectionManager manager) {
        manager.clear();
        return new CommandResponse(TextFormatter.colorInfoMessage("Collection successfully cleared"));
    }
}
