package lab.common.util.commands;

import lab.common.util.entities.CollectionManager;
import lab.common.util.handlers.TextFormatter;


public class ExitCommand extends CommandAbstract {

    public ExitCommand() {
        super("exit", "Выход из программы с сохранением", 0);
    }

    @Override
    public String execute(CollectionManager manager) {
        return TextFormatter.colorInfoMessage("Connection disabled");
    }
}
