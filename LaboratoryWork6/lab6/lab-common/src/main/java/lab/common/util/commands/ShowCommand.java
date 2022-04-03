package lab.common.util.commands;

import lab.common.util.entities.CollectionManager;
import lab.common.util.handlers.TextFormatter;

public class ShowCommand extends CommandAbstract {

    public ShowCommand() {
        super("show", "вывести на экран элементы коллекции в строковом представлении", 0);
    }

    @Override
    public String execute(CollectionManager manager) {
        return TextFormatter.colorMessage(manager.getDragons().toString());
    }
}
