package lab.common.util.commands;

import lab.common.util.entities.CollectionManager;
import lab.common.util.entities.Dragon;
import lab.common.util.handlers.TextFormatter;


public class AddIfMaxCommand extends CommandAbstract {

    private final Dragon dragon;

    public AddIfMaxCommand(Dragon dragon) {
        super("add_if_max", "Добавить дракона в коллекцию, если он старше всех существующих", Dragon.COUNT_OF_PRIMITIVE_ARGS);
        this.dragon = dragon;
    }

    @Override
    public String execute(CollectionManager manager) {
        int maxAge = manager.getMax().getAge();
        if (dragon.getAge() > maxAge) {
            manager.addDragon(dragon);
            return TextFormatter.colorInfoMessage("Dragon successfully added");
        } else {
            return TextFormatter.colorInfoMessage("В коллекции есть дракон постарше!");
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
