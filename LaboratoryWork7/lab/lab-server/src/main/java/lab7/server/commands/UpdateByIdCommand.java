package lab7.server.commands;

import lab7.server.CollectionManager;
import lab7.common.util.entities.Dragon;
import lab7.common.util.handlers.TextFormatter;
import lab7.common.util.requestSystem.responses.CommandResponse;
import lab7.server.databaseHandlers.DatabaseWorker;

public class UpdateByIdCommand extends CommandAbstract {

    private final long id;
    private final Dragon dragon;

    public UpdateByIdCommand(long id, Dragon dragon, DatabaseWorker databaseWorker) {
        super("update_by_id", databaseWorker);
        this.dragon = dragon;
        this.id = id;
    }

    @Override
    public CommandResponse execute(CollectionManager manager) {
        if (getDatabaseWorker().updateById(dragon, id)) {
            for (Dragon elem : manager.getDragons()) {
                if (elem.getId() == id) {
                    manager.removeById(id);
                    manager.addDragon(dragon);
                    return new CommandResponse(TextFormatter.
                                colorInfoMessage("Info about dragon successfully updated"));
                }
            }
        } else {
            return new CommandResponse(TextFormatter.colorInfoMessage("ID not found"));
        }
        return null; //never used
    }
}
