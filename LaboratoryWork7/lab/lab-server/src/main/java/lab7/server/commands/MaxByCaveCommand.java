package lab7.server.commands;

import lab7.common.util.entities.CollectionManager;
import lab7.common.util.entities.Dragon;
import lab7.common.util.handlers.TextFormatter;
import lab7.common.util.requestSystem.responses.CommandResponse;
import lab7.server.databaseHandlers.DatabaseWorker;

import java.util.ArrayList;
import java.util.List;

public class MaxByCaveCommand extends CommandAbstract {

    public MaxByCaveCommand(DatabaseWorker databaseWorker) {
        super("max_by_cave", databaseWorker);
    }

    @Override
    public CommandResponse execute(CollectionManager manager) {
        Dragon dragon = manager.getMaxByCave();
        List<Dragon> dragons = new ArrayList<>();
        dragons.add(dragon);
        return new CommandResponse(dragons, TextFormatter.colorMessage("Info about dragon with deepest cave: "));
    }
}
