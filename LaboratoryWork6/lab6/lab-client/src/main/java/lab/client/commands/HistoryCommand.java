package lab.client.commands;

import lab.client.handlers.TextFormatter;
import lab.client.handlers.CommandManager;

import java.util.ArrayList;

public class HistoryCommand extends CommandAbstract {

    public HistoryCommand() {
        super("history", "Вывести последние 11 команд (без их аргументов)", 0);
    }

    @Override
    public boolean execute(ArrayList<String> args) {
        final int countOfWatchableCommands = 11;
        if (CommandManager.getCommandsHistory().size() > countOfWatchableCommands) {
            TextFormatter.printMessage(CommandManager.getCommandsHistory().subList(CommandManager.getCommandsHistory().size() - countOfWatchableCommands, CommandManager.getCommandsHistory().size()).toString());
        }
        TextFormatter.printMessage(CommandManager.getCommandsHistory().toString());
        return true;
    }
}