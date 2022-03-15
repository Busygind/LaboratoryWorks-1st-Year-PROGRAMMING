package lab.client.commands;

import lab.client.entities.CollectionManager;
import lab.client.entities.Dragon;
import lab.client.handlers.TextFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintAscendingCommand extends CommandAbstract {

    private final CollectionManager manager;

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
