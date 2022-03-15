package lab.client.commands;

import lab.client.entities.CollectionManager;
import lab.client.entities.Dragon;
import lab.client.handlers.ArgumentsListener;
import lab.client.handlers.TextFormatter;

import java.util.ArrayList;

public class AddIfMinCommand extends CommandAbstract {

    private final CollectionManager manager;
    private final ArgumentsListener argumentsListener = new ArgumentsListener();

    public AddIfMinCommand(CollectionManager manager) {
        super("add_if_min", "Добавить дракона в коллекцию, если его возраст меньше, чем у самого младшего в коллекции", Dragon.COUNT_OF_PRIMITIVE_ARGS);
        this.manager = manager;
    }

    @Override
    public boolean execute(ArrayList<String> args) {
        String name = args.get(0).substring(0, 1).toUpperCase() + args.get(0).substring(1);
        int age;
        int wingspan;
        try {
            age = Integer.parseInt(args.get(1));
            wingspan = Integer.parseInt(args.get(2));
        } catch (NumberFormatException e) {
            TextFormatter.printErrorMessage("Аргументы имеют неверный формат");
            return false;
        }
        int minAge = Integer.MAX_VALUE;
        for (Dragon dragon : manager.getDragons()) {
            if (dragon.getAge() < minAge) {
                minAge = dragon.getAge();
            }
        }
        if (age < minAge) {
            Dragon dragon = new Dragon();
            dragon.setAge(age);
            dragon.setWingspan(wingspan);
            dragon.setName(name);
            dragon.setCoordinates(argumentsListener.inputCoordinates());
            argumentsListener.inputColor(dragon);
            argumentsListener.inputCharacter(dragon);
            dragon.setCave(argumentsListener.inputCave());
            manager.addDragon(dragon);
            return true;
        } else {
            TextFormatter.printInfoMessage("В коллекции есть дракон помладше!");
            return false;
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