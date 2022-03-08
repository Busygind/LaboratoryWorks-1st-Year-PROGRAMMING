package lab6.client.commands;

import lab6.client.entities.CollectionManager;
import lab6.client.entities.Dragon;
import lab6.client.handlers.TextFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintAscendingCommand extends CommandAbstract {
    CollectionManager manager;

    public PrintAscendingCommand(CollectionManager manager) {
        super("print_ascending", "Вывести драконов коллекции от младшего к старшему", 0);
        this.manager = manager;
    }

    @Override
    public boolean execute(ArrayList<String> args) {
        List<Dragon> dragons = new ArrayList<>(manager.getDragons());
        Collections.sort(dragons);
        TextFormatter.printMessage(dragons.toString());
        return true;
    }
}
