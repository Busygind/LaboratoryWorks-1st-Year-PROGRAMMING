package lab7.server.commands;

import lab7.common.util.entities.CollectionManager;
import lab7.common.util.entities.Dragon;
import lab7.common.util.handlers.TextFormatter;
import lab7.common.util.requestSystem.responses.CommandResponse;
import lab7.server.databaseHandlers.DatabaseWorker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintDescendingCommand extends CommandAbstract {

    public PrintDescendingCommand(DatabaseWorker databaseWorker) {
        super("print_descending", databaseWorker);
    }

    @Override
    public CommandResponse execute(CollectionManager manager) {
        List<Dragon> dragons = new ArrayList<>(manager.getDragons());
        dragons.sort(Collections.reverseOrder());
        return new CommandResponse(dragons, TextFormatter.colorInfoMessage("List of dragons compared of age (reverse): "));
    }
}
