package lab.client.commands;

import lab.client.entities.CollectionManager;

import java.util.ArrayList;

public class UpdateByIdCommand extends CommandAbstract {

    private final CollectionManager manager;

    public UpdateByIdCommand(CollectionManager manager) {
        super("update_by_id", "Обновить данные о элементе коллекции по данному id", 1);
        this.manager = manager;
    }

    @Override
    public boolean execute(ArrayList<String> args) {
        manager.updateById(args.get(0));
        return true;
    }
}
