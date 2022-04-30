package lab7.server;

import lab7.server.commands.CommandAbstract;

import java.util.ArrayList;
import java.util.List;

public class HistorySaver {
    private volatile static List<String> commandsHistory = new ArrayList<>();

    public List<String> getCommandsHistory() {
        return commandsHistory;
    }

    public void addCommandInHistory(CommandAbstract command) {
        commandsHistory.add(command.getName());
    }
}
