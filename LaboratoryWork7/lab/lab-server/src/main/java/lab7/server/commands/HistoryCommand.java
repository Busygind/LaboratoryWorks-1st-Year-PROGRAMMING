package lab7.server.commands;


import lab7.common.util.entities.CollectionManager;
import lab7.server.HistorySaver;
import lab7.common.util.handlers.TextFormatter;
import lab7.common.util.requestSystem.responses.CommandResponse;
import lab7.server.databaseHandlers.DatabaseWorker;

public class HistoryCommand extends CommandAbstract {

    private static final int COUNT_OF_WATCHABLE_COMMANDS = 11;

    public HistoryCommand(DatabaseWorker databaseWorker) {
        super("history", databaseWorker);
    }

    @Override
    public CommandResponse execute(CollectionManager manager) {
        System.out.println(HistorySaver.getCommandsHistory().size());
        if (HistorySaver.getCommandsHistory().size() > COUNT_OF_WATCHABLE_COMMANDS) {
            return new CommandResponse(TextFormatter.colorMessage(HistorySaver.getCommandsHistory().subList(HistorySaver.getCommandsHistory().size()
                    - COUNT_OF_WATCHABLE_COMMANDS, HistorySaver.getCommandsHistory().size()).toString()));
        }
        return new CommandResponse(TextFormatter.colorMessage(HistorySaver.getCommandsHistory().toString()));
    }
}
