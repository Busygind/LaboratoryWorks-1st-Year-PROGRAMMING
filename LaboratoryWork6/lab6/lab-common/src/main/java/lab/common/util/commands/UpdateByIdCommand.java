package lab.common.util.commands;

import lab.common.util.entities.CollectionManager;
import lab.common.util.entities.Dragon;
import lab.common.util.handlers.ArgumentsListener;
import lab.common.util.handlers.TextFormatter;

import java.util.ArrayList;

public class UpdateByIdCommand extends CommandAbstract {

    private final CollectionManager manager;
    private final ArgumentsListener argumentsListener = new ArgumentsListener();

    public UpdateByIdCommand(CollectionManager manager) {
        super("update_by_id", "Обновить данные о элементе коллекции по данному id", 1);
        this.manager = manager;
    }

    @Override
    public boolean execute(ArrayList<String> args) {
        String id = args.get(0);
        try {
            long newId = Long.parseLong(id);
            for (Dragon elem : manager.getDragons()) {
                if (elem.getId() == newId) {
                    TextFormatter.printInfoMessage("Введите информацию о драконе: {name, age, wingspan}");
                    argumentsListener.inputPrimitives(elem);
                    elem.setCoordinates(argumentsListener.inputCoordinates());
                    argumentsListener.inputColor(elem);
                    argumentsListener.inputCharacter(elem);
                    elem.setCave(argumentsListener.inputCave());
                    TextFormatter.printInfoMessage("Данные о драконе успешно обновлены");
                }
            }
        } catch (NumberFormatException e) {
            TextFormatter.printErrorMessage("ID представлено в неверном формате");
        }
        return true;
    }
}
