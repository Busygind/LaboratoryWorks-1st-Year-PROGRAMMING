package lab6.client.commands;

import lab6.client.entities.CollectionManager;

import java.util.ArrayList;

public class RemoveByIdCommand extends CommandAbstract {

    CollectionManager manager;

    public RemoveByIdCommand(CollectionManager manager) {
        super("remove_by_id", "удалить дракона с текущим значением id", 1);
        this.manager = manager;
    }

    @Override
    public boolean execute(ArrayList<String> args) {
        manager.removeById(args.get(0));
        return true;
    }
}
