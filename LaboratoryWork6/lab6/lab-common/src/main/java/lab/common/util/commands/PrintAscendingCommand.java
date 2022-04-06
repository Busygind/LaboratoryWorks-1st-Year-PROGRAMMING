package lab.common.util.commands;

import lab.common.util.entities.CollectionManager;
import lab.common.util.entities.Dragon;
import lab.common.util.handlers.TextFormatter;
import lab.common.util.requestSystem.Response;

import java.util.ArrayList;
import java.util.Collections;

public class PrintAscendingCommand extends CommandAbstract {

    public PrintAscendingCommand() {
        super("print_ascending", "Вывести драконов коллекции от младшего к старшему", 0);
    }

    @Override
    public Response execute(CollectionManager manager) {
        ArrayList<Dragon> dragons = new ArrayList<>(manager.getDragons());
        Collections.sort(dragons);
        return new Response(dragons, TextFormatter.colorInfoMessage("List of dragons compared of age: "));
    }
}
