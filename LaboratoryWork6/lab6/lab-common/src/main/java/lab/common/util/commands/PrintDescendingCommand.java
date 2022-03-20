package lab.common.util.commands;

import lab.common.util.entities.CollectionManager;
import lab.common.util.entities.Dragon;
import lab.common.util.handlers.TextFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintDescendingCommand extends CommandAbstract {

    public PrintDescendingCommand() {
        super("print_descending", "Вывести всех драконов от старшего к младшему", 0);
    }

    @Override
    public String execute(CollectionManager manager) {
        List<Dragon> dragons = new ArrayList<>(manager.getDragons());
        dragons.sort(Collections.reverseOrder());
        return TextFormatter.colorMessage(dragons.toString());
    }
}
