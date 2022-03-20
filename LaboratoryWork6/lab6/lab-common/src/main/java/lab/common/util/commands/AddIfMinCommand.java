package lab.common.util.commands;


import lab.common.util.entities.CollectionManager;
import lab.common.util.entities.Dragon;
import lab.common.util.handlers.TextFormatter;


public class AddIfMinCommand extends CommandAbstract {

    private final Dragon dragon;

    public AddIfMinCommand(Dragon dragon) {
        super("add_if_min", "Добавить дракона в коллекцию, если его возраст меньше, чем у самого младшего в коллекции", Dragon.COUNT_OF_PRIMITIVE_ARGS);
        this.dragon = dragon;
    }

    @Override
    public String execute(CollectionManager manager) {
        int minAge = manager.getMin().getAge();
        if (dragon.getAge() < minAge) {
            manager.addDragon(dragon);
            TextFormatter.printInfoMessage("Dragon successfully added");
            return TextFormatter.colorInfoMessage("Dragon successfully added");
        } else {
            return TextFormatter.colorInfoMessage("В коллекции есть дракон помладше!");
        }
    }
}

//    /**
//     * Метод, вызываемый командой <strong>add_if_min</strong>
//     *
//     * @param name имя дракона, которого пытается добавить пользователь
//     * @param age возраст дракона, которого пытается добавить пользователь
//     * @param wingspan размах крыльев дракона, которого пытается добавить пользователь
//     */
//    @Command(name = "add_if_min",
//            args = "{name, age, wingspan}",
//            countOfArgs = Dragon.COUNT_OF_PRIMITIVE_ARGS,
//            desc = "Добавить дракона в коллекцию, если его возраст меньше, чем у самого младшего в коллекции")
//    private void addIfMin(String name, String age, String wingspan) {
//        int minAge = Integer.MAX_VALUE;
//        for (Dragon dragon : collection.getDragons()) {
//            if (dragon.getAge() < minAge) {
//                minAge = dragon.getAge();
//            }
//        }
//        if (Integer.parseInt(age) < minAge) {
//            add(name, age, wingspan);
//        } else {
//            System.out.println("В коллекции есть дракон помладше!");
//        }
//    }
