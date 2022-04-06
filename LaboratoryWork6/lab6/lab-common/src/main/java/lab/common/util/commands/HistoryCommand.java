package lab.common.util.commands;


import lab.common.util.entities.CollectionManager;
import lab.common.util.handlers.HistorySaver;
import lab.common.util.handlers.TextFormatter;
import lab.common.util.requestSystem.Response;

public class HistoryCommand extends CommandAbstract {

    private static final int COUNT_OF_WATCHABLE_COMMANDS = 11;

    public HistoryCommand() {
        super("history", "Вывести последние 11 команд (без их аргументов)", 0);
    }

    @Override
    public Response execute(CollectionManager manager) {
        System.out.println(HistorySaver.getCommandsHistory().size());
        if (HistorySaver.getCommandsHistory().size() > COUNT_OF_WATCHABLE_COMMANDS) {
            return new Response(TextFormatter.colorMessage(HistorySaver.getCommandsHistory().subList(HistorySaver.getCommandsHistory().size()
                    - COUNT_OF_WATCHABLE_COMMANDS, HistorySaver.getCommandsHistory().size()).toString()));
        }
        return new Response(TextFormatter.colorMessage(HistorySaver.getCommandsHistory().toString()));
    }
}
