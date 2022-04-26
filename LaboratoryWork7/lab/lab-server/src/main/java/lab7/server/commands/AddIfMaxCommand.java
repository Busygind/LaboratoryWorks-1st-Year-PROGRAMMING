package lab7.server.commands;

import lab7.common.util.entities.CollectionManager;
import lab7.common.util.entities.Dragon;
import lab7.common.util.handlers.TextFormatter;
import lab7.common.util.requestSystem.responses.CommandResponse;
import lab7.server.databaseHandlers.DatabaseWorker;

public class AddIfMaxCommand extends CommandAbstract {

    private final Dragon dragon;

    public AddIfMaxCommand(Dragon dragon, DatabaseWorker databaseWorker) {
        super("add_if_max", databaseWorker);
        this.dragon = dragon;
    }

    @Override
    public CommandResponse execute(CollectionManager manager) {
        int maxAge = manager.getMax().getAge();
        if (dragon.getAge() > maxAge && getDatabaseWorker().addDragon(dragon)) {
            dragon.setAuthorName(getDatabaseWorker().getUsername());
            manager.addDragon(dragon);
            return new CommandResponse(TextFormatter.colorInfoMessage("Dragon successfully added"));
        } else {
            return new CommandResponse(TextFormatter.colorInfoMessage("В коллекции есть дракон постарше!"));
        }
    }
}
