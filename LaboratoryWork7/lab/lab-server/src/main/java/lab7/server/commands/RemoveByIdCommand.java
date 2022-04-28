package lab7.server.commands;

import lab7.server.CollectionManager;
import lab7.common.util.handlers.TextFormatter;
import lab7.common.util.requestSystem.responses.CommandResponse;
import lab7.server.databaseHandlers.DatabaseWorker;

public class RemoveByIdCommand extends CommandAbstract {

    private final long id;

    public RemoveByIdCommand(long id, DatabaseWorker databaseWorker) {
        super("remove_by_id", databaseWorker);
        this.id = id;
    }

    @Override
    public CommandResponse execute(CollectionManager manager) {
        if (getDatabaseWorker().removeById(id)) {
            return new CommandResponse(manager.removeById(id));
        } else {
            return new CommandResponse(TextFormatter.colorMessage("You don't have dragon with that id"));
        }
    }
}
