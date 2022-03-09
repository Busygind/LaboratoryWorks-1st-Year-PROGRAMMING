package lab6.client.commands;

import lab6.client.handlers.CommandListener;
import lab6.client.handlers.TextFormatter;

import java.util.ArrayList;
import java.util.Map;

public class HelpCommand extends CommandAbstract {

    public HelpCommand() {
        super("help", "Вывести список доступных команд", 0);
    }

    @Override
    public boolean execute(ArrayList<String> args) {
        StringBuilder sb = new StringBuilder("Список команд: \n");
        Map<String, CommandAbstract> commands = CommandListener.getCommandsNew();
        for (String key : commands.keySet()) {
            sb.append(commands.get(key).getDescription()).append("\n");
        }
        TextFormatter.printMessage(sb.toString());
        return false;
    }
}
