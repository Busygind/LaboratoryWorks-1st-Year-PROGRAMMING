package lab6.client.commands;

import lab6.client.entities.Dragon;

import java.util.ArrayList;

public class AddIfMinCommand extends CommandAbstract {


    public AddIfMinCommand() {
        super("add_if_min", "добавить дракона в коллекцию, если его возраст меньше, чем у самого младшего в коллекции", Dragon.COUNT_OF_PRIMITIVE_ARGS);
    }

    //TODO РЕАЛИЗОВАТЬ
    @Override
    public boolean execute(ArrayList<String> args) {
        return false;
    }
}
