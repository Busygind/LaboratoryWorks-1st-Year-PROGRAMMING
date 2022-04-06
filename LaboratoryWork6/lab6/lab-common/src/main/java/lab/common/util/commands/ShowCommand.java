package lab.common.util.commands;

import lab.common.util.entities.CollectionManager;
import lab.common.util.entities.Dragon;
import lab.common.util.requestSystem.Response;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ShowCommand extends CommandAbstract {

    public ShowCommand() {
        super("show", "вывести на экран элементы коллекции в строковом представлении", 0);
    }

    @Override
    public Response execute(CollectionManager manager) {
        ArrayList<Dragon> sortedList = new ArrayList<>(manager.getDragons());
        sortedList = (ArrayList<Dragon>) sortedList.stream().sorted(Dragon::compareByName).collect(Collectors.toList());
        return new Response(sortedList, "List of dragons: ");
    }
}
