package lab6.client.commands;

import lab6.client.entities.CollectionManager;

import java.util.ArrayList;

public class InfoCommand extends CommandAbstract {

    CollectionManager manager;

    public InfoCommand(CollectionManager manager) {
        super("show", "вывод всех элементов коллекции в строковом представлении", 0);
        this.manager = manager;
    }

    @Override
    public boolean execute(ArrayList<String> args) {
        manager.showInfo();
        return true;
    }
}
