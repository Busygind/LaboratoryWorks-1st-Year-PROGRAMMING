package lab.common.util.commands;

import lab.common.util.entities.CollectionManager;
import lab.common.util.handlers.TextFormatter;
import lab.common.util.requestSystem.Response;


public class ExitCommand extends CommandAbstract {

    public ExitCommand() {
        super("exit", "Выход из программы с сохранением", 0);
    }

    @Override
    public Response execute(CollectionManager manager) {
        return new Response(TextFormatter.colorInfoMessage("Connection disabled"));
    }
}
