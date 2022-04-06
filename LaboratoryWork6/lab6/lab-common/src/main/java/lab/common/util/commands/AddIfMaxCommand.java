package lab.common.util.commands;

import lab.common.util.entities.CollectionManager;
import lab.common.util.entities.Dragon;
import lab.common.util.handlers.TextFormatter;
import lab.common.util.requestSystem.Response;

public class AddIfMaxCommand extends CommandAbstract {

    private final Dragon dragon;

    public AddIfMaxCommand(Dragon dragon) {
        super("add_if_max", "Добавить дракона в коллекцию, если он старше всех существующих", Dragon.COUNT_OF_PRIMITIVE_ARGS);
        this.dragon = dragon;
    }

    @Override
    public Response execute(CollectionManager manager) {
        int maxAge = manager.getMax().getAge();
        if (dragon.getAge() > maxAge) {
            manager.addDragon(dragon);
            return new Response(TextFormatter.colorInfoMessage("Dragon successfully added"));
        } else {
            return new Response(TextFormatter.colorInfoMessage("В коллекции есть дракон постарше!"));
        }
    }
}
