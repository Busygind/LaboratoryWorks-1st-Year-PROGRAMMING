package lab7.server.commands;

import lab7.common.util.entities.CollectionManager;
import lab7.common.util.entities.Dragon;
import lab7.common.util.requestSystem.responses.CommandResponse;
import lab7.server.databaseHandlers.DatabaseWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShowCommand extends CommandAbstract {

    public ShowCommand(DatabaseWorker databaseWorker) {
        super("show", databaseWorker);
    }

    @Override
    public CommandResponse execute(CollectionManager manager) {
        List<Dragon> sortedList = new ArrayList<>(manager.getDragons());
        sortedList = sortedList.stream().sorted(Dragon::compareByName).collect(Collectors.toList());
        return new CommandResponse(sortedList, "List of dragons: ");
    }
}
