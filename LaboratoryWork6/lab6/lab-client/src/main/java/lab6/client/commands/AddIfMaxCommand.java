package lab6.client.commands;

import lab6.client.entities.Dragon;

import java.util.ArrayList;

public class AddIfMaxCommand extends CommandAbstract {

    public AddIfMaxCommand() {
        super("add_if_max", "добавление дракона в коллекцию, если он старше всех существующих", Dragon.COUNT_OF_PRIMITIVE_ARGS);
    }

    //TODO реализовать
    @Override
    public boolean execute(ArrayList<String> args) {
        return true;
    }
}
