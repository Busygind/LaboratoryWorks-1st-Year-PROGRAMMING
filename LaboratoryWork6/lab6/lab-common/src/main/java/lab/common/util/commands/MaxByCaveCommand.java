package lab.common.util.commands;

import lab.common.util.entities.CollectionManager;
import lab.common.util.entities.Dragon;
import lab.common.util.handlers.TextFormatter;

import java.util.ArrayList;

public class MaxByCaveCommand extends CommandAbstract {

    private final CollectionManager manager;

    public MaxByCaveCommand(CollectionManager manager) {
        super("max_by_cave", "Вывести любого дракона из коллекции, глубина пещеры которого является максимальной", 0);
        this.manager = manager;
    }

    @Override
    public boolean execute(ArrayList<String> args) {
        double maxDepth = Double.MIN_VALUE;
        Dragon dragonWithDeepestCave = null;
        for (Dragon dragon : manager.getDragons()) {
            if (dragon.getCave().getDepth() > maxDepth) {
                maxDepth = dragon.getCave().getDepth();
                dragonWithDeepestCave = dragon;
            }
        }
        TextFormatter.printMessage("Данные о драконе с самой глубокой пещерой:\n" + dragonWithDeepestCave);
        return true;
    }
}
