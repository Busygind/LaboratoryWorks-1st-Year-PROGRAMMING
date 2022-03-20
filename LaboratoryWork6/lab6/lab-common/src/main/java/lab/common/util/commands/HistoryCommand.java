package lab.common.util.commands;


import lab.common.util.entities.CollectionManager;
import lab.common.util.handlers.CommandFactory;
import lab.common.util.handlers.TextFormatter;

public class HistoryCommand extends CommandAbstract {

    public HistoryCommand() {
        super("history", "Вывести последние 11 команд (без их аргументов)", 0);
    }

    @Override
    public String execute(CollectionManager manager) {
        final int countOfWatchableCommands = 11;
        if (CommandFactory.getCommandsHistory().size() > countOfWatchableCommands) {
            return TextFormatter.colorMessage(CommandFactory.getCommandsHistory().subList(CommandFactory.getCommandsHistory().size()
                    - countOfWatchableCommands, CommandFactory.getCommandsHistory().size()).toString());
        }
        return TextFormatter.colorMessage(CommandFactory.getCommandsHistory().toString());
    }
}
