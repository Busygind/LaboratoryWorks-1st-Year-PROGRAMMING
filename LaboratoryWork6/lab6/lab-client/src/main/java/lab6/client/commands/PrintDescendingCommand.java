package lab6.client.commands;

import lab6.client.entities.CollectionManager;
import lab6.client.entities.Dragon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintDescendingCommand extends CommandAbstract {
    CollectionManager manager;

    public PrintDescendingCommand(CollectionManager manager) {
        super("print_descending", "Вывести всех драконов от старшего к младшему", 0);
        this.manager = manager;
    }

    @Override
    public boolean execute(ArrayList<String> args) {
        List<Dragon> dragons = new ArrayList<>(manager.getDragons());
        dragons.sort(Collections.reverseOrder());
        System.out.println(dragons);
        return true;
    }
}
