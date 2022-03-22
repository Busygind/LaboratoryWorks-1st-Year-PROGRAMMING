package lab.common.util.handlers;

import lab.common.util.commands.CommandAbstract;

import java.util.ArrayList;
import java.util.List;

public class HistorySaver {
    private static List<String> COMMANDS_HISTORY = new ArrayList<>();

    public static List<String> getCommandsHistory() {
        return COMMANDS_HISTORY;
    }

    public static void addCommandInHistory(CommandAbstract command) {
        COMMANDS_HISTORY.add(command.getName());
    }
}
