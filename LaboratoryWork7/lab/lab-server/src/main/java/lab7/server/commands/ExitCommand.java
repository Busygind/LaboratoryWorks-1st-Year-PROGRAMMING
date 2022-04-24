package lab7.server.commands;

import lab7.common.util.entities.CollectionManager;
import lab7.common.util.handlers.TextFormatter;
import lab7.common.util.requestSystem.responses.CommandResponse;
import lab7.server.databaseHandlers.DatabaseWorker;


public class ExitCommand extends CommandAbstract {

    public ExitCommand(DatabaseWorker databaseWorker) {
        super("exit", databaseWorker);
    }

    @Override
    public CommandResponse execute(CollectionManager manager) {
        return new CommandResponse(TextFormatter.colorInfoMessage("Connection disabled"));
    }
}
