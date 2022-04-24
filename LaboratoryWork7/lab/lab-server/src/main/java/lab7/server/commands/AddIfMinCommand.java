package lab7.server.commands;

import lab7.common.util.entities.CollectionManager;
import lab7.common.util.entities.Dragon;
import lab7.common.util.handlers.TextFormatter;
import lab7.common.util.requestSystem.responses.CommandResponse;
import lab7.server.databaseHandlers.DatabaseWorker;


public class AddIfMinCommand extends CommandAbstract {

    private final Dragon dragon;

    public AddIfMinCommand(Dragon dragon, DatabaseWorker databaseWorker) {
        super("add_if_min", databaseWorker);
        this.dragon = dragon;
    }

    @Override
    public CommandResponse execute(CollectionManager manager) {
        int minAge = manager.getMin().getAge();
        if (dragon.getAge() < minAge && getDatabaseWorker().addDragon(dragon)) {
            manager.addDragon(dragon);
            return new CommandResponse(TextFormatter.colorInfoMessage("Dragon successfully added"));
        } else {
            return new CommandResponse(TextFormatter.colorInfoMessage("В коллекции есть дракон помладше!"));
        }
    }
}
