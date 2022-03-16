package lab.client.commands;

import lab.client.entities.CollectionManager;
import lab.client.entities.Dragon;
import lab.client.handlers.ArgumentsListener;
import lab.client.handlers.TextFormatter;

import java.util.ArrayList;

public class AddCommand extends CommandAbstract {

    private final CollectionManager manager;
    private final ArgumentsListener argumentsListener = new ArgumentsListener();

    public AddCommand(CollectionManager manager) {
        super("add", "Добавить элемент в коллекцию", Dragon.COUNT_OF_PRIMITIVE_ARGS);
        this.manager = manager;
    }

    @Override
    public boolean execute(ArrayList<String> args) {
        String name = args.get(0).substring(0, 1).toUpperCase() + args.get(0).substring(1); //Делаем имя с большой буквы
        Dragon dragon = new Dragon();
        try {
            dragon.setAge(Integer.parseInt(args.get(1)));
            dragon.setWingspan(Integer.parseInt(args.get(2)));
        } catch (NumberFormatException e) {
            TextFormatter.printErrorMessage("Аргументы имеют неверный формат");
            return false;
        }
        dragon.setName(name);
        dragon.setCoordinates(argumentsListener.inputCoordinates());
        argumentsListener.inputColor(dragon);
        argumentsListener.inputCharacter(dragon);
        dragon.setCave(argumentsListener.inputCave());
        manager.addDragon(dragon);
        return true;
    }
}