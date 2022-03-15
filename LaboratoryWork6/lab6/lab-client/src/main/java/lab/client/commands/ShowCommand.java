package lab.client.commands;

import lab.client.entities.CollectionManager;
import lab.client.handlers.TextFormatter;

import java.util.ArrayList;

public class ShowCommand extends CommandAbstract {

    private final CollectionManager manager;

    public ShowCommand(CollectionManager manager) {
        super("show", "вывести на экран элементы коллекции в строковом представлении", 0);
        this.manager = manager;
    }

    @Override
    public boolean execute(ArrayList<String> args) {
        TextFormatter.printMessage(manager.getDragons().toString());
        return true;
    }
}
