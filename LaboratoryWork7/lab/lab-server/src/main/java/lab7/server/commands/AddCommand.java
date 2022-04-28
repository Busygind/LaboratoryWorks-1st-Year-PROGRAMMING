package lab7.server.commands;

import lab7.server.CollectionManager;
import lab7.common.util.entities.Dragon;
import lab7.common.util.handlers.TextFormatter;
import lab7.common.util.requestSystem.responses.CommandResponse;
import lab7.server.databaseHandlers.DatabaseWorker;


public class AddCommand extends CommandAbstract {

    private Dragon dragon;

    public AddCommand(Dragon dragon, DatabaseWorker databaseWorker) {
        super("add", databaseWorker);
        this.dragon = dragon;
    }

    @Override
    public CommandResponse execute(CollectionManager manager) {
        if (getDatabaseWorker().addDragon(dragon)) {
            dragon.setAuthorName(getDatabaseWorker().getUsername());
            manager.addDragon(dragon);
            return new CommandResponse(TextFormatter.colorInfoMessage("Dragon successfully added"));
        } else {
            return new CommandResponse(TextFormatter.colorErrorMessage("SQL problems, dragon didn't add"));
        }
    }

    public void setDragon(Dragon dragon) {
        this.dragon = dragon;
    }
}
