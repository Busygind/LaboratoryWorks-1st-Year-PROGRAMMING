package lab.common.util.commands;


import lab.common.util.entities.CollectionManager;
import lab.common.util.handlers.CommandFactory;
import lab.common.util.handlers.HistorySaver;
import lab.common.util.handlers.TextFormatter;

public class HistoryCommand extends CommandAbstract {

    public HistoryCommand() {
        super("history", "Вывести последние 11 команд (без их аргументов)", 0);
    }

    @Override
    public String execute(CollectionManager manager) {
        final int countOfWatchableCommands = 11;
        System.out.println(HistorySaver.getCommandsHistory().size());
        if (HistorySaver.getCommandsHistory().size() > countOfWatchableCommands) {
            return TextFormatter.colorMessage(HistorySaver.getCommandsHistory().subList(HistorySaver.getCommandsHistory().size()
                    - countOfWatchableCommands, HistorySaver.getCommandsHistory().size()).toString());
        }
        return TextFormatter.colorMessage(HistorySaver.getCommandsHistory().toString());
    }
}
