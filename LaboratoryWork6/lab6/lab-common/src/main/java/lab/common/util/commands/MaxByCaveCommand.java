package lab.common.util.commands;

import lab.common.util.entities.CollectionManager;
import lab.common.util.entities.Dragon;
import lab.common.util.handlers.TextFormatter;

import java.util.ArrayList;

public class MaxByCaveCommand extends CommandAbstract {

    public MaxByCaveCommand() {
        super("max_by_cave", "Вывести любого дракона из коллекции, глубина пещеры которого является максимальной", 0);
    }

    @Override
    public String execute(CollectionManager manager) {
        double maxDepth = Double.MIN_VALUE;
        Dragon dragonWithDeepestCave = null;
        for (Dragon dragon : manager.getDragons()) {
            if (dragon.getCave().getDepth() > maxDepth) {
                maxDepth = dragon.getCave().getDepth();
                dragonWithDeepestCave = dragon;
            }
        }
        return TextFormatter.colorMessage("Данные о драконе с самой глубокой пещерой:\n" + dragonWithDeepestCave);
    }
}
