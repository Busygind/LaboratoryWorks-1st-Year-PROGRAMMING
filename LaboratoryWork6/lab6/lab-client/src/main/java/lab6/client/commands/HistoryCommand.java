package lab6.client.commands;

import lab6.client.handlers.CommandListener;
import lab6.client.handlers.TextFormatter;

import java.util.ArrayList;

public class HistoryCommand extends CommandAbstract {

    public HistoryCommand() {
        super("history", "Вывести последние 11 команд (без их аргументов)", 0);
    }

    @Override
    public boolean execute(ArrayList<String> args) {
        final int countOfWatchableCommands = 11;
        if (CommandListener.getCommandHistory().size() > countOfWatchableCommands) {
            TextFormatter.printMessage(CommandListener.getCommandHistory().subList(CommandListener.getCommandHistory().size() - countOfWatchableCommands, CommandListener.getCommandHistory().size()).toString());
        }
        TextFormatter.printMessage(CommandListener.getCommandHistory().toString());
        return true;
    }
}
