package lab.common.util.commands;

import lab.common.util.entities.CollectionManager;
import lab.common.util.entities.Dragon;
import lab.common.util.handlers.TextFormatter;
import lab.common.util.requestSystem.Response;

import java.util.ArrayList;
import java.util.List;

public class MaxByCaveCommand extends CommandAbstract {

    public MaxByCaveCommand() {
        super("max_by_cave", "Вывести любого дракона из коллекции, глубина пещеры которого является максимальной", 0);
    }

    @Override
    public Response execute(CollectionManager manager) {
        Dragon dragon = manager.getMaxByCave();
        List<Dragon> dragons = new ArrayList<>();
        dragons.add(dragon);
        return new Response(dragons, TextFormatter.colorMessage("Info about dragon with deepest cave: "));
    }
}
