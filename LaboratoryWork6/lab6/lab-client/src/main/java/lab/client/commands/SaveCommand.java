package lab.client.commands;

import lab.client.entities.CollectionManager;

import java.util.ArrayList;

public class SaveCommand extends CommandAbstract {

    private final CollectionManager manager;

    public SaveCommand(CollectionManager manager) {
        super("save", "сохранить коллекцию в файл", 0);
        this.manager = manager;
    }

    @Override
    public boolean execute(ArrayList<String> args) {
        manager.save();
        return false;
    }
}
