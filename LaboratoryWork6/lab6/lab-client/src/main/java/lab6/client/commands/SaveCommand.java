package lab6.client.commands;

import lab6.client.entities.CollectionManager;
import lab6.client.handlers.XMLWriter;

import java.util.ArrayList;

public class SaveCommand extends CommandAbstract {

    CollectionManager manager;

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
