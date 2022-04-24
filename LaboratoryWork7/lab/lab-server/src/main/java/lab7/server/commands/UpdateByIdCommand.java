package lab7.server.commands;

import lab7.common.util.entities.CollectionManager;
import lab7.common.util.entities.Dragon;
import lab7.common.util.handlers.TextFormatter;
import lab7.common.util.requestSystem.responses.CommandResponse;
import lab7.server.databaseHandlers.DatabaseWorker;

public class UpdateByIdCommand extends CommandAbstract {

    private long id;
    private Dragon dragon;

    public UpdateByIdCommand(long id, Dragon dragon, DatabaseWorker databaseWorker) {
        super("update_by_id", databaseWorker);
        this.dragon = dragon;
        this.id = id;
    }

    @Override
    public CommandResponse execute(CollectionManager manager) {
        boolean flag = false;
        for (Dragon elem : manager.getDragons()) {
            if (elem.getId() == id) {
                manager.removeById(id);
                flag = true;
            }
        }
        if (flag) {
            manager.addDragon(dragon);
            return new CommandResponse(TextFormatter.colorInfoMessage("Info about dragon successfully updated"));
        } else {
            return new CommandResponse(TextFormatter.colorInfoMessage("ID not found"));
        }
    }
}
