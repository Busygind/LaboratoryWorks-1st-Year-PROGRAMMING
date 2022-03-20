package lab.common.util.commands;

import lab.common.util.entities.CollectionManager;
import lab.common.util.entities.Dragon;
import lab.common.util.handlers.TextFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintAscendingCommand extends CommandAbstract {

    public PrintAscendingCommand() {
        super("print_ascending", "Вывести драконов коллекции от младшего к старшему", 0);
    }

    @Override
    public String execute(CollectionManager manager) {
        List<Dragon> dragons = new ArrayList<>(manager.getDragons());
        Collections.sort(dragons);
        return TextFormatter.colorMessage(dragons.toString());
    }
}
