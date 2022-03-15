package lab.client.commands;

import lab.client.entities.CollectionManager;
import lab.client.entities.Dragon;
import lab.client.handlers.ArgumentsListener;
import lab.client.handlers.TextFormatter;

import java.util.ArrayList;

public class AddIfMaxCommand extends CommandAbstract {

    private final CollectionManager manager;
    private final ArgumentsListener argumentsListener = new ArgumentsListener();;

    public AddIfMaxCommand(CollectionManager manager) {
        super("add_if_max", "Добавить дракона в коллекцию, если он старше всех существующих", Dragon.COUNT_OF_PRIMITIVE_ARGS);
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
        int maxAge = 0;
        for (Dragon dragon : manager.getDragons()) {
            if (dragon.getAge() > maxAge) {
                maxAge = dragon.getAge();
            }
        }
        if (age > maxAge) {
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
            TextFormatter.printInfoMessage("В коллекции есть дракон постарше!");
            return false;
        }

    }
}
//    String name = args.get(0).substring(0, 1).toUpperCase() + args.get(0).substring(1); //Делаем имя с большой буквы
//    Dragon dragon = new Dragon();
//        try {
//                dragon.setAge(Integer.parseInt(args.get(1)));
//                dragon.setWingspan(Integer.parseInt(args.get(2)));
//                } catch (NumberFormatException e) {
//                TextFormatter.printErrorMessage("Аргументы имеют неверный формат");
//                return false;
//                }
//                dragon.setName(name);
//                dragon.setCoordinates(argumentsListener.inputCoordinates());
//                argumentsListener.inputColor(dragon);
//                argumentsListener.inputCharacter(dragon);
//                dragon.setCave(argumentsListener.inputCave());
//                manager.addDragon(dragon);
//                return true;
        /**
 //     * Метод, вызываемый командой <strong>add_if_max</strong>
 //     *
 //     * @param name имя дракона, которого пытается добавить пользователь
 //     * @param age возраст дракона, которого пытается добавить пользователь
 //     * @param wingspan размах крыльев дракона, которого пытается добавить пользователь
 //     */
//    @Command(name = "add_if_max",
//            args = "{name, age, wingspan}",
//            countOfArgs = Dragon.COUNT_OF_PRIMITIVE_ARGS,
//            desc = "Добавить дракона в коллекцию, если его возраст больше, чем у самого старшего в коллекции")
//    private void addIfMax(String name, String age, String wingspan) {
//        int maxAge = 0;
//        for (Dragon dragon : collection.getDragons()) {
//            if (dragon.getAge() > maxAge) {
//                maxAge = dragon.getAge();
//            }
//        }
//        if (Integer.parseInt(age) > maxAge) {
//            add(name, age, wingspan);
//        } else {
//            System.out.println("В коллекции есть дракон постарше!");
//        }
//    }
