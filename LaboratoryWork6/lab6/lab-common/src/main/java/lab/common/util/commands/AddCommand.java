package lab.common.util.commands;

import lab.common.util.entities.CollectionManager;
import lab.common.util.entities.Dragon;
import lab.common.util.handlers.TextFormatter;


public class AddCommand extends CommandAbstract {

    private Dragon dragon;

    public AddCommand(Dragon dragon) {
        super("add", "Добавить элемент в коллекцию", Dragon.COUNT_OF_PRIMITIVE_ARGS);
        this.dragon = dragon;
    }

    @Override
    public String execute(CollectionManager manager) {
        manager.addDragon(dragon);
        return TextFormatter.colorInfoMessage("Dragon successfully added");
    }

    public void setDragon(Dragon dragon) {
        this.dragon = dragon;
    }
}
