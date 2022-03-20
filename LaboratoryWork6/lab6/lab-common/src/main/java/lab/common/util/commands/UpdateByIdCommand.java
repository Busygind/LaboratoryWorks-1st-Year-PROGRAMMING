package lab.common.util.commands;

import lab.common.util.entities.CollectionManager;
import lab.common.util.entities.Dragon;
import lab.common.util.handlers.TextFormatter;

public class UpdateByIdCommand extends CommandAbstract {

    private long id;
    private Dragon dragon;
    public UpdateByIdCommand(long id, Dragon dragon) {
        super("update_by_id", "Обновить данные о элементе коллекции по данному id", 1);
        this.dragon = dragon;
        this.id = id;
    }

    @Override
    public String execute(CollectionManager manager) {
        boolean flag = false;
        for (Dragon elem : manager.getDragons()) {
            if (elem.getId() == id) {
                manager.removeById(id);
                flag = true;
            }
        }
        if (flag) {
            manager.addDragon(dragon);
            return TextFormatter.colorInfoMessage("Info about dragon successfully updated");
        } else {
            return TextFormatter.colorInfoMessage("ID not found");
        }
    }
}
