package lab7.server.commands;


import lab7.server.CollectionManager;
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
        HistorySaver historySaver = new HistorySaver();
        if (historySaver.getCommandsHistory().size() > COUNT_OF_WATCHABLE_COMMANDS) {
            return new CommandResponse(TextFormatter.colorMessage(historySaver.getCommandsHistory()
                                .subList(historySaver.getCommandsHistory().size() - COUNT_OF_WATCHABLE_COMMANDS,
                                historySaver.getCommandsHistory().size()).toString()));
        }
        return new CommandResponse(TextFormatter.colorMessage(historySaver.getCommandsHistory().toString()));
    }
}
