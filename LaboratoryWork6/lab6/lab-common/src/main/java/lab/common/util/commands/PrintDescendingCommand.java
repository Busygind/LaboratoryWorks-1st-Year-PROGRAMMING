package lab.common.util.commands;

import lab.common.util.entities.CollectionManager;
import lab.common.util.entities.Dragon;
import lab.common.util.handlers.TextFormatter;
import lab.common.util.requestSystem.Response;

import java.util.ArrayList;
import java.util.Collections;

public class PrintDescendingCommand extends CommandAbstract {

    public PrintDescendingCommand() {
        super("print_descending", "Вывести всех драконов от старшего к младшему", 0);
    }

    @Override
    public Response execute(CollectionManager manager) {
        ArrayList<Dragon> dragons = new ArrayList<>(manager.getDragons());
        dragons.sort(Collections.reverseOrder());
        return new Response(dragons, TextFormatter.colorInfoMessage("List of dragons compared of age (reverse): "));
    }
}
